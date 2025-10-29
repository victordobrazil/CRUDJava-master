/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Obejtos.Produto;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author victor.haalves2
 */
public class ProdutoDAO {
    public List<Produto> read(){
        Connection con = Conexao.getConnection();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                List<Produto> produtos = new ArrayList<>();
                
                try {
                    stmt = con.prepareStatement("SELECT * FROM tbl_produto");
                    rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Produto p = new Produto();
                        p.setId(rs.getInt("id"));
                        p.setDescricao(rs.getString("descricao"));
                        p.setValor(rs.getDouble("valor"));
                        p.setQuantidade(rs.getInt("quantidade"));
                        produtos.add(p);
                    }
                    
                }catch (SQLException e){
                    
                    JOptionPane.showMessageDialog(null, "falha ao obter os dados" + e);
                    
                }finally {
                    Conexao.closeConnection(con, stmt, rs);
                }
                return produtos;
    }
    public void create(Produto p){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_produto(descricao, valor, quantidade) VALUES (?,?,?)");
            stmt.setString(1, p.getDescricao());  
             stmt.setDouble(2, p.getValor());
            stmt.setDouble(3, p.getQuantidade());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }
    public void update(Produto p){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbl_produto SET descricao = ?, quantidade = ?, valor = ? WHERE id = ? ");
            stmt.setString(1, p.getDescricao());  
            stmt.setDouble(2, p.getQuantidade());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getId());
           
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }
}
