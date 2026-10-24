module grafica.experiencial {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires static lombok;

    opens grafica.experiencial to javafx.fxml;
    exports grafica.experiencial;
    opens grafica.experiencial.view to javafx.fxml;

}