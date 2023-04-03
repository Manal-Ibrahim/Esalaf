package ma.fstt.javaprojet;

import javafx.event.ActionEvent;

import java.sql.*;

public abstract class BaseDAO {
   protected Connection con = null ;
    protected PreparedStatement stm = null;
    protected ResultSet rs = null;
     static String url = "jdbc:mysql://127.0.0.1:3306/esalaf";
    static String user="root";
    static String password ="";
    public static Connection getCon() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return con;
    }
    public abstract void create(ActionEvent event)throws SQLException;
    public abstract void delete(ActionEvent event)throws SQLException;
    public abstract void update(ActionEvent event)throws SQLException;
    public abstract void clearCh(ActionEvent event) ;
    public abstract  void show();
}
