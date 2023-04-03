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

public class ClientDAO extends BaseDAO implements Initializable {

public ClientDAO() throws SQLException{
    super();
}
    @FXML
    private Button BtnUpdate;
    @FXML
    private AnchorPane clientint;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnCommande;
    @FXML
    private Button btnCredit;
    @FXML
    private Button btnProduits;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<Client, Integer> colid;

    @FXML
    private TableColumn<Client, String> colnom;

    @FXML
    private TableColumn<Client,String > colprenom;

    @FXML
    private TableColumn<Client, String> coltele;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TableView<Client> table;

    @FXML
    private TextField telephone;
    int id;
    private Parent fxml;

    @FXML
    @Override
    public void clearCh(ActionEvent event) {
        clear();
    }

    @FXML
    @Override
    public void create(ActionEvent event) {
        String create ="insert into client (nom,prenom,telephone) values (?,?,?);";
        con= BaseDAO.getCon();
        try {
            stm=con.prepareStatement(create);
            stm.setString(1,nom.getText());
            stm.setString(2,prenom.getText());
            stm.setString(3,telephone.getText());
            stm.executeUpdate();
            clear();
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
    @FXML
    void getData(MouseEvent event) {
        Client client =table.getSelectionModel().getSelectedItem();
        id =client.getId();
        nom.setText(client.getNom());
        prenom.setText(client.getPrenom());
        telephone.setText(client.getTelephone());
        btnSave.setDisable(true);
    }

    @FXML
    @Override
    public void delete(ActionEvent event) {
    String delete= "delete from client where id=?;";
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
        String update= "update client set nom=?,prenom=?,telephone=? where id=?;";
        con =BaseDAO.getCon();
        try {
            stm =con.prepareStatement(update);
            stm.setString(1,nom.getText());
            stm.setString(2,prenom.getText());
            stm.setString(3,telephone.getText());
            stm.setInt(4,id);
            stm.executeUpdate();
            show();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    void clear(){
        nom.setText(null);
        prenom.setText(null);
        telephone.setText(null);
        btnSave.setDisable(false);
    }

    @FXML
    void openCommande()  {
        clientint.getScene().getWindow().hide();
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
        clientint.getScene().getWindow().hide();
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
        clientint.getScene().getWindow().hide();
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
        clientint.getScene().getWindow().hide();
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
    public ObservableList<Client> getClient() {
        ObservableList<Client> client = FXCollections.observableArrayList();
        String query = "Select * from client;";
        con = BaseDAO.getCon();
        try {

            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Client stm = new Client();
                stm.setId(rs.getInt("id"));
                stm.setNom(rs.getString("nom"));
                stm.setPrenom(rs.getString("prenom"));
                stm.setTelephone(rs.getString("telephone"));
                client.add(stm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return client;
    }
    @Override
    public void show()  {
        ObservableList<Client>  list =getClient() ;

        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        coltele.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
    }

}
