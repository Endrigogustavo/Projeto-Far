/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexão;

import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class Conexao {

    public static boolean statament(String pesquisa2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/clientes";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public Statement statament;
    public ResultSet resultset;
    
    public boolean conecta() {
    boolean result = true;
    
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,usuario,senha);
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver nao localizado"+Driver);
            result = false;
        }   catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Fonte de dados nao encontrada"+Fonte);
            result = false;
        }
        return result;
    }
    
    public void desconectar(){
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Conexao com o banco fechada");
        } catch (SQLException fecha) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar");
        }
    }
    
    public void execultarSQL(String sql){
        try {
            statament = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statament.executeQuery(sql);
        } catch (SQLException excecao) {
            JOptionPane.showMessageDialog(null, "Erro no comando sql"+excecao);
        }
    }
}
