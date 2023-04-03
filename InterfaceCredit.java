package ma.fstt.javaprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceCredit extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent parent = FXMLLoader.load(Esalaf.class.getResource("/Fxml/Credit.fxml"));

        Scene scene = new Scene(parent);
        stage.setTitle("Esalaf");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
