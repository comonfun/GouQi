package datatools;

import java.sql.*;

public class DatabaseUtils {
    public static void delete(int id) {
        try (final Connection conn= ConnectTool.getConnection();
             final Statement statement = conn.createStatement();
             final PreparedStatement preparedStatement = conn.prepareStatement("update details set id=id-1 where id>?");
         ){
            statement.execute("delete from details where id="+id);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            statement.executeUpdate("update sqlite_sequence set seq=seq-1 where name='details'");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updata(int id,String field,Object value){
        try (final Connection conn= ConnectTool.getConnection();
             final PreparedStatement preparedStatement = conn.prepareStatement("update details set "+field+"= ? where id=?")
        ){
            preparedStatement.setObject(1,value);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static String getRootPassword(){
        try (final Connection conn= ConnectTool.getConnection();
                final Statement statement = conn.createStatement()){
            final ResultSet resultSet = statement.executeQuery("select password from users where user='root'");
            String password="";
            if (resultSet.next()){
                password=resultSet.getString(1);
                return password;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public static boolean addUser(String user,String password,String rootPasswor) {
        try (final Connection conn = ConnectTool.getConnection();
             final PreparedStatement preparedStatement = conn.prepareStatement("insert into users values(?,?,?)")
        ) {

            if (rootPasswor.equals(getRootPassword())) {
                preparedStatement.setString(1, user);
                preparedStatement.setString(3, user);
                preparedStatement.setString(2, password);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static String verify(String sUser, String sPassWord) {
        try (final Connection conn = ConnectTool.getConnection();
             final PreparedStatement preparedStatement = conn.prepareStatement("select name from users where user=? and password=?")){
            preparedStatement.setString(1,sUser);
            preparedStatement.setString(2,sPassWord);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static double count(String sql) {
        try (final Connection conn = ConnectTool.getConnection();
             final Statement statement = conn.createStatement()){
            final ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next())return resultSet.getDouble(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0.0d;
    }
}
