package bind;

import javafx.scene.control.Pagination;
import pages.InitHandler;
import pages.MainController;
import pages.OneRow;
import datatools.ConnectTool;
import java.sql.*;

import static pages.TableController.items;

public class ItemModel {

//    final private TableView<OneRow> table = MainController.toModel;
    final private InitHandler handler=new InitHandler();
    Pagination getSelector = MainController.toModleSelector;
    public int max=0;

    public ItemModel() {
    }

    public void save(ItemRequest itemRequest) throws SQLException {
        try (   Connection conn=ConnectTool.getConnection();
                PreparedStatement ps=conn.prepareStatement("insert into details values(null,?,?,?,?)")
        ) {
            final String type = itemRequest.getType();
            ps.setString(1, type);
            final Double num = itemRequest.getNum();
            ps.setDouble(2, num);
            final String date = itemRequest.getDate().replace('/', '-');
            ps.setString(3, date);
            final String mark = itemRequest.getBackup();
            ps.setString(4, mark);
            ps.execute();

            //都是从0开始的
            max = handler.getMax();
            //初始化最后一页
            final int pages = max / 25;
            if (getSelector.getPageCount()-1 < pages) {
                getSelector.setPageCount(pages + 1);
                if(pages + 1<9)
                    getSelector.setMaxPageIndicatorCount(pages + 1);

            }
            if(getSelector.getCurrentPageIndex()!= pages)
                getSelector.setCurrentPageIndex(pages);
            else
                items.add(new OneRow(max + 1, type, num, date, mark));
        }
    }
}
