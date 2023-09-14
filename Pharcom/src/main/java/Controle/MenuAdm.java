/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Admin
 */
public class MenuAdm extends JFrame {
    Conexao con_cliente;
   
    JButton sair,sair1,sair2;
    public MenuAdm() throws SQLException, ParseException{

        Container tela = getContentPane();

        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Conexao Java com Mysql");
        setResizable(false);
        tela.setLayout(null);
        sair = new JButton ("Funcionarios");
        sair1 = new JButton ("Categorias");
        sair2 = new JButton ("Cliente");
 
        sair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Funcionarios fun = new Funcionarios();
                    fun.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
                sair2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    cliente cli = new cliente();
                    cli.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
              
                
                sair.setBounds(750, 335,150, 30);
                tela.add(sair);
                
                sair1.setBounds(600, 335,150, 30);
                tela.add(sair1);
                
                sair2.setBounds(450, 335,150, 30);
                tela.add(sair2);
       
        setSize(1000,650);
        setVisible(true);
        setLocationRelativeTo(null);

        

    }
    
public static void main(String args[]) throws SQLException, ParseException{
    
try {
		    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {

		    System.out.println("Erro: " + e.getMessage());
		    e.printStackTrace();

		} catch (ClassNotFoundException e) {

			System.out.println("Erro: " + e.getMessage());
		    e.printStackTrace();

		} catch (InstantiationException e) {

			System.out.println("Erro: " + e.getMessage());
		    e.printStackTrace();

		} catch (IllegalAccessException e) {

			System.out.println("Erro: " + e.getMessage());
		    e.printStackTrace();
		}
 
}

}
