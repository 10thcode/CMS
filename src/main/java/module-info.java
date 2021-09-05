module com.app.cms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.cms.controllers to javafx.fxml;
    exports com.app.cms;
}