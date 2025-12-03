module gruppo5.bibliosoft {
    requires javafx.controls;
    requires javafx.fxml;
    //requires gruppo5.bibliosoft;

    opens gruppo5.bibliosoft to javafx.fxml;
    opens gruppo5.bibliosoft.controller to javafx.fxml;

    exports gruppo5.bibliosoft;
    exports gruppo5.bibliosoft.controller;
}