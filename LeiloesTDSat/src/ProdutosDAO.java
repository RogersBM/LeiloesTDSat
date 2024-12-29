
/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/uc11";
        String user = "root";
        String password = "123456";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    
    }
    
    public void cadastrar (ProdutosDTO produto)throws Exception {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getNome());
                stmt.setInt(2, produto.getValor());
                stmt.setString(3, produto.getStatus());
                stmt.executeUpdate();
        }
        
               
    }
    
    public ArrayList<ProdutosDTO> listarProdutos()throws Exception{
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                produtos.add(produto);
            }
        }
        return produtos;
    }
    
    public void venderProduto (ProdutosDTO p){
        // .....
    }
}

