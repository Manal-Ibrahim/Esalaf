package ma.fstt.javaprojet;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitsDAO extends BaseDAO implements Initializable {
    public ProduitsDAO() throws SQLException{
        super();
    }
        @FXML
        private Button bntClient;

        @FXML
        private Button btnClear;

        @FXML
        private Button btnCommandes;

        @FXML
        private Button btnCredit;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<Produits, Integer> colid;

        @FXML
        private TableColumn<Produits, String> colnom;

        @FXML
        private TableColumn<Produits, Float> colprix;

        @FXML
        private TableColumn<Produits, Integer> colquantite;

        @FXML
        private TextField nom;

        @FXML
        private TextField prix;

        @FXML
        private AnchorPane produitint;

        @FXML
        private TextField quantite;

        @FXML
        private TableView<Produits> table;
        private Parent fxml;

        @FXML
        void openClient(ActionEvent event) {
            produitint.getScene().getWindow().hide();
            Stage prod= new Stage();
            try {
                fxml= FXMLLoader.load(getClass().getResource("/Fxml/Client.fxml"));
                Scene scene =new Scene (fxml);
                prod.setScene(scene);
                prod.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void openCommande(ActionEvent event) {
            produitint.getScene().getWindow().hide();
            Stage prod= new Stage();
            try {
                fxml= FXMLLoader.load(getClass().getResource("/Fxml/Commande.fxml"));
                Scene scene =new Scene (fxml);
                prod.setScene(scene);
                prod.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        @FXML
        void openCredit(ActionEvent event) {
                produitint.getScene().getWindow().hide();
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
    void retourMenu(ActionEvent event) {
        produitint.getScene().getWindow().hide();
        Stage menu= new Stage();
        try {
            fxml= FXMLLoader.load(getClass().getResource("/Fxml/Menu.fxml"));
            Scene scene =new Scene (fxml);
            menu.setScene(scene);
            menu.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
    }

    @Override
    public void create(ActionEvent event) throws SQLException {
        String create = "insert into produit (nom,quantitestock,prix) values (?,?,?);";
        con = BaseDAO.getCon();
        try {
            stm = con.prepareStatement(create);
            stm.setString(1,nom.getText());
            stm.setInt(2, Integer.parseInt(prix.getText()));
            stm.setFloat(3, Float.parseFloat(prix.getText()));

            stm.executeUpdate();
            clear();
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Produits> getProduit() {
        ObservableList<Produits>  produit = FXCollections.observableArrayList();
        String query = "Select * from produit;";
        con = BaseDAO.getCon();
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Produits stm = new Produits();
                stm.setId(rs.getInt("id"));
                stm.setNom((rs.getString("nom")));
                stm.setQuantitestock(Integer.parseInt(rs.getString("quantitestock")));
                stm.setPrix(rs.getFloat("prix"));


                produit.add(stm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produit;
    }
    int id;
    @FXML
    void getData(MouseEvent event) {
        Produits produit =table.getSelectionModel().getSelectedItem();
        id =produit.getId();
        nom.setText(String.valueOf(produit.getNom()));
        quantite.setText(String.valueOf(produit.getQuantitestock()));
        prix.setText(String.valueOf(produit.getPrix()));
        btnSave.setDisable(true);

    }


    @Override
    public void delete(ActionEvent event) throws SQLException {
        String delete= "delete from produit where id=?;";
        con= BaseDAO.getCon();
        try {
            stm= con.prepareStatement(delete);
            stm.setInt(1,id);
            stm.executeUpdate();
            show();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        String update= "update produit set nom=?,quantitestock=?,prix=? where id=?;";
        con =BaseDAO.getCon();
        try {
            stm =con.prepareStatement(update);
            stm.setString(1,nom.getText());
            stm.setInt(2, Integer.parseInt(quantite.getText()));
            stm.setFloat(3, Float.parseFloat(prix.getText()));
            stm.setInt(4,id);
            stm.executeUpdate();
            show();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCh(ActionEvent event) {
        clear();
    }
    void clear() {
        nom.setText(null);
        quantite.setText(null);
        prix.setText(null);
        btnSave.setDisable(false);
    }

    @Override
    public void show() {

        ObservableList<Produits> list = getProduit();

        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Produits, String>("nom"));
        colquantite.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("quantitestock"));
        colprix.setCellValueFactory(new PropertyValueFactory<Produits, Float>("prix"));
    }
}
