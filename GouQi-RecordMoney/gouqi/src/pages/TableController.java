package pages;

import datatools.DatabaseUtils;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


public class TableController implements Initializable {
    @FXML
    private TableColumn operate;
    @FXML
    private TableView<OneRow> table_view;

    @FXML
    private TableColumn<OneRow, Number> xuhao;
    @FXML
    private TableColumn<OneRow, String> type;

    @FXML
    private TableColumn<OneRow, Number> num;

    @FXML
    private TableColumn<OneRow, String> date;

    @FXML
    private TableColumn<OneRow, String> mark;
    private InitHandler handler=new InitHandler();

    public static ObservableList<OneRow> items=null;
    public static TableCell toMain=null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xuhao.setEditable(false);
        xuhao.setSortable(false);
        type.setSortable(false);
        num.setSortable(false);
        date.setSortable(false);
        mark.setSortable(false);
        items = table_view.getItems();
        table_view.setEditable(true);
        operate.setCellFactory(new Callback<TableColumn, TableCell>() {

            @Override
            public TableCell call(TableColumn param) {

                final TableCell<TableColumn, Number> objectObjectTableCell = new TableCell<TableColumn, Number>(){

                    private HBox hBox;

                    @Override
                    protected void updateItem(Number item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty){
                            hBox = new HBox();
                            ImageView delete=new ImageView(new Image(getClass().getResource("/resources/trash_15px.png").toExternalForm()));
                            ImageView edit=new ImageView(new Image(getClass().getResource("/resources/edit_file_15px.png").toExternalForm()));
                            delete.setId("delete");
//                            edit.setId("edit");
//                            Text tip=new Text("(禁用编辑)");
                            Label label = new Label("(开启或禁用编辑)",edit);
                            label.setId("edit");
                            hBox.getChildren().addAll(delete,label);

                            class BothHandler implements EventHandler<MouseEvent>{
                                boolean lastPage;
                                @Override
                                public void handle(MouseEvent event) {
                                    final Node source = (Node) event.getSource();
                                    switch (source.getId()){
                                        case "delete":
//                                            if(!MainController.toLogin.getText().equals("管理员"))return;
                                            final TableCell w = (TableCell) delete.getParent().getParent();
                                            int id = items.get(w.getIndex()).getId();
                                            DatabaseUtils.delete(id);
                                            final int max = handler.getMax();
                                            if(MainController.toModleSelector.getPageCount()-1>max/25){
                                                if(MainController.toModleSelector.getCurrentPageIndex()==MainController.toModleSelector.getPageCount()-1)
                                                    lastPage=true;
                                                MainController.toModleSelector.setPageCount(max/25+1);
                                                if(lastPage)
                                                    MainController.toModleSelector.setCurrentPageIndex(max/25);
                                            }
                                            items.clear();
                                            handler.initTable(25,MainController.toModleSelector.getCurrentPageIndex());
                                            break;
                                        case "edit":
//                                            if(!MainController.toLogin.getText().equals("管理员"))return;
                                            table_view.setEditable(!table_view.isEditable());
                                    }
                                }
                            }

                            final BothHandler bothHandler = new BothHandler();
                            delete.setOnMouseClicked(bothHandler);
                            label.setOnMouseClicked(bothHandler);
                            hBox.setSpacing(10);
                            hBox.setAlignment(Pos.CENTER);
                            this.setGraphic(hBox);
                        }
                        else this.setGraphic(null);
                    }
                };
                return objectObjectTableCell;
            }
        });
        final StringCallBack stringCallBack = new StringCallBack();
        type.setCellFactory(stringCallBack);
        num.setCellFactory(new NumberCallBack());
        date.setCellFactory(new DateCallBack());
        mark.setCellFactory(stringCallBack);
    }
}
