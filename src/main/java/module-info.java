module com.example.chesspiece3d {
    requires javafx.controls;
    requires javafx.fxml;
    requires jimObjModelImporterJFX;
    requires java.desktop;


    opens com.example.chesspiece3d to javafx.fxml;
    exports com.example.chesspiece3d;
}