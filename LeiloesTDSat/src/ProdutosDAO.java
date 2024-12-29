
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
    
    public void venderProduto (int id){
        String sql = "UPDATE produtos SET status = ? WHERE id =?";
        
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, "Vendido");
            stmt.setInt(2, id);
            int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com o ID informado.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws Exception {
            ArrayList<ProdutosDTO> listaVendidos = null;
            return listaVendidos;
//              ArrayList<ProdutosDTO> produtos = new ArrayList<>();
//            String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = ?";
//            try (Connection conn = getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {
//                stmt.setString(1, "Vendido");
//                try (ResultSet rs = stmt.executeQuery()) {
//                    while (rs.next()) {
//                        ProdutosDTO produto = new ProdutosDTO();
//                        produto.setId(rs.getInt("id"));
//                        produto.setNome(rs.getString("nome"));
//                        produto.setValor(rs.getInt("valor"));
//                        produto.setStatus(rs.getString("status"));
//                        produtos.add(produto);
            
        }
    }
        //return produtos;
    


