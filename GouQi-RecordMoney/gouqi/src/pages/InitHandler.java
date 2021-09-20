package pages;


import javafx.scene.control.TableView;
import datatools.ConnectTool;

import java.sql.*;

public class InitHandler{

    private ResultSet resultSet;
//    private Collection<OneRow> data=new ArrayList();
//    private ObservableList<OneRow> data=null;
    private TableView<OneRow> tableView;

    public void initTable(int rows, int page) {

        try (final Connection connection = ConnectTool.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement("select * from details where id>? and id<=?")
        ){
            TableController.items.clear();
            final int max = getMax();
//            data.clear();
//            int start = 0;
//            if(ItemModel.max%rows==0)page-=1;
            int finalRows = 0;
            preparedStatement.setInt(1, rows*page);
            if(page==max/rows) {
                preparedStatement.setInt(2, rows*page+max%rows+1);
            }
            else preparedStatement.setInt(2, rows*page+rows);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TableController.items.add(new OneRow(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getString(4),resultSet.getString(5)));
            }
//            tableView = MainController.toModel;
//            TableController.items.addAll(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getMax(){
        int max=0;
        try (   Connection conn=ConnectTool.getConnection();
                final Statement statement = conn.createStatement()){
            final ResultSet resultSet = statement.executeQuery("select id from details order by id desc limit 1");
            if(resultSet.next())max=resultSet.getInt(1)-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return max;
    }
}