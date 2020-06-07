import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class SQLTest {
    public static void main(String args[])
    {
        Connection branchConn = DBConnection.getBranchConnection();
        Connection mainConn = DBConnection.getMainConnection();
        SharedGlobalWithUI.workingStaffNo = 2;
        SharedGlobalWithUI.customRageCd = "CD1003";
        SharedGlobalWithUI.customSex = "M";
        try {
            // Test Here
            Utilities.AddDisplayItem("N00100007");
            Utilities.AddDisplayItem("N00100007");
            Utilities.AddDisplayItem("Y00500001201906151200");
            Utilities.AddDisplayItem("Y00500001201906161200");
            for (Map.Entry<String, Item> entry : SharedGlobalWithUI.displayItems.entrySet()) {
                String key   = entry.getKey();
                Item value =  entry.getValue();
                System.out.println("Barcode: " + key);
                System.out.println("Name: " + value.itemName + " Qty: " + value.qty + " Price: " + value.price);
            }
            Utilities.SellItems();
            //Utilities.UpdateTableItem();
        }catch(SQLException sqle) {
            Connection conn = DBConnection.getBranchConnection();
            try {
                conn.rollback();
            }catch(SQLException sqle1){
                System.out.print(sqle1.getSQLState());
                System.out.println(" Error!");
                sqle1.printStackTrace();
            }
            System.out.print(sqle.getSQLState());
            System.out.println(" Error!");
            sqle.printStackTrace();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try {
                if (branchConn != null) branchConn.close();
                if(mainConn != null) mainConn.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }
}
