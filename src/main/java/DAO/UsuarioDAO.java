/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Obejtos.Usuario;
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
public class UsuarioDAO {
    public List<Usuario> read(){
        Connection con = Conexao.getConnection();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                List<Usuario> produtos = new ArrayList<>();
                
                try {
                    stmt = con.prepareStatement("SELECT * FROM tbl_usuarios");
                    rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        Usuario p = new Usuario();
                        p.setId(rs.getInt("id"));
                        p.setNome(rs.getString("nome"));
                        p.setLogin(rs.getString("login"));
                        p.setSenha(rs.getString("senha"));
                        p.setTipo(rs.getString("tipo"));
                        produtos.add(p);
                    }
                    
                }catch (SQLException e){
                    
                    JOptionPane.showMessageDialog(null, "falha ao obter os dados" + e);
                    
                }finally {
                    Conexao.closeConnection(con, stmt, rs);
                }
                return produtos;
    }
    public void create(Usuario u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_usuarios(descricao, valor, quantidade) VALUES (?,?,?,?)");
            stmt.setString(1, u.getNome());  
            stmt.setString(3, u.getLogin());
            stmt.setString(4, u.getSenha());
            stmt.setString(5, u.getTipo());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }
    public void update(Usuario u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbl_usuarios SET nome = ?, login = ?, senha = ?, tipo = ? WHERE id = ? ");
            stmt.setString(1, u.getNome());  
            stmt.setString(3, u.getLogin());
            stmt.setString(4, u.getSenha());
            stmt.setString(5, u.getTipo());
            stmt.setInt(6, u.getId());
           
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }
    
       public void delete(Usuario U){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbl_produto WHERE id = ?");
            stmt.setInt(1, U.getId());
           
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        }finally {
            Conexao.closeConnection(con, stmt);
        }
        
    }
}
