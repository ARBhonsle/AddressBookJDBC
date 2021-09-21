package mysqldb;

import java.sql.*;

public class AddressBookJDBC {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/address_book_service";
        String user = "root";
        String password = "root@1234";
        Connection con = null;
        try{
            // connect with database
            con = DriverManager.getConnection(url,user,password);
            con.setAutoCommit(false);
            // query entries from database table
            Statement stmt = con.createStatement();

            ResultSet resultSet = stmt.executeQuery("select * from address_book;");
            ResultSetMetaData metaData = resultSet.getMetaData();

            //  show/display entries from table
            while (resultSet.next()){
                for(int i=0;i<metaData.getColumnCount();i++){
                    if(i==0){
                        System.out.print(metaData.getColumnLabel(i+1)+"\t");
                    }else{
                        System.out.print(resultSet.getString(i)+"\t");
                    }
                }
                System.out.println();
            }
            con.commit();
        } catch (SQLException sqlException){
            con.rollback();
            sqlException.printStackTrace();
        } finally {
            con.close();
        }
    }
}
