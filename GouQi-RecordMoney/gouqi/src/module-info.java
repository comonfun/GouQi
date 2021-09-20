module gouqi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires dataprocess;
    requires javafxblur;
    exports pages;
    opens pages to javafx.fxml;
}