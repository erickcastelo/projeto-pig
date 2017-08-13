package FormaPagto;
import dao.FormaPagtoDAO;
import produto.*;
import dao.ProdutoDAO;
import excecao.BDException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import modelos.FormaPagto;
import modelos.Produto;
import produto.TabelaModeloProduto;

public class TelaPesquisaFormaPagto extends javax.swing.JFrame {

   private List<FormaPagto> formaPagtos = new ArrayList<>();
   private FormaPagto formaPagto = new FormaPagto();
    //ProdutoDAO dao = new ProdutoDAO();
    
    public TelaPesquisaFormaPagto(){
        initComponents();      
        
        carregarTabela();
        carregarMenuFlutuante();       
        
    }
    
    private void pesquisar(){
        try {
            preencherFormaPagto();
            FormaPagtoDAO dao = new FormaPagtoDAO();
            this.formaPagtos = dao.pesquisar(formaPagto);
        } catch (BDException ex) {
            ex.printStackTrace();
        }
    }    
    
    @SuppressWarnings("unchecked")
    
    private void carregarTabela(){
        pesquisar();
        tabelaFormaPagto.setModel(new TabelaModeloFormaPagto(formaPagtos));
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
                        formaPagto.getDescricao()+ "?", null, JOptionPane.YES_NO_OPTION);
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
      TelaVerDetalhesFormaPagto ver = new TelaVerDetalhesFormaPagto(formaPagto);
      ver.setVisible(true);
    }
    
    private void excluir(){
        try {
            FormaPagtoDAO dao = new FormaPagtoDAO();
            dao.excluir(formaPagto);
        } catch (BDException ex) {
            ex.printStackTrace();
        }
    }
    
    private void alterar() throws BDException{
        try {
            TelaAlterarFormaPagto alterarFormaPagto = new TelaAlterarFormaPagto(formaPagto);
            alterarFormaPagto.setVisible(true);
        } catch (BDException ex) {
            ex.printStackTrace();
        }
        
    }
    
    private void preencherFormaPagto(){
        this.formaPagto.setDescricao(campoNome.getText());
    }
    
    private void selecionarFormaPagto(MouseEvent evt){
        int linha = tabelaFormaPagto.rowAtPoint(evt.getPoint());
        
        if(linha >= 0){
            tabelaFormaPagto.setRowSelectionInterval(linha, linha);
            linha = tabelaFormaPagto.getSelectedRow();
            
            this.formaPagto = formaPagtos.get(linha);
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
        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        campoCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFormaPagto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Cliente");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel1.setText("Nome:");

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        campoCancelar.setText("Cancelar");
        campoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));

        tabelaFormaPagto.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaFormaPagto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFormaPagtoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaFormaPagtoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFormaPagto);

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoCadastrar)
                                .addGap(205, 205, 205)
                                .addComponent(campoCancelar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoPesquisar)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoPesquisar)
                .addGap(32, 32, 32)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(campoCancelar))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
       try {
           TelaCadastroFormaPagto cadastroFormaPagto = new TelaCadastroFormaPagto(formaPagtos);
           cadastroFormaPagto.setVisible(true);
       } catch (BDException ex) {
           ex.printStackTrace();
       }  
       
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        carregarTabela();
    }//GEN-LAST:event_formWindowGainedFocus

    private void campoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_campoCancelarActionPerformed

    private void tabelaFormaPagtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFormaPagtoMouseClicked
        
    }//GEN-LAST:event_tabelaFormaPagtoMouseClicked

    private void tabelaFormaPagtoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFormaPagtoMouseReleased
        selecionarFormaPagto(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaFormaPagtoMouseReleased

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        carregarTabela();
       
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton campoCancelar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaFormaPagto;
    // End of variables declaration//GEN-END:variables
}
