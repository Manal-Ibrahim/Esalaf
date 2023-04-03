package ma.fstt.javaprojet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btnClient;

    @FXML
    private Button btnCommandes;

    @FXML
    private Button btnCredit;

    @FXML
    private Button btnProduits;

    @FXML
    private AnchorPane esalaf;
    private Parent fxml;


    @FXML
    void openClient()  {
        esalaf.getScene().getWindow().hide();
        Stage commd= new Stage();
        try {
            fxml= FXMLLoader.load(getClass().getResource("/Fxml/Client.fxml"));
            Scene scene =new Scene (fxml);
            commd.setScene(scene);
            commd.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void openCommande()  {
        esalaf.getScene().getWindow().hide();
        Stage commd= new Stage();
        try {
            fxml= FXMLLoader.load(getClass().getResource("/Fxml/Commande.fxml"));
            Scene scene =new Scene (fxml);
            commd.setScene(scene);
            commd.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void openCredit(ActionEvent event) {
        esalaf.getScene().getWindow().hide();
        Stage credit= new Stage();
        try {
            fxml= FXMLLoader.load(getClass().getResource("/Fxml/Credit.fxml"));
            Scene scene =new Scene (fxml);
            credit.setScene(scene);
            credit.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void openProduits(ActionEvent event) {
        esalaf.getScene().getWindow().hide();
        Stage prods= new Stage();
        try {
            fxml= FXMLLoader.load(getClass().getResource("/Fxml/Produits.fxml"));
            Scene scene =new Scene (fxml);
            prods.setScene(scene);
            prods.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
