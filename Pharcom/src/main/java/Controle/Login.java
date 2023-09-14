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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Admin
 */
public class Login extends JFrame {

    Conexao con_cliente;

    JLabel Login, Senha1;
    JTextField Nome;
    JButton Logar;
    JPasswordField Senha;

    public Login() {
        Container tela = getContentPane();

        con_cliente = new Conexao();
        con_cliente.conecta();

        setTitle("Conexao Java com Mysql");
        setResizable(false);
        tela.setLayout(null);

        Login = new JLabel("Login");
        Senha1 = new JLabel("Senha");
        Nome = new JTextField();
        Senha = new JPasswordField();
        Logar = new JButton("Logar");

        Login.setBounds(130, 80, 150, 30);
        Senha1.setBounds(130, 180, 150, 30);
        Nome.setBounds(130, 105, 220, 30);
        Senha.setBounds(130, 205, 220, 30);
        Logar.setBounds(165, 305, 150, 30);

        Logar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
   
                    String pesquisa = "select * from funcionário where Usuário like '" +Nome.getText() + "' && Senha = " +Senha.getText() + "";
                    con_cliente.executaSQL(pesquisa);
                    
                    try {
                        if(con_cliente.resultset.first()){
                           MenuAdm Adm = new MenuAdm();
                            Adm.setVisible(true);
                            dispose();
                        }else{
                    String pesquisa2 = "select * from cliente where Usuário like '" +Nome.getText() + "' && Senha = " +Senha.getText() + "";
                    con_cliente.executaSQL(pesquisa2);
                    if(con_cliente.resultset.first()){
                           MenuUser User = new MenuUser();
                            User.setVisible(true);
                            dispose();
                        }else{
                    JOptionPane.showMessageDialog(null, "Erro");
                    }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                    


            
              
            }
        });
        tela.add(Nome);
        tela.add(Senha);
        tela.add(Logar);
        tela.add(Senha1);
        tela.add(Login);

        setSize(500, 450);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String args[]) {

        Login app = new Login();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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
