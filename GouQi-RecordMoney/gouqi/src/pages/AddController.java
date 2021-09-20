package pages;

import bind.ItemViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    private TextField type;
    @FXML
    private TextField num;

    @FXML
    private DatePicker date;

    @FXML
    private TextField backup;

    @FXML
    private Button add;

    private ItemViewModel viewModel=new ItemViewModel();

    public AddController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add.setDefaultButton(true);
        type.textProperty().bindBidirectional(viewModel.typemProperty());
            Bindings.bindBidirectional(num.textProperty(),viewModel.nummProperty(),new NumberStringConverter(){
                @Override
                public Number fromString(String value) {
                    if(value.equals("-"))
                        return 0;
                    return super.fromString(value);
                }
            });

        backup.textProperty().bindBidirectional(viewModel.backupmProperty());
        date.setEditable(false);
        date.valueProperty().set(LocalDate.now());
        Bindings.bindBidirectional(viewModel.datemProperty(),date.valueProperty(),new LocalDateStringConverter());
    }

    public void add(ActionEvent actionEvent) throws SQLException {
        viewModel.add();
    }

    public void reset(ActionEvent actionEvent) {
        viewModel.reset();
    }
}
