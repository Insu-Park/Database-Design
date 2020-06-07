import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Utilities {
    private static String curYear, curMonth, curDay, curHour, curMin, curSec;

    private static void SetCurTime(){
        Calendar current = Calendar.getInstance();
        curYear = String.format("%04d", current.get(Calendar.YEAR));
        curMonth = String.format("%02d", current.get(Calendar.MONTH) + 1);
        curDay = String.format("%02d", current.get(Calendar.DAY_OF_MONTH));
        curHour = String.format("%02d", current.get(Calendar.HOUR));
        curMin = String.format("%02d", current.get(Calendar.MINUTE));
        curSec = String.format("%02d", current.get(Calendar.SECOND));
    }
    public static void UpdateTableItem() throws Exception{
        Connection mainConn = DBConnection.getMainConnection();
        Connection branchConn = DBConnection.getBranchConnection();
        PreparedStatement pstmMain, pstmBranch;  // SQL 문을 나타내는 객체
        pstmMain = pstmBranch = null;
        ResultSet rsMain, rsBranch;
        rsMain = rsBranch = null;

        SetCurTime();

        String queryBranch = "SELECT LAST_UPDATE_DATE FROM LAST_ITEM_UPDATE_DATE";
        pstmBranch = branchConn.prepareStatement(queryBranch);
        rsBranch = pstmBranch.executeQuery();
        rsBranch.next();
        String lastUpdateDate = rsBranch.getString("LAST_UPDATE_DATE");
        rsBranch.close();
        pstmBranch.close();

        // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
        // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
        String queryMain = "SELECT ITEM_NO, ITEM_NAME, ITEM_PER_PRICE, ITEM_REC_PRICE, EXDATE_IMPT_FLAG, ITEM_DIV_CD, ITEM_DIV_CD, ITEM_ODR_UNIT " +
                "FROM ITEM WHERE LAST_MODIF_DATE > "+ lastUpdateDate;
        System.out.println("query: " + queryMain);
        pstmMain = mainConn.prepareStatement(queryMain);
        rsMain = pstmMain.executeQuery();

        while(rsMain.next()){
            String itemNo = rsMain.getString("ITEM_NO");
            String itemName = rsMain.getString("ITEM_NAME");
            double itemPerPrice = rsMain.getDouble("ITEM_PER_PRICE");
            int itemRecPrice = rsMain.getInt("ITEM_REC_PRICE");
            String exdateImptFlag = rsMain.getString("EXDATE_IMPT_FLAG");
            String itemDivCd = rsMain.getString("ITEM_DIV_CD");
            int itemOdrUnit = rsMain.getInt("ITEM_ODR_UNIT");

            queryBranch = "SELECT * FROM ITEM WHERE ITEM_NO='" + itemNo + "'";
            pstmBranch = branchConn.prepareStatement(queryBranch);
            rsBranch = pstmBranch.executeQuery();
            if(rsBranch.next()){ // 존재하므로 업데이트
                rsBranch.close();
                pstmBranch.close();
                queryBranch = "UPDATE ITEM SET ITEM_NAME='" + itemName + "', ITEM_PER_PRICE=" + itemPerPrice + ", ITEM_DIV_CD='" +itemDivCd +
                        "', ITEM_REC_PRICE=" + itemRecPrice + ", EXDATE_IMPT_FLAG='" + exdateImptFlag + "', ITEM_ODR_UNIT=" + itemOdrUnit +
                        " WHERE ITEM_NO='" + itemNo + "'";
            }
            else{
                rsBranch.close();
                pstmBranch.close();
                queryBranch = "INSERT INTO ITEM(ITEM_NO, ITEM_NAME, ITEM_PER_PRICE, ITEM_REC_PRICE, EXDATE_IMPT_FLAG,  ITEM_DIV_CD, ITEM_ODR_UNIT)" +
                        " VALUES('" + itemNo + "', '" + itemName + "', " + itemPerPrice + ", " + itemRecPrice + ", '" + exdateImptFlag + "', " +
                        "'" + itemDivCd +"', " + itemOdrUnit +")";
            }
            System.out.println(queryBranch);
            pstmBranch = branchConn.prepareStatement(queryBranch);
            rsBranch = pstmBranch.executeQuery();
        }

        queryBranch = "UPDATE LAST_ITEM_UPDATE_DATE SET LAST_UPDATE_DATE='" + curYear + curMonth + curDay + curHour + curMin + curSec + "'";
        pstmBranch = branchConn.prepareStatement(queryBranch);
        rsBranch = pstmBranch.executeQuery();
        rsBranch.close();
        pstmBranch.close();

        branchConn.commit();

        mainConn.close();
    }

    public static void AddDisplayItem(String barcode) throws Exception{
        String itemNo = barcode.substring(0, 9);
        String exdate;
        boolean isExdateImpt = false;
        if(itemNo.substring(0,1).compareTo("Y") == 0) {
            isExdateImpt = true;
            exdate = barcode.substring(9);
        }
        else
            exdate = "000000000000";

        if(SharedGlobalWithUI.displayItems.containsKey(barcode)){ // 리스트에 있으면
            Item item = SharedGlobalWithUI.displayItems.get(barcode);
            item.qty++;
        }else{
            Connection conn = DBConnection.getBranchConnection();

            // get item name
            String query = String.format("SELECT ITEM_NAME FROM ITEM WHERE ITEM_NO = '%s'", itemNo);
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            String itemName = rs.getString("ITEM_NAME");
            rs.close();
            pstm.close();

            // get item price
            query = String.format("SELECT PRICE FROM STOCK WHERE ITEM_NO = '%s' AND EXDATE = '%s'", itemNo, exdate);
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            rs.next();
            int itemPrice = rs.getInt("PRICE");
            rs.close();
            pstm.close();

            Item item = new Item();
            item.itemNo = itemNo;
            item.exdate = exdate;
            item.isExdateImpt = isExdateImpt;
            item.price = itemPrice;
            item.qty = 1;

            SharedGlobalWithUI.displayItems.put(barcode, item);

        }
        SharedGlobalWithUI.Invalidate();
    }

    // 데모를 위해 초기 재고 추가
    public static void InitStock() throws Exception{
        Connection conn = DBConnection.getBranchConnection();
        String query = "SELECT ITEM_NO, ITEM_REC_PRICE FROM ITEM WHERE EXDATE_IMPT_FLAG='N'";
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();

        List<String> itemNos = new ArrayList<String>();
        List<Integer> itemPrices = new ArrayList<Integer>();

        while(rs.next()){
            String itemNo = rs.getString("ITEM_NO");
            int itemPrice = rs.getInt("ITEM_REC_PRICE");
            itemNos.add(itemNo);
            itemPrices.add(itemPrice);
        }
        rs.close();
        pstm.close();

        Iterator<String> iterNo = itemNos.iterator();
        Iterator<Integer> iterPrice = itemPrices.iterator();

        while(iterNo.hasNext()){
            query = String.format("INSERT INTO STOCK(ITEM_NO, EXDATE, WRHOUSE_QTY, DISPLAY_QTY, PRICE)" +
                    " VALUES('%s', '%s', 100, 10, %d)", iterNo.next(), "000000000000", iterPrice.next());
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            rs.close();
            pstm.close();
        }

        query = "SELECT ITEM_NO, ITEM_REC_PRICE FROM ITEM WHERE EXDATE_IMPT_FLAG='Y'";
        pstm = conn.prepareStatement(query);
        rs = pstm.executeQuery();

        itemNos = new ArrayList<String>();
        itemPrices = new ArrayList<Integer>();

        while(rs.next()){
            String itemNo = rs.getString("ITEM_NO");
            int itemPrice = rs.getInt("ITEM_REC_PRICE");
            itemNos.add(itemNo);
            itemPrices.add(itemPrice);
        }
        rs.close();
        pstm.close();

        iterNo = itemNos.iterator();
        iterPrice = itemPrices.iterator();

        while(iterNo.hasNext()){
            String no = iterNo.next();
            int price = iterPrice.next();
            query = String.format("INSERT INTO STOCK(ITEM_NO, EXDATE, WRHOUSE_QTY, DISPLAY_QTY, PRICE)" +
                    " VALUES('%s', '%s', 0, 2, %d)", no, "201906151200", price);
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            rs.close();
            pstm.close();

            query = String.format("INSERT INTO STOCK(ITEM_NO, EXDATE, WRHOUSE_QTY, DISPLAY_QTY, PRICE)" +
                    " VALUES('%s', '%s', 0, 2, %d)", no, "201906161200", price);
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            rs.close();
            pstm.close();
        }

        conn.commit();
    }

    public static void ClearDisplayItems(){
        SharedGlobalWithUI.displayItems.clear();
    }

    public static void SellItems() throws Exception{
        Connection conn = DBConnection.getBranchConnection();
        String query;
        PreparedStatement pstm;
        ResultSet rs;

        int totalPrice = 0;
        // 재고 체크
        for (Map.Entry<String, Item> entry : SharedGlobalWithUI.displayItems.entrySet()) {
            String barcode = entry.getKey();
            Item item =  entry.getValue();

            query = String.format("SELECT WRHOUSE_QTY, DISPLAY_QTY FROM STOCK WHERE ITEM_NO='%s' AND EXDATE='%s'", item.itemNo, item.exdate);
            pstm = conn.prepareStatement(query);
            System.out.println("SQL: " + query);
            rs = pstm.executeQuery();
            rs.next();
            int displayQty = rs.getInt("DISPLAY_QTY");
            int wrhousQty = rs.getInt("WRHOUSE_QTY");
            int qty = displayQty + wrhousQty;
            rs.close();
            pstm.close();

            if(qty < item.qty) {
                SharedGlobalWithUI.PopUpError(item.itemName + "의 재고가 모자랍니다.");
                return;
            }
            totalPrice += item.price*item.qty;
        }

        // 판매 순번 구함
        query = "SELECT MAX(SELL_NO) FROM SELL";
        pstm = conn.prepareStatement(query);
        rs = pstm.executeQuery();

        int sellNo, outputNo;
        if(rs.next())
            sellNo = rs.getInt("MAX(SELL_NO)") + 1;
        else
            sellNo = 0;
        rs.close();
        pstm.close();

        // 출고 순번 구함
        query = "SELECT OUTPUT_NO FROM OUTPUT WHERE OUTPUT_NO LIKE 'SEL%' ORDER BY OUTPUT_NO DESC";
        pstm = conn.prepareStatement(query);
        rs = pstm.executeQuery();
        if(rs.next())
            outputNo = Integer.parseInt(rs.getString("MAX(OUTPUT_NO").substring(3));
        else
            outputNo = 0;
        rs.close();
        pstm.close();

        SetCurTime();


        // 출고 엔티티 추가
        query = String.format("INSERT INTO OUTPUT(OUTPUT_NO, OUTPUT_TIME, OUTPUT_DIV_CD) " +
                "VALUES('SEL%010d', '%s', 'CD04SEL')", outputNo, curYear + curMonth + curDay + curHour + curMin);
        ExecSql(conn, query);

        // 판매 엔티티 추가
        query = String.format("INSERT INTO SELL(SELL_NO, TOTAL_PRICE, TAX, STAFF_NO, SELL_TIME, OUTPUT_NO, CUSTOM_SEX, CUSTOM_RAGE_CD) " +
                        "VALUES(%d, %d, %f, '%s', '%s', 'SEL%010d', '%s', '%s')", sellNo, totalPrice, totalPrice * 0.1, SharedGlobalWithUI.workingStaffNo,
                curYear + curMonth + curDay + curHour + curMin + curSec, outputNo, SharedGlobalWithUI.customSex, SharedGlobalWithUI.customRageCd);
        ExecSql(conn, query);

        // 판매물품 엔티티 추가, 재고 수량 감소
        for (Map.Entry<String, Item> entry : SharedGlobalWithUI.displayItems.entrySet()) {
            String barcode = entry.getKey();
            Item item = entry.getValue();

            //판매 물품 엔티티 추가
            query = String.format("INSERT INTO SITEM(SELL_NO, ITEM_NO, QTY, PER_PRICE) " +
                    "VALUES(%d, '%s', %d, %d)", sellNo, item.itemNo, item.qty, item.price);
            try {
                ExecSql(conn, query);
            }
            catch(SQLException sqle){
                // 유통기한 다른 동일한 아이템인 경우 확인
                query = String.format("SELECT * FROM SITEM WHERE SELL_NO = %d AND ITEM_NO = '%s'", sellNo, item.itemNo);
                pstm = conn.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs.next()) { // 유통기한 다른 동일한 아이템인 경우
                    rs.close();
                    pstm.close();
                    query = String.format("UPDATE STITEM SET QTY=QTY+%d WHERE SELL_NO = %d AND ITEM_NO = '%s'", item.qty, sellNo, item.itemNo);
                    ExecSql(conn, query);
                } else {
                    rs.close();
                    pstm.close();
                    throw sqle;
                }
            }

            // 재고 수량 감소
            query = String.format("SELECT WRHOUSE_QTY, DISPLAY_QTY FROM STOCK WHERE ITEM_NO='%s' AND EXDATE='%s'", item.itemNo, item.exdate);
            pstm = conn.prepareStatement(query);
            System.out.println("SQL: " + query);
            rs = pstm.executeQuery();
            rs.next();
            int displayQty = rs.getInt("DISPLAY_QTY");
            int wrhousQty = rs.getInt("WRHOUSE_QTY");
            rs.close();
            pstm.close();

            if (displayQty >= item.qty) {
                displayQty -= item.qty;
                query = String.format("UPDATE STOCK SET DISPLAY_QTY=%d WHERE ITEM_NO = %s AND EXDATE = %s", displayQty, item.itemNo, item.exdate);
            } else {
                displayQty = 0;
                wrhousQty -= item.qty - displayQty;
                query = String.format("UPDATE STOCK SET DISPLAY_QTY=%d, WRHOUSE_QTY=%d WHERE ITEM_NO = %s AND EXDATE = %s", displayQty, wrhousQty,
                        item.itemNo, item.exdate);
            }

            ExecSql(conn, query);
        }

    }

    private static boolean ExecSql(Connection conn, String sql) throws Exception{
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println("SQL: " + sql);
        boolean ret = pstm.execute();
        pstm.close();
        return ret;
    }

    private static void ExecCommit(Connection conn) {
        try {
            ExecSql(conn, "commit");
        }catch(SQLException sqle){
            System.out.println("Commit Error");
            System.out.println(sqle.getSQLState());
            sqle.printStackTrace();
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ExecRollback(Connection conn) {
        try {
            ExecSql(conn, "rollback");
        }catch(SQLException sqle){
            System.out.println("rollback Error");
            System.out.println(sqle.getSQLState());
            sqle.printStackTrace();
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
