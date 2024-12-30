
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Rudy Rogers
 */
public class conectaDAO {
    
    private static final String URL = "jdbc:mysql://localhost:3306/uc11?" + "autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static Connection conn;
    
    public static Connection getConexao(){
        Connection conn = null;
        
        try {
        
            if( conn == null || conn.isClosed()){
                        conn = DriverManager.getConnection(URL, USER, PASSWORD);
                        //return conn;
                }
                return conn;
                
                
        } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
            }
    }
    
}
