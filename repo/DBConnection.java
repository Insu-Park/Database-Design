import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBConnection
    {
        private static Connection mainConn = null;
        private static Connection branchConn = null;

        // not thread safe
        public static Connection getMainConnection() {
            if (mainConn == null) {
                try {
                    String user = "mainComp";
                    String pw = "elqlrhkwp";
                    String url = "jdbc:oracle:thin:@52.231.78.225:1521:mainComp";

                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    mainConn = DriverManager.getConnection(url, user, pw);

                    System.out.println("Database에 연결되었습니다.\n");

                    mainConn.setAutoCommit(false);

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle.toString());
                    sqle.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Unkonwn error");
                    e.printStackTrace();
                }
            }
            return mainConn;
        }

        // not thread safe
        public static Connection getBranchConnection() {
            if (branchConn == null) {
                try {
                    String user = "uos25_branch_comp";
                    String pw = "elqlrhkwp";
                    String url = "jdbc:oracle:thin:@localhost:1521:orcl";

                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    branchConn = DriverManager.getConnection(url, user, pw);

                    System.out.println("Database에 연결되었습니다.\n");

                    branchConn.setAutoCommit(false);
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle.toString());
                    sqle.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Unkonwn error");
                    e.printStackTrace();
                }
            }
            return branchConn;
        }
    }
