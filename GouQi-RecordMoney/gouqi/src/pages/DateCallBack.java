package pages;

import datatools.DatabaseUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.time.LocalDate;

public class DateCallBack implements Callback<TableColumn<OneRow, String>, TableCell<OneRow, String>> {
    @Override
    public TableCell<OneRow, String> call(TableColumn<OneRow, String> param) {

        TableCell<OneRow, String> dateCell=new TableCell<>(){
            final DatePicker date=new DatePicker();

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                this.setGraphic(null);
                this.setText(this.getItem());
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty){
                    this.setGraphic(null);
                    this.setText(item);
                }
                else
                    this.setText(null);
            }

            @Override
            public void commitEdit(String newValue) {
                super.commitEdit(newValue);
                this.setGraphic(null);
                this.setAlignment(Pos.CENTER_LEFT);
            }

            @Override
            public void startEdit() {
                super.startEdit();
                TableCell<OneRow, String> cellt=this;
                date.setEditable(false);
                date.valueProperty().set(LocalDate.now());
                this.setGraphic(date);

                this.setText(null);
                date.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode()== KeyCode.ENTER){
                            final String value = date.valueProperty().getValue().toString().replace('/','-');
                            if(value.equals(cellt.getItem())) {
                                cellt.setGraphic(null);
                                cellt.setText(value);
                                return;
                            }
                            cellt.commitEdit(value);
                            final int id = cellt.getTableRow().getItem().getId();
                            String field="date";
                            DatabaseUtils.updata(id,field, value);
                        }
                    }
                });
            }
        };
        return dateCell;
    }
}