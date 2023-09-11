/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class MenuAdm extends JFrame {
    Conexao con_cliente;
    JLabel rCodigo, rNome, rEmail, rTel, rData, rPesquisar, imagem;
    JTextField tCodigo, tNome, tEmail, tTel, tData,tPesquisar;
    JFormattedTextField mTel, mData, mCodigo;
    JButton primeiro, anterior, proximo, ultimo, registro, gravar, alterar, excluir,pesquisar,sair;
    
    JTable tblClientes;
    JScrollPane scp_tabela;
    
     MaskFormatter n1,n2;
    
    public MenuAdm() throws SQLException, ParseException{

        Container tela = getContentPane();
        
        ImageIcon icone = new ImageIcon("baixados.jpeg");
        imagem = new JLabel(icone);
        imagem.setBounds(0, 0, 1200, 620);

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



        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Conexao Java com Mysql");
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
                tData.setText("");
                tEmail.setText("");
                tNome.setText("");
                tTel.setText("");
            }
        });
        
        gravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tNome.getText();
                String dt = tData.getText();
                String tel = tTel.getText();
                String email = tEmail.getText();
                try{
                 String insert_sql = "insert into tbclientes(nome,telefone,email,dt_nasc)values('"+nome+"','"+tel+"','"+email+"','"+dt+"')";
                 con_cliente.statement.executeUpdate(insert_sql);
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                 
                 con_cliente.executaSQL("select * from tbclientes order by cod");
                 preencherTabela();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel gravar registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        alterar.addActionListener(new ActionListener(){           
            //Arrumar
            public void actionPerformed(ActionEvent e){
                String nome = tNome.getText();
                String dt = tData.getText();
                String tel = tTel.getText();
                String email = tEmail.getText();
                String sql;
                String msg="";
               
                try {
                    if (tCodigo.getText().equals("")) {
                       sql = "insert into tbclientes(nome,telefone,email,dt_nasc)values('"+nome+"','"+tel+"','"+email+"','"+dt+"')";
                       msg="Gravado com sucesso";
                    }
                    else{
                        sql = "update tbclientes set nome='"+nome+"',telefone='"+tel+"',email='"+email+"',dt_nasc='"+dt+"'where cod="+tCodigo.getText();
                        msg="Alterado com sucesso";
                    }
                    
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                    con_cliente.executaSQL("select * from tbclientes order by cod");
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
                      sql = "delete from tbclientes where cod = " +tCodigo.getText();
                      int excluir = con_cliente.statement.executeUpdate(sql);                   
                    if(excluir==1){
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        con_cliente.executaSQL("select * from tbclientes order by cod");
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
                    String pesquisa = "select * from tbclientes where nome like'"+tPesquisar.getText()+"%'";
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
                
     
                primeiro.setBounds(60, 260,100, 30);
                tela.add(primeiro);
                anterior.setBounds(150, 260,100, 30);
                tela.add(anterior);
                proximo.setBounds(240, 260,100, 30);
                tela.add(proximo);
                ultimo.setBounds(330, 260,100, 30);
                tela.add(ultimo);
                
                
                registro.setBounds(500, 260,130, 30);
                tela.add(registro);
                gravar.setBounds(635, 260,100, 30);
                tela.add(gravar);
                alterar.setBounds(730, 260,100, 30);
                tela.add(alterar);
                excluir.setBounds(830, 260,100, 30);
                tela.add(excluir);
                
                pesquisar.setBounds(450, 335,150, 22);
                tela.add(pesquisar);
                
                sair.setBounds(750, 335,150, 30);
                tela.add(sair);
                
               
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
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
        },
                new String [] {"Código", "Nome", "Data Nascimento", "Telefone", "Email"})
        {
            boolean[] canEdit = new boolean[]{
                false, false,false, false,false
            };
          
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
});
        
       
        scp_tabela.setViewportView(tblClientes);
        
        tblClientes.setAutoCreateRowSorter(true);

        
        
        try {
            MaskFormatter mTel = new MaskFormatter("(##)#####-####");
            MaskFormatter mData = new MaskFormatter("##/##/####");
            MaskFormatter mCodigo = new MaskFormatter("###");
           
        } catch (ParseException e) {
        }
        
        mData = new JFormattedTextField(tData);
        mTel = new JFormattedTextField(tTel);
        mCodigo = new JFormattedTextField(tCodigo);
        
        
        rCodigo.setBounds(50, 40, 150, 50);
        rNome.setBounds(50, 80, 150, 50);
        rEmail.setBounds(50, 120, 150, 50);
        rTel.setBounds(50, 160, 150, 50);
        rData.setBounds(50, 200, 150, 50);
        
        rCodigo.setForeground(Color.white);
        rNome.setForeground(Color.white);
        rEmail.setForeground(Color.white);
        rTel.setForeground(Color.white);
        rData.setForeground(Color.white);
        rPesquisar.setForeground(Color.white);
        
        rCodigo.setFont(new Font("Tahoma",Font.BOLD,15));
        rNome.setFont(new Font("Tahoma",Font.BOLD,15));
        rEmail.setFont(new Font("Tahoma",Font.BOLD,15));
        rTel.setFont(new Font("Tahoma",Font.BOLD,15));
        rData.setFont(new Font("Tahoma",Font.BOLD,15));
        rPesquisar.setFont(new Font("Tahoma",Font.BOLD,15));
        
        
        tCodigo.setBounds(130, 55, 80, 20);
        tNome.setBounds(130, 95, 220, 20);
        tEmail.setBounds(130, 135, 220, 20);
        
        
        
                try {
           n1 = new MaskFormatter("(##)#####-####");
           n2 = new MaskFormatter("##/##/####");
           tData = new JFormattedTextField(n2); 
           tData.setBounds(130, 215, 80, 20);
           tTel = new JFormattedTextField(n1);
           tTel.setBounds(130, 175, 120, 20);
        } catch (ParseException e) {
        }
                
                
        
        tela.add(tEmail);
        tela.add(tCodigo);
        tela.add(tNome);
        tela.add(tTel);
        tela.add(tData);
        tela.add(rCodigo);
        tela.add(rNome);
        tela.add(rEmail);
        tela.add(rTel);
        tela.add(rData);
        
        tela.add(rPesquisar);
        tela.add(tPesquisar);
        

        tela.add(imagem);
        

                tela.setComponentZOrder(primeiro, 0);
                tela.setComponentZOrder(anterior, 0);
                tela.setComponentZOrder(proximo, 0);
                tela.setComponentZOrder(ultimo, 0);
                tela.setComponentZOrder(registro, 0);
                tela.setComponentZOrder(gravar, 0);
                tela.setComponentZOrder(alterar, 0);
                tela.setComponentZOrder(excluir, 0);
                tela.setComponentZOrder(pesquisar, 0);
                tela.setComponentZOrder(sair, 0);
                
         

        
        
        setSize(1000,650);
        setVisible(true);
        setLocationRelativeTo(null);

        
        con_cliente.executaSQL("select * from tbclientes order by cod");
        preencherTabela();

 }
      public void mostrar_Dados(){
        try{
            tCodigo.setText(con_cliente.resultset.getString("cod"));
            tNome.setText(con_cliente.resultset.getString("nome"));
            tData.setText(con_cliente.resultset.getString("dt_nasc"));
            tTel.setText(con_cliente.resultset.getString("telefone"));
            tEmail.setText(con_cliente.resultset.getString("email"));
        }catch(SQLException erro){
             JOptionPane.showMessageDialog(null, "Não localizou dados: "+erro,"Mensagem do prograna", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    public void preencherTabela() throws SQLException
    {
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(11);
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(14);
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
        
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);
        
        try{
            con_cliente.resultset.beforeFirst();
            while(con_cliente.resultset.next()){
                modelo.addRow(new Object[]{
                con_cliente.resultset.getString("cod"), con_cliente.resultset.getString("nome"), con_cliente.resultset.getString("dt_nasc"), con_cliente.resultset.getString("telefone"), con_cliente.resultset.getString("email")
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
