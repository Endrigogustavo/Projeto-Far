package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.*;
import javax.swing.*;


import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Admin
 */
public class Funcionarios extends JFrame {
    Conexao con_cliente;
    JLabel rCodigo, rNome,  rUsuario,rID, rSenha, rEmail, rData,rCPF,imagen;
    JTextField tCodigo, tNome, tUsuario,tID, tSenha,tEmail,tCPF,tPesq;
    JFormattedTextField tData;
    MaskFormatter mData;
    JButton Prim,Ante,Prox,Ult,NoR,Grav,Alter,Exclu,Pesq,sair;
    JTable tblClientes;
    JScrollPane scp_tabela;
     Image backgroundImage;
    
     
    
    public Funcionarios() throws SQLException, ParseException{
 ImageIcon icone = new ImageIcon("src/img/logo.jpg");
       setIconImage(icone.getImage());
       Container tela = getContentPane();
       ImageIcon backgroundImageIcon = new ImageIcon("src/img/NEED.jpg");
       backgroundImage = backgroundImageIcon.getImage();
       tela.setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));
       tela.setLayout(null);
       
        rCodigo = new JLabel("Codigo");
        rNome = new JLabel("Nome");
        rUsuario = new JLabel("Usuario");
         rID = new JLabel("ID");
        rSenha = new JLabel("Senha");
        rEmail = new JLabel("Email");
        rData = new JLabel("Data");
          rCPF = new JLabel("CPF");
          
        
           Prim=new JButton("Primeiro");
            Ante=new JButton("Anterior");
            Prox=new JButton("Proximo");
            Ult=new JButton("Último");
            NoR=new JButton("Novo Registro");
            Grav=new JButton("Gravar");
            Alter=new JButton("Alterar");
            Exclu=new JButton("Excluir");
            Pesq=new JButton("Pesquisar");
            sair=new JButton("Sair");
        
         tCodigo = new JTextField();
         tNome = new JTextField();
         tUsuario= new JTextField();
         tID= new JTextField();
         tSenha= new JTextField();
         tEmail= new JTextField();
         tCPF= new JTextField();
       tPesq = new JTextField();
         

        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Conexao Java com Mysql");
        setResizable(false);
        tela.setLayout(null);
        
            Prim.setBounds(150, 190, 100, 25);
            Ante.setBounds(350, 190, 100, 25);
            Prox.setBounds(550, 190, 100, 25);
            Ult.setBounds(750, 190, 100, 25);
            NoR.setBounds(150, 290, 100, 25);
            Grav.setBounds(350, 290, 100, 25);
            Alter.setBounds(550, 290, 100, 25);
            Exclu.setBounds(750, 290, 100, 25);
            Pesq.setBounds(200, 340, 100, 30);
            sair.setBounds(650, 340, 30, 30);
                   
            rCodigo.setBounds(50, 20, 100, 20);
            tCodigo.setBounds(100, 20, 150, 20);
            
            rNome.setBounds(50, 40, 100, 20);
            tNome.setBounds(100, 40, 150, 20);
            
            rUsuario.setBounds(50, 60, 100, 20);
            tUsuario.setBounds(100,60, 150, 20);
             
            rID.setBounds(50, 80, 100, 20);
            tID.setBounds(100, 80, 150, 20);
            
            rSenha.setBounds(50, 100, 100, 20);
            tSenha.setBounds(100, 100, 150, 20);
            
            rEmail.setBounds(50, 120, 200, 20);
            tEmail.setBounds(100, 120, 150, 20);
            
            rData.setBounds(50, 140, 200, 20);
             
            rCPF.setBounds(50, 160, 100, 20);
            tCPF.setBounds(100, 160, 150, 20);
            
           
            tPesq.setBounds(100, 340, 100, 30);
        

                
        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();

        tblClientes.setBounds(50,400,900,200);
        scp_tabela.setBounds(50,400,900,200);
        
             try {
           
            MaskFormatter mData = new MaskFormatter("####/##/##");
    
            tData = new JFormattedTextField(mData);
            tData.setBounds(100, 140, 50, 20);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        Prim.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.first();
                    mostrar_Dados();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        Ante.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
               
                  if(con_cliente.resultset.isFirst()){
                JOptionPane.showMessageDialog(null, "Você está no primeiro registro,mas você poderá ir para o ultimo ao clicar novamente ");
                 con_cliente.resultset.last(); 
               
                }else{
                      con_cliente.resultset.previous();
                    mostrar_Dados();
                }
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o registro anterior");
            }
        } 
    });
    Prox.addActionListener(new ActionListener(){
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
    
    Ult.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           try{
              if (con_cliente.resultset.last()) {
                mostrar_Dados();
            } else {
                JOptionPane.showMessageDialog(null, "Você está no último registro, mas você poderá ir para o primeiro ao clicar novamente.");
                con_cliente.resultset.first();  
                mostrar_Dados();
              }
              
           }catch(SQLException erro){
               JOptionPane.showMessageDialog(null, "Não foi possivel acessar o Último");
           }
       }});
         
   NoR.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             
           tCodigo.setText("");
           tNome.setText("");
            tUsuario.setText("");
           tID.setText("");
           tSenha.setText("");
           tEmail.setText("");
            tData.setText("");
            tCPF.setText("");
            tCodigo.requestFocus();
       
            }
             }
              );
   
   Grav.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
    
        String nome = Funcionarios.this.tNome.getText();
        String Usuario = Funcionarios.this.tUsuario.getText();
        String ID = Funcionarios.this.tID.getText();
        String Senha = Funcionarios.this.tSenha.getText();
        String email = Funcionarios.this.tEmail.getText();
        String data_nasc = Funcionarios.this.tData.getText();
        String CPF = Funcionarios.this.tCPF.getText();

   
   try{
  
           String insert_sql = "insert into funcionário( Nome_Func, Usuário, Nivel_ID, Senha, Email, Data_Nasc, CPF) values ('" + nome + "','" + Usuario + "','" + ID + "','" + Senha + "','" + email + "','" +data_nasc+ "','" + CPF + "')";
           con_cliente.statement.executeUpdate(insert_sql);
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                 
                 con_cliente.executaSQL("select * from funcionário order by ID_Func");
                 preencherTabela();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel gravar registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
   
   Alter.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
      String nome = Funcionarios.this.tNome.getText();
        String Usuario = Funcionarios.this.tUsuario.getText();
        String ID = Funcionarios.this.tID.getText();
        String Senha = Funcionarios.this.tSenha.getText();
        String email = Funcionarios.this.tEmail.getText();
        String data_nasc = Funcionarios.this.tData.getText();
        String CPF = Funcionarios.this.tCPF.getText();
     String sql;
     String msg="";
     try{
         if(tCodigo.getText().equals("")){
         sql = "insert into funcionário( Nome_Func, Usuário, Nivel_ID, Senha, Email, Data_Nasc, CPF) values ( '"+nome+"','"+Usuario+"','"+ID+"','"+Senha+"','"+email+"','"+data_nasc+"','"+CPF+"')";
         msg="Gravação de um novo registro";
         }else{
              sql = "update funcionário set nome='" + nome + "', Usuario='" + Usuario + "', ID='" + ID + "', Senha='" + Senha + "',email='" + email + "', dt_nasc='" + data_nasc +"',CPF='" + CPF + "' where cod=" + tCodigo.getText();
             msg="Alteração de registro";
         }
         con_cliente.statement.executeUpdate(sql);
         JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
         con_cliente.executaSQL("select * from funcionário order by  ID_Func");
         preencherTabela();
     }catch(SQLException errosql){
      JOptionPane.showMessageDialog(null, "\n Erro na gravação :\n" +errosql,"Mesagem do Programa", JOptionPane.INFORMATION_MESSAGE);
     }
       }
        }
          );
   
   Exclu.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){

     String sql;
     try{
         int resposta = JOptionPane.showConfirmDialog(rootPane,"Deseja excluir o registro: " , "Confirmar Exclusão",JOptionPane.YES_NO_OPTION,3);
         if(resposta==0){
             sql = "delete from funcionários where cod ="+tCodigo.getText();
             int excluir = con_cliente.statement.executeUpdate(sql);
             if(excluir==1){
                 JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                 con_cliente.executaSQL("select * from  funcionário order by  ID_Func");
                 con_cliente.resultset.first();
                 preencherTabela();
                 
             }
             else{
                 JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuário!!","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
             }}
             }catch(SQLException excecao){
                 JOptionPane.showMessageDialog(null,"Erro na exclusão: " + excecao,"Mendagem do programa",JOptionPane.INFORMATION_MESSAGE);
             }
             }
     });
   
    sair.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
            
                           int opcao;
            
                            Object[] botoes = {"Sim","Não"};
                             opcao = JOptionPane.showOptionDialog(null,"Deseja mesmo fechar a janela?","Fechar",
                          JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,botoes,botoes[0]);
                                    if(opcao==JOptionPane.YES_OPTION)System.exit(0);
              } 
                }
                  );

    Pesq.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           try{
              
               String pesquisa = "select * from funcionário where Nome_Func like'"+tPesq.getText()+"%'";//Valor aproximado
               con_cliente.executaSQL(pesquisa);
               if (con_cliente.resultset.first()) {
                preencherTabela();
            } else {
                JOptionPane.showMessageDialog(null, "\n Os dados digitados não foram localizados!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\n Erro ao executar a consulta SQL: " + errosql.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }
});
        tela.add(Prim);
        tela.add(Ante);
        tela.add(Prox);
        tela.add(Ult);
        tela.add(NoR);
        tela.add(Grav);
        tela.add(Alter);
        tela.add(Exclu);
        tela.add(Pesq);
        tela.add(sair);
        
        tela.add(rCodigo);
        tela.add(rNome);
        tela.add(rUsuario);
        tela.add(rID);
        tela.add(rSenha);
        tela.add(rEmail);
        tela.add(rData);
        tela.add(rCPF);
      
        
        tela.add(tCodigo);
        tela.add(tNome);
         tela.add(tUsuario);
         tela.add(tID);
        tela.add(tSenha);
        tela.add(tEmail);
        tela.add(tData);
         tela.add(tCPF);
         tela.add(tPesq);
         

        
        tela.add(tblClientes);
        tela.add(scp_tabela);

        
Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(1000, 660, Image.SCALE_SMOOTH);
ImageIcon resizedBackgroundImageIcon = new ImageIcon(backgroundImage);
JLabel backgroundLabel = new JLabel(resizedBackgroundImageIcon);
backgroundLabel.setBounds(0, 0, 1000, 660);
tela.add(backgroundLabel);
        
        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
        
        tblClientes.setFont(new java.awt.Font("Arial",1,12)); 
        
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][]{
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
        },
                new String [] {"ID_Func", "Nome", "Usuário", "Nivel_ID", "Senha", "Email", "Data_Nasc", "CPF"})
        {
            boolean[] canEdit = new boolean[]{
                false, false,false, false,false,false,false,false
            };
          
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
});          
        scp_tabela.setViewportView(tblClientes); 
        tblClientes.setAutoCreateRowSorter(true);

        setSize(1000,650);
        setVisible(true);
        setLocationRelativeTo(null);

        
        con_cliente.executaSQL("select * from funcionário order by ID_Func");
        preencherTabela();

 }
      public void mostrar_Dados(){
        try{
           tCodigo.setText(con_cliente.resultset.getString("ID_Func"));
            tNome.setText(con_cliente.resultset.getString("Nome_Func"));
            tUsuario.setText(con_cliente.resultset.getString("Usuário"));
            tID.setText(con_cliente.resultset.getString("Nivel_ID"));
            tSenha.setText(con_cliente.resultset.getString("Senha"));
            tEmail.setText(con_cliente.resultset.getString("Email"));
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
           String dataFormatada = sdf.format(con_cliente.resultset.getDate("Data_Nasc"));
           tData.setText(dataFormatada);
            tCPF.setText(con_cliente.resultset.getString("CPF"));
        }catch(SQLException erro){
             JOptionPane.showMessageDialog(null, "Não localizou dados: "+erro,"Mensagem do prograna", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    public void preencherTabela() throws SQLException
    {
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(11);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(14);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(14);
        tblClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
        tblClientes.getColumnModel().getColumn(7).setPreferredWidth(100);
        
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);
        
        try{
            con_cliente.resultset.beforeFirst();
            while(con_cliente.resultset.next()){
                modelo.addRow(new Object[]{
                con_cliente.resultset.getString("ID_Func"),
                con_cliente.resultset.getString("Nome_Func"), 
                con_cliente.resultset.getString("Usuário"), 
                con_cliente.resultset.getString("Nivel_ID"), 
                con_cliente.resultset.getString("Senha"), 
                con_cliente.resultset.getString("Email"), 
                con_cliente.resultset.getString("Data_Nasc"), 
                con_cliente.resultset.getString("CPF")
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
