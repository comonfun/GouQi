package bind;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ItemViewModel {
    private final StringProperty typem = new SimpleStringProperty("");
    private final DoubleProperty numm = new SimpleDoubleProperty();
    private final StringProperty datem = new SimpleStringProperty("");
    private final StringProperty backupm = new SimpleStringProperty("");
    private final ItemRequestConverter converter=new ItemRequestConverter();
    private final ItemModel itemModel = new ItemModel();

    public ItemViewModel() throws IOException {
    }

    public String getTypem() {
        return typem.get();
    }

    public StringProperty typemProperty() {
        return typem;
    }

    public void setTypem(String typem) {
        this.typem.set(typem);
    }

    public double getNumm() {
        return numm.get();
    }

    public DoubleProperty nummProperty() {
        return numm;
    }

    public void setNumm(double numm) {
        this.numm.set(numm);
    }

    public String getDatem() {
        return datem.get();
    }

    public StringProperty datemProperty() {
        return datem;
    }

    public void setDatem(String datem) {
        this.datem.set(datem);
    }

    public String getBackupm() {
        return backupm.get();
    }

    public StringProperty backupmProperty() {
        return backupm;
    }

    public void setBackupm(String backupm) {
        this.backupm.set(backupm);
    }

    public void reset() {
        this.typem.set("");
        this.numm.set(0.0d);
        this.datem.set(LocalDate.now().toString().replace('-', '/'));
        this.backupm.set("");
    }

    public void add() throws SQLException {
        ItemRequest itemRequest = converter.toItemRequest(this);
        itemModel.save(itemRequest);
    }
}
