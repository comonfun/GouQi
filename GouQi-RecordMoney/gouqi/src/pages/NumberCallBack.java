package pages;

import datatools.DatabaseUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class NumberCallBack implements Callback<TableColumn<OneRow, Number>, TableCell<OneRow, Number>> {
    @Override
    public TableCell<OneRow, Number> call(TableColumn<OneRow, Number> param) {

        TableCell<OneRow, Number> typeCell=new TableCell<OneRow, Number>(){
            final TextField type = new TextField();

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                this.setGraphic(null);
                this.setText(String.valueOf(this.getItem()));
            }

            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty){
                    this.setGraphic(null);
                    this.setText(String.valueOf(item));
                }
                else
                    this.setText(null);
            }

            @Override
            public void commitEdit(Number newValue) {
                super.commitEdit(newValue);
                this.setGraphic(null);
                this.setAlignment(Pos.CENTER_LEFT);
            }

            @Override
            public void startEdit() {
                super.startEdit();
                TableCell<OneRow, Number> cellt=this;
                type.setText(this.getText());
                this.setGraphic(type);
//                        TableCell cell=this;
                this.setText(null);
                type.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode()== KeyCode.ENTER){
                            final String value = type.getText();
                            if(Double.valueOf(value).equals(cellt.getItem())) {
                                cellt.setGraphic(null);
                                cellt.setText(value);
                                return;
                            }
                            cellt.commitEdit(Double.valueOf(value));
                            final int id = cellt.getTableRow().getItem().getId();
                            DatabaseUtils.updata(id,"number", Double.valueOf(value));
                        }
                    }
                });
            }
        };
        return typeCell;
    }
}
