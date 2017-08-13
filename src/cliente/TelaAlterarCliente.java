/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cidade.*;
import Estado.*;
import dao.CidadeDAO;
import dao.ClienteDAO;
import dao.EstadoDAO;
import excecao.BDException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Cidade;
import modelos.Cliente;
import modelos.Estado;

/**
 *
 * @author Usuario
 */
public class TelaAlterarCliente extends javax.swing.JFrame {

    private Cliente cliente;
    private String anterior;
    public TelaAlterarCliente(Cliente cliente) throws BDException {
        initComponents();
        this.cliente = cliente;     
        anterior = cliente.getNomeCliente();
        preencherComboCidade();
        setar();
        getRootPane().setDefaultButton(botaoAlterar);
    }

    private void setar(){
        campoNome.setText(cliente.getNomeCliente());
        campoEndereco.setText(cliente.getEndereco());
        campoTelefone.setText(cliente.getTelefone());
        comboCidade.getModel().setSelectedItem(cliente.getCidade());
        comboSexo.removeAllItems();
        if(cliente.getSexo() == 'm'){
            comboSexo.addItem("Masculino");
            comboSexo.addItem("Feminino");
        }else{
            comboSexo.addItem("Feminino");
            comboSexo.addItem("Masculino");
        }
//          JOptionPane.showMessageDialog(null, );
    }
    
     private void limpar(){
        campoNome.setText("");
        campoEndereco.setText("");
        campoTelefone.setText("");
      }  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoAlterar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        campoEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoTelefone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboSexo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu de Opções:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoCancelar.setText("Cancelar");

        jLabel1.setText("Nome:");

        jLabel3.setText("Endereço:");

        jLabel4.setText("Telefone:");

        jLabel5.setText("Cidade:");

        jLabel2.setText("Sexo:");

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNome)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(botaoAlterar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                                    .addComponent(botaoCancelar))
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboCidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoEndereco, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoTelefone, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel2)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterar)
                    .addComponent(botaoCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void preencherComboCidade() throws BDException{
        comboCidade.removeAllItems();
        comboCidade.addItem("");
        
        CidadeDAO dao = new CidadeDAO();
        
        for (Cidade cidade : dao.cidade()) {
            comboCidade.addItem(cidade);
        }
    }
    
    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
       cliente.setNomeCliente(campoNome.getText());
       cliente.setEndereco(campoEndereco.getText());
       cliente.setTelefone(campoTelefone.getText());
       cliente.setCidade((Cidade) comboCidade.getSelectedItem());
       char sexo;
       if(comboSexo.getModel().getElementAt(0).toString().equals("Masculino")){
           sexo = 'f';
       }else{
           sexo = 'm';
       }
       //OptionPane.showMessageDialog(null, sexo);
       cliente.setSexo(sexo);
      int mensagem = JOptionPane.showConfirmDialog(null, "Deseja alterar os campo" +
                        "?", null, JOptionPane.YES_NO_OPTION);
      
      if(mensagem == JOptionPane.YES_OPTION){
          ClienteDAO dao = new ClienteDAO();
        try {
            dao.alterar(cliente);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            limpar();
            dispose();
        } catch (BDException ex) {
           ex.printStackTrace();
        }
      }    
     
    }//GEN-LAST:event_botaoAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoTelefone;
    private javax.swing.JComboBox comboCidade;
    private javax.swing.JComboBox comboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
