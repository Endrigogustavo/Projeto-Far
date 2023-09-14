/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexão;

import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.*;  //Para execução do SQL

public class Conexao {
    final private String driver = "com.mysql.cj.jdbc.Driver"; //Definição do Driver MySQL
    final private String url = "jdbc:mysql://localhost/pharcom"; //acessa o bd "clientes" através do MyADMIN
    final private String usuario = "root"; //Usuária do MYSQL
    final private String senha = ""; //Senha do Mysql
    
    private Connection conexao; //variável que armazenará a conexão aberta
    public Statement statement; //Variável que executará comeandos SQL
    public ResultSet resultset; //variável que armazenará o resultado de um comando SQL
    
    public String erros = "";
    
    public boolean conecta(){
           boolean result = true;
          try{
              Class.forName(driver);
              conexao = DriverManager.getConnection(url, usuario, senha);
              
              
          }
          catch(ClassNotFoundException Driver){
           
              erros = "Driver não foi encontrado";
              result = false;
          }
          
          catch(SQLException Fonte){
                erros = "Banco de Dados Não Encontrado";
                result = false;
          }
          
            return result;
        
    }
    
    public void desconecta(){
        try{
                conexao.close();
                JOptionPane.showMessageDialog(null, "Conexão com o Banco Encerrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        
        
        }
        catch(SQLException fecha){
            JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexão com o Banco", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void executaSQL(String sql){
            try{
                statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultset = statement.executeQuery(sql);
            }
            catch(SQLException excecao){
                
            }
    }

}
