/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.*;
import javax.swing.*;


import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Remedio extends JFrame { 
    Conexao con_cliente;
    JLabel rCodigo, rNome,rCategoria,rPreco,rEstoque,rPesquisar,rDes;
    JTextField tCodigo, tNome,tCategoria,tPreco,tEstoque, tPesquisar,tDes;
    JButton primeiro, anterior, proximo, ultimo, registro, gravar, alterar, excluir,pesquisar,sair;
    
    JTable tblClientes;
    JScrollPane scp_tabela;
    
     
    
    public Remedio() throws SQLException, ParseException{

        Container tela = getContentPane();
        
        rCodigo = new JLabel("Codigo");
        rNome = new JLabel("Remedio");
        rCategoria = new JLabel("Categoria");
        rPreco = new JLabel("Preço");
        rEstoque = new JLabel("Estoque");
        rPesquisar = new JLabel("Pesquisar");
        rDes = new JLabel("Descrição");
        tCodigo = new JTextField();
        tNome = new JTextField();
        tCategoria= new JTextField();
        tPreco = new JTextField();
        tEstoque= new JTextField();
        tPesquisar= new JTextField();
        tDes= new JTextField();



        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Remedio");
        setResizable(false);
        tela.setLayout(null);
        
        primeiro = new JButton ("Primeiro");
        anterior = new JButton ("Anterior");
        proximo = new JButton ("Próximo");
        ultimo = new JButton ("Último");
        
        registro = new JButton ("Nova Registro");
        gravar = new JButton ("Gravar");
        alterar = new JButton ("Alterar");
        excluir = new JButton ("Excluir");
        pesquisar = new JButton ("Pesquisar");
        sair = new JButton ("Sair");
        
         sair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
         
        primeiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.first();
                    mostrar_Dados();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        anterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    if(con_cliente.resultset.isFirst()){
                    JOptionPane.showMessageDialog(null, "Ja esta no primeiro registro");
                    }else{
                    con_cliente.resultset.previous();
                    mostrar_Dados();
                    }
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        
        proximo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                     if(con_cliente.resultset.isLast()){
                    JOptionPane.showMessageDialog(null, "Ja esta no ultimo registro");
                    }else{
                    con_cliente.resultset.next();
                    mostrar_Dados();
                    }
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        ultimo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.last();
                    mostrar_Dados();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        registro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tCodigo.setText("");
                tEstoque.setText("");
                tCategoria.setText("");
                tNome.setText("");
                tPreco.setText("");
                tDes.setText("");
            }
        });
        
        gravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tNome.getText();
                String dt = tEstoque.getText();
                String tel = tPreco.getText();
                String email = tCategoria.getText();
                String des = tDes.getText();
                try{
                 String insert_sql = "insert into remedio(Nome_Rem,Categoria_Num,Preço,Estoque,Descrição)values('"+nome+"','"+tel+"','"+email+"','"+dt+"','"+des+"')";
                 con_cliente.statement.executeUpdate(insert_sql);
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                 
                 con_cliente.executaSQL("select * from remedio order by Id_Rem");
                 preencherTabela();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel gravar registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        alterar.addActionListener(new ActionListener(){           
            public void actionPerformed(ActionEvent e){
                String nome = tNome.getText();
                String dt = tEstoque.getText();
                String tel = tPreco.getText();
                String email = tCategoria.getText();
                String des = tDes.getText();
                String sql;
                String msg="";
               
                try {
                    if (tCodigo.getText().equals("")) {
                       sql = "insert into remedio(Nome_Rem,Categoria_Num,Preço,Estoque,Descrição)values('"+nome+"','"+tel+"','"+email+"','"+dt+"','"+des+"')";
                       msg="Gravado com sucesso";
                    }
                    else{
                        sql = "update remedio set Nome_Rem='"+nome+"',Categoria_Num='"+email+"',Preço='"+tel+"',Estoque='"+dt+"',Descrição='"+des+"'where Id_Rem="+tCodigo.getText();
                        msg="Alterado com sucesso";
                    }
                    
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                    con_cliente.executaSQL("select * from remedio order by Id_Rem ");
                    preencherTabela();
                    
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar");
                }
            }
        });
        
       
         excluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String sql;
                
                try {
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir?");
                    if (resposta==0) {
                      sql = "delete from remedio where Id_Rem = " +tCodigo.getText();
                      int excluir = con_cliente.statement.executeUpdate(sql);                   
                    if(excluir==1){
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        con_cliente.executaSQL("select * from remedio order by Id_Rem");
                        con_cliente.resultset.first();
                        preencherTabela();
                        //posicionarRegistro();
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "Operaçao cancelada pelo usuario");
                    }
                }
                    

             
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "Erro ao deletar");
                }
            }
        });
         
           pesquisar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    String pesquisa = "select * from remedio where Nome_Rem like'"+tPesquisar.getText()+"%'";
                    con_cliente.executaSQL(pesquisa);
                    if(con_cliente.resultset.first()){
                    preencherTabela();
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "\n Nao existe");
                    }
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Dados nao encontrados");
                }
            }
        });
                
     
                primeiro.setBounds(60, 320,100, 30);
                tela.add(primeiro);
                anterior.setBounds(150, 320,100, 30);
                tela.add(anterior);
                proximo.setBounds(240, 320,100, 30);
                tela.add(proximo);
                ultimo.setBounds(330, 320,100, 30);
                tela.add(ultimo);
                
                
                registro.setBounds(500, 320,130, 30);
                tela.add(registro);
                gravar.setBounds(635, 320,100, 30);
                tela.add(gravar);
                alterar.setBounds(730, 320,100, 30);
                tela.add(alterar);
                excluir.setBounds(830, 320,100, 30);
                tela.add(excluir);
                
                pesquisar.setBounds(60, 355,150, 30);
                tela.add(pesquisar);
                
                sair.setBounds(750, 355,150, 30);
                tela.add(sair);
                
               
        rPesquisar.setBounds(50, 320, 200, 50);
        tPesquisar.setBounds(220, 355, 250, 30);
        

                
        
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
            {null, null,null,null,null,null},
            {null, null,null,null,null,null},
            {null, null,null,null,null,null},
            {null, null,null,null,null,null},
        },
                new String [] {"Id_Rem", "Nome_Rem", "Categoria_Num", "Preço", "Estoque", "Descrição"})
        {
            boolean[] canEdit = new boolean[]{
                false, false,false,false,false,false
            };
          
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
});          
        scp_tabela.setViewportView(tblClientes); 
        tblClientes.setAutoCreateRowSorter(true);

       
        rCodigo.setBounds(50, 40, 150, 50);
        rNome.setBounds(50, 80, 150, 50);
        rCategoria.setBounds(50, 120, 150, 50);
        rPreco.setBounds(50, 160, 150, 50);
        rEstoque.setBounds(50, 200, 150, 50);
        rDes.setBounds(50, 250, 150, 50);
        
        rCodigo.setForeground(Color.white);
        rNome.setForeground(Color.white);
        rCategoria.setForeground(Color.white);
        rPreco.setForeground(Color.white);
        rEstoque.setForeground(Color.white);
        rPesquisar.setForeground(Color.white);
        rDes.setForeground(Color.white);
        
        rCodigo.setFont(new Font("Tahoma",Font.BOLD,15));
        rNome.setFont(new Font("Tahoma",Font.BOLD,15));
        rCategoria.setFont(new Font("Tahoma",Font.BOLD,15));
        rPreco.setFont(new Font("Tahoma",Font.BOLD,15));
        rEstoque.setFont(new Font("Tahoma",Font.BOLD,15));
        rPesquisar.setFont(new Font("Tahoma",Font.BOLD,15));
        rDes.setFont(new Font("Tahoma",Font.BOLD,15));
        
        tCodigo.setBounds(130, 50, 80, 30);
        tNome.setBounds(130, 90, 220, 30);
        tCategoria.setBounds(130, 130, 200, 30);
        tPreco.setBounds(130, 170, 100, 30);
        tEstoque.setBounds(130, 210, 80, 30);
        tDes.setBounds(130, 250, 250, 60);
        
        
        tela.add(tCategoria);
        tela.add(tCodigo);
        tela.add(tNome);
        tela.add(tPreco);
        tela.add(tEstoque);
        tela.add(rCodigo);
        tela.add(rNome);
        tela.add(rCategoria);
        tela.add(rPreco);
        tela.add(rEstoque);
        tela.add(tDes);
        tela.add(rDes);
        tela.add(tPesquisar);
                
        setSize(1000,650);
        setVisible(true);
        setLocationRelativeTo(null);

        
        con_cliente.executaSQL("select * from remedio order by Id_Rem");
        preencherTabela();

 }
      public void mostrar_Dados(){
        try{
            tCodigo.setText(con_cliente.resultset.getString("Id_Rem"));
            tNome.setText(con_cliente.resultset.getString("Nome_Rem"));
            tCategoria.setText(con_cliente.resultset.getString("Categoria_Num"));
            tPreco.setText(con_cliente.resultset.getString("Preço"));
            tEstoque.setText(con_cliente.resultset.getString("Estoque"));
            tDes.setText(con_cliente.resultset.getString("Descrição"));
            
        }catch(SQLException erro){
             JOptionPane.showMessageDialog(null, "Não localizou dados: "+erro,"Mensagem do prograna", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    public void preencherTabela() throws SQLException
    {
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(150);
 
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);
        
        try{
            con_cliente.resultset.beforeFirst();
            while(con_cliente.resultset.next()){
                modelo.addRow(new Object[]{
                con_cliente.resultset.getString("Id_Rem"),
                con_cliente.resultset.getString("Nome_Rem"), 
                con_cliente.resultset.getString("Categoria_Num"),
                con_cliente.resultset.getString("Preço"), 
                con_cliente.resultset.getString("Estoque"),
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
