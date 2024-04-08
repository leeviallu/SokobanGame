/**
 * Moduulivaatimukset
 */
module org.example.sokoban {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.sokoban to javafx.fxml;
    exports org.example.sokoban;
}