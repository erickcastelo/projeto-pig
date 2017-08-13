package venda;

import dao.ProdutoDAO;
import dao.VendaDAO;
import excecao.BDException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import modelos.Cliente;
import modelos.Venda;


public class TelaPesquisaVenda extends javax.swing.JFrame {

   private List<Venda> vendas = new ArrayList<>();
   private Venda venda = new Venda();
    //ProdutoDAO dao = new ProdutoDAO();
    
    public TelaPesquisaVenda() throws ParseException{
        initComponents();      
        
        carregarTabela();
        carregarMenuFlutuante();       
        
    }
    
    private void pesquisar() throws ParseException{
        try {
            preencherVenda();
            VendaDAO dao = new VendaDAO();
            this.vendas = dao.pesquisar(venda);
        } catch (BDException ex) {
            ex.printStackTrace();
        }
    }    
    
    @SuppressWarnings("unchecked")
    
    private void carregarTabela() throws ParseException{
        pesquisar();
        tabelaVenda.setModel(new TabelaModeloVenda(vendas));
    }
    
    private void carregarMenuFlutuante(){
        JMenuItem menuItem[] = {new JMenuItem("Ver detalhes"), new JMenuItem("Excluir"), new JMenuItem("Alterar")};
        
        menuItem[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
        menuItem[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int mensagem = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir " +
                        venda.getId()+ "?", null, JOptionPane.YES_NO_OPTION);
                if(mensagem == JOptionPane.YES_OPTION){
                    excluir();
                }   
            }
        });
        
        menuItem[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alterar();
                } catch (BDException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        for (JMenuItem menuItem1 : menuItem) {
            jPopupMenu1.add(menuItem1);
        }
    }
    
    private void verDetalhes(){
     
    }
    
    private void excluir(){
        try {
            VendaDAO dao = new VendaDAO();
            dao.excluir(venda);
            JOptionPane.showMessageDialog(null, "Removido com sucesso!!");
        } catch (BDException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Impossivel excluir");
        }
    }
    
    private void alterar() throws BDException{
        
        
    }
    
    private void preencherVenda(){
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(campoCliente.getText());
        this.venda.setCliente(cliente);
    }
    
    private void selecionarProduto(MouseEvent evt){
        int linha = tabelaVenda.rowAtPoint(evt.getPoint());
        
        if(linha >= 0){
            tabelaVenda.setRowSelectionInterval(linha, linha);
            linha = tabelaVenda.getSelectedRow();
            
            this.venda = vendas.get(linha);            
        }
    }
    
    private void realizarAcao(MouseEvent evt){
        if (evt.getButton() == evt.BUTTON1){
            
            if(evt.getClickCount() > 1){
                verDetalhes();
            }
        }else{
            if(evt.getButton() == evt.BUTTON3){
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }   
   
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        botaoCadastrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVenda = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoNumero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoData = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        campoCliente = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Venda");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));

        tabelaVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaVendaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaVendaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaVenda);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Pesquisa:"));

        jLabel1.setText("Número:");

        jLabel2.setText("Data:");

        jLabel3.setText("Cliente:");

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(campoNumero)
                        .addComponent(jLabel2)
                        .addComponent(campoData)
                        .addComponent(jLabel3)
                        .addComponent(campoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                    .addComponent(botaoPesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(botaoPesquisar))
        );

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        TelaCadastroVenda cadastraVenda = null;
       try {
           cadastraVenda = new TelaCadastroVenda(vendas);
       } catch (BDException ex) {
           Logger.getLogger(TelaPesquisaVenda.class.getName()).log(Level.SEVERE, null, ex);
       }
        cadastraVenda.setVisible(true);
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
       try {
           carregarTabela();
       } catch (ParseException ex) {
           Logger.getLogger(TelaPesquisaVenda.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_formWindowGainedFocus

    private void tabelaVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaVendaMouseClicked
        
    }//GEN-LAST:event_tabelaVendaMouseClicked

    private void tabelaVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaVendaMouseReleased
        selecionarProduto(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaVendaMouseReleased

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
       try {
           carregarTabela();
       } catch (ParseException ex) {
           Logger.getLogger(TelaPesquisaVenda.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JTextField campoCliente;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaVenda;
    // End of variables declaration//GEN-END:variables
}
