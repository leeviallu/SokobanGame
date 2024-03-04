module org.example.pulmapeli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.pulmapeli to javafx.fxml;
    exports org.example.pulmapeli;
}