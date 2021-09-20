package pages;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OneRow {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleDoubleProperty number = new SimpleDoubleProperty();
    private final SimpleStringProperty date = new SimpleStringProperty();
    private final SimpleStringProperty mark = new SimpleStringProperty();

    public OneRow() {
        this(0,"",0.0f,"","");
    }

    public OneRow(int id, String type, double number, String date, String mark) {
        setId(id);
        setType(type);
        setNumber(number);
        setDate(date);
        setMark(mark);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public double getNumber() {
        return number.get();
    }

    public SimpleDoubleProperty numberProperty() {
        return number;
    }

    public void setNumber(double number) {
        this.number.set(number);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getMark() {
        return mark.get();
    }

    public SimpleStringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}