/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.sql.*;
import java.text.*;
import javax.swing.*;


import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Categoria extends JFrame {
    Conexao con_cliente;
    JLabel rCodigo, rNome, rEmail, rTel, rData, rPesquisar, imagem;
    JTextField tCodigo, tNome, tEmail, tTel, tData,tPesquisar, tEmail2, tEmail3, tEmail4;
    
    
    JTable tblClientes;
    JScrollPane scp_tabela;
    
     
    
    public Categoria() throws SQLException, ParseException{

        Container tela = getContentPane();
        
        rCodigo = new JLabel("Codigo");
        rNome = new JLabel("Nome");
        rEmail = new JLabel("Email");
        rTel = new JLabel("Telefone");
        rData = new JLabel("Data");
        rPesquisar = new JLabel("Pesquisar cliente");
        tCodigo = new JTextField();
        tNome = new JTextField();
        tEmail= new JTextField();
        tTel = new JTextField();
        tData= new JTextField();
        tPesquisar= new JTextField();
        tEmail2= new JTextField();
        tEmail3= new JTextField();
        tEmail4= new JTextField();

        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Conexao Java com Mysql");
        setResizable(false);
        tela.setLayout(null);
        
       
                   
        rPesquisar.setBounds(50, 320, 200, 50);
        tPesquisar.setBounds(190, 335, 250, 20);
        

                
        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();

        tblClientes.setBounds(50,400,900,350);
        scp_tabela.setBounds(50,400,900,350);
        

        
        tela.add(tblClientes);
        tela.add(scp_tabela);

        
        
        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
        
        tblClientes.setFont(new java.awt.Font("Arial",1,12)); 
        
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][]{
            {null, null},
            {null, null},
            {null, null},
            {null, null},
        },
                new String [] {"Id _Categoria", "Descrição"})
        {
            boolean[] canEdit = new boolean[]{
                false, false
            };
          
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
});          
        scp_tabela.setViewportView(tblClientes); 
        tblClientes.setAutoCreateRowSorter(true);

        setSize(1000,650);
        setVisible(true);
        setLocationRelativeTo(null);

        
        con_cliente.executaSQL("select * from categoria order by Id _Categoria");
        preencherTabela();

 }
      public void mostrar_Dados(){
        try{
            tCodigo.setText(con_cliente.resultset.getString("Id _Categoria"));
            tNome.setText(con_cliente.resultset.getString("Descrição"));
            
        }catch(SQLException erro){
             JOptionPane.showMessageDialog(null, "Não localizou dados: "+erro,"Mensagem do prograna", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    public void preencherTabela() throws SQLException
    {
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(550);
 
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);
        
        try{
            con_cliente.resultset.beforeFirst();
            while(con_cliente.resultset.next()){
                modelo.addRow(new Object[]{
                con_cliente.resultset.getString("Id _Categoria"),
                con_cliente.resultset.getString("Descrição"), 
            });
            }
        }catch(SQLException erro){
    JOptionPane.showMessageDialog(null,"erro ao listar dados da tabela!! \n "+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
}
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
