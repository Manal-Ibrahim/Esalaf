package ma.fstt.javaprojet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreditDAO  extends BaseDAO implements Initializable {
    public CreditDAO() throws SQLException{
        super();
    }

        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;
    @FXML
    private Button btnClient;

    @FXML
    private Button btnCommandes;


    @FXML
    private Button btnProduits;

        @FXML
        private TextField client;
        @FXML
        private TableView<Credit> table;
        @FXML
        private TableColumn<Credit, String> colclient;

        @FXML
        private TableColumn<Credit, String> coldatecredit;

        @FXML
        private TableColumn<Credit, String> coldatefin;

        @FXML
        private TableColumn<Credit, String> coletat;

        @FXML
        private TableColumn<Credit, Integer> colid;

        @FXML
        private TableColumn<Credit, Float> colprix;

        @FXML
        private AnchorPane creditint;

        @FXML
        private TextField datecredit;

        @FXML
        private TextField datefin;

        @FXML
        private TextField etat;

        @FXML
        private TextField prix;
        private Parent fxml;

        @FXML
        @Override
        public void clearCh(ActionEvent event) {
            clear();
        }
    void clear(){
        client.setText(null);
        prix.setText(null);
        datecredit.setText(null);
        datefin.setText(null);
        etat.setText(null);
        btnSave.setDisable(false);
    }

        @FXML
        @Override
        public void create(ActionEvent event) {
            String create ="insert into credit (client,prix,datecredit,datefin,etat) values (?,?,?,?,?);";
            con= BaseDAO.getCon();
            try {
                stm=con.prepareStatement(create);
                stm.setString(1,client.getText());
                stm.setFloat(2, Float.parseFloat(prix.getText()));
                stm.setString(3,datecredit.getText());
                stm.setString(4,datefin.getText());
                stm.setString(5,etat.getText());
                stm.executeUpdate();
                clear();
                show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    public ObservableList<Credit> getCredit() {
        ObservableList<Credit> credit = FXCollections.observableArrayList();
        String query = "Select * from credit;";
        con = BaseDAO.getCon();
        try {

            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Credit stm = new Credit();
                stm.setId(rs.getInt("id"));
                stm.setClient(rs.getString("client"));
                stm.setPrix(Float.valueOf(rs.getString("prix")));
                stm.setDatecredit(rs.getString("datecredit"));
                stm.setDatefin(rs.getString("datefin"));
                stm.setEtat(rs.getString("etat"));
                credit.add(stm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return credit;
    }
    @Override
    public void show()  {
        ObservableList<Credit> list =getCredit() ;

        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Credit,Integer>("id"));
        colclient.setCellValueFactory(new PropertyValueFactory<Credit,String>("client"));
        colprix.setCellValueFactory(new PropertyValueFactory<Credit,Float>("prix"));
        coldatecredit.setCellValueFactory(new PropertyValueFactory<Credit,String>("datecredit"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<Credit,String>("datefin"));
        coletat.setCellValueFactory(new PropertyValueFactory<Credit,String>("etat"));
    }
    int id;
    @FXML
    void getData(MouseEvent event) {
        Credit credit =table.getSelectionModel().getSelectedItem();
        id =credit.getId();
        client.setText(credit.getClient());
        prix.setText(String.valueOf(credit.getPrix()));
        datecredit.setText(credit.getDatecredit());
        datefin.setText(credit.getDatefin());
        etat.setText(credit.getEtat());
        btnSave.setDisable(true);
    }
        @FXML
        @Override
        public void delete(ActionEvent event) {
            String delete= "delete from credit where id=?;";
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
            String update= "update credit set client=?,prix=?,datecredit=?,datefin=?,etat=? where id=?;";
            con =BaseDAO.getCon();
            try {
                stm =con.prepareStatement(update);
                stm.setString(1,client.getText());
                stm.setFloat(2, Float.parseFloat(prix.getText()));
                stm.setString(3,datecredit.getText());
                stm.setString(4,datefin.getText());
                stm.setString(5,etat.getText());
                stm.setInt(6,id);
                stm.executeUpdate();
                show();
                clear();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @FXML
    void openClient(ActionEvent event) {
        creditint.getScene().getWindow().hide();
        Stage client= new Stage();
        try {
            fxml= FXMLLoader.load(getClass().getResource("/Fxml/Client.fxml"));
            Scene scene =new Scene (fxml);
            client.setScene(scene);
            client.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void openCommandes(ActionEvent event) {
        creditint.getScene().getWindow().hide();
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
    void openProduits(ActionEvent event) {
        creditint.getScene().getWindow().hide();
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
        creditint.getScene().getWindow().hide();
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

    }


