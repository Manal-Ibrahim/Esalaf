package ma.fstt.javaprojet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommandeDAO extends BaseDAO implements Initializable {
    public CommandeDAO() throws SQLException{
        super();
    }
    @FXML
    private AnchorPane commandeint;
    private Parent fxml;
    @FXML
    private Button BtnUpdate;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnCredit;

    @FXML
    private Button btnProduits;
    @FXML
    private Button btnSave;

    @FXML
    private TextField date;

    @FXML
    private TextField client;

    @FXML
    private TextField prixt;

    @FXML
    private TextArea produit;
    @FXML
    private TableView<Commande> table;

    @FXML
    private TableColumn<Commande, Integer> colid;

    @FXML
    private TableColumn<Commande, String> colidclient;

    @FXML
    private TableColumn<Commande, Float> colprixtotal;

    @FXML
    private TableColumn<Commande, String> colproduits;
    @FXML
    private TableColumn<Commande, String> coldate;
     int id;

    @FXML
    @Override
    public void clearCh(ActionEvent event) {

        clear();
    }

    @FXML
    @Override
    public void create(ActionEvent event) {
        String create = "insert into commande (client,produits,prixtotal,date) values (?,?,?,?);";
        con = BaseDAO.getCon();
        try {
            stm = con.prepareStatement(create);
           stm.setString(1,client.getText());
            stm.setString(2, produit.getText());
            stm.setFloat(3, Float.parseFloat(prixt.getText()));
            stm.setString(4, date.getText());
            stm.executeUpdate();
            clear();
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void clear() {
        client.setText(null);
        produit.setText(null);
        prixt.setText(null);
        date.setText(null);
        btnSave.setDisable(false);
    }

    public ObservableList<Commande> getCommande() {
        ObservableList<Commande> commande = FXCollections.observableArrayList();
        String query = "Select * from commande;";
        con = BaseDAO.getCon();
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Commande stm = new Commande();
                stm.setId(rs.getInt("id"));
                stm.setClient(rs.getString("client"));
                stm.setProduits(rs.getString("produits"));
                stm.setPrixtotal(rs.getFloat("prixtotal"));
                stm.setDate(rs.getString("date"));

                commande.add(stm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commande;
    }

    @FXML
    void getData(MouseEvent event) {
        Commande commande =table.getSelectionModel().getSelectedItem();
        id =commande.getId();
        client.setText(String.valueOf(commande.getClient()));
        produit.setText(commande.getProduits());
        prixt.setText(String.valueOf(commande.getPrixtotal()));
        date.setText(commande.getDate());
        btnSave.setDisable(true);

    }
    @FXML
    @Override
    public void delete(ActionEvent event) {
        String delete= "delete from commande where id=?;";
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
    @FXML
    @Override
    public void update(ActionEvent event) {
   String update= "update commande set client=?,produits=?,prixtotal=?,date=? where id=?;";
        con =BaseDAO.getCon();
        try {
            stm =con.prepareStatement(update);
            stm.setString(1,client.getText());
            stm.setString(2,produit.getText());
            stm.setFloat(3, Float.parseFloat(prixt.getText()));
            stm.setString(4,date.getText());
            stm.setInt(5,id);
            stm.executeUpdate();
            show();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        show();
    }

    @Override
    public void show() {
        ObservableList<Commande> list = getCommande();

        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
        colidclient.setCellValueFactory(new PropertyValueFactory<Commande, String>("client"));
        colproduits.setCellValueFactory(new PropertyValueFactory<Commande, String>("produits"));
        colprixtotal.setCellValueFactory(new PropertyValueFactory<Commande, Float>("prixtotal"));
        coldate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
    }
    @FXML
    void openClient()  {
        commandeint.getScene().getWindow().hide();
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
    void openCredit(ActionEvent event) {
        commandeint.getScene().getWindow().hide();
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
        commandeint.getScene().getWindow().hide();
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
    @FXML
    void retourMenu(ActionEvent event) {
        commandeint.getScene().getWindow().hide();
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
}