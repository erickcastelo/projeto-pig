
package cidade;

import Estado.*;
import dao.BDMensagensPadrao;
import dao.CidadeDAO;
import dao.EstadoDAO;
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
import modelos.Cidade;
import modelos.Estado;


public class TelaPesquisaCidade extends javax.swing.JFrame {

   private List<Cidade> cidades = new ArrayList<>();
   private Cidade cidade = new Cidade();
   CidadeDAO dao = new CidadeDAO();
    
    public TelaPesquisaCidade(){
        initComponents();      
        
        carregarTabela();
        carregarMenuFlutuante();       
        
    }
    
    private void pesquisar(){
        try {            
            CidadeDAO dao = new CidadeDAO();
            this.cidades = dao.cidade();
        } catch (BDException ex) {
            ex.printStackTrace();
        }
    }
    private void pesquisarEspecifico(){
        try {
            preencherCidade();
            CidadeDAO dao = new CidadeDAO();
            this.cidades = dao.pesquisar(this.cidade);             
             tabelaCidade.setModel(new TabelaModeloCidade(this.cidades));
        } catch (BDException ex) {
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    
    private void carregarTabela(){
        pesquisar();
        tabelaCidade.setModel(new TabelaModeloCidade(this.cidades));
    }
    
    private void carregarMenuFlutuante(){
        JMenuItem menuItem[] = {new JMenuItem("Ver detalhes"), new JMenuItem("Excluir"), new JMenuItem("Alterar")};
        
        menuItem[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               verDetalhes();
            }
        });
        
        menuItem[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int mensagem = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir " +
                        cidade.getNomeCidade()+ "?", null, JOptionPane.YES_NO_OPTION);
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
        TelaVerDetalhesCidade verDetalhesCidade = new TelaVerDetalhesCidade(cidade);
        verDetalhesCidade.setVisible(true);
    }
    
    private void excluir(){
        try {
            CidadeDAO dao = new CidadeDAO();
            dao.excluir(cidade);
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!");
        } catch (BDException ex) {
            JOptionPane.showMessageDialog(null, "Impossível excluir, essa cidade estar "
                    + "relacionada a um cliente");
        }
    }
    
    private void alterar() throws BDException{
        TelaAlterarCidade alterarCidade = new TelaAlterarCidade(cidade);
        alterarCidade.setVisible(true);
    }
    
    private void preencherCidade(){
        this.cidade.setNomeCidade(campoNome.getText());
    }
    
    private void selecionarCidade(MouseEvent evt){
        int linha = tabelaCidade.rowAtPoint(evt.getPoint());
        
        if(linha >= 0){
            tabelaCidade.setRowSelectionInterval(linha, linha);
            linha = tabelaCidade.getSelectedRow();
            
            this.cidade = cidades.get(linha);            
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
    
   private void preencherTabela() throws BDException{
       cidades.removeAll(cidades);
       CidadeDAO dao = new CidadeDAO();
       
       this.cidades = dao.cidade();
       
       tabelaCidade.setModel(new TabelaModeloCidade(cidades));
       
   }  
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        campoCadastrar = new javax.swing.JButton();
        campoCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCidade = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Cidade");
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

        campoCadastrar.setText("Cadastrar");
        campoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCadastrarActionPerformed(evt);
            }
        });

        campoCancelar.setText("Cancelar");
        campoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(51, 51, 51)));

        tabelaCidade.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCidadeMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaCidadeMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCidade);

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
                                .addComponent(campoCadastrar)
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
                    .addComponent(campoCadastrar)
                    .addComponent(campoCancelar))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCadastrarActionPerformed
       TelaCadastroCidade telaCidade = null;
       try {
           telaCidade = new TelaCadastroCidade(cidades);
       } catch (BDException ex) {
           Logger.getLogger(TelaPesquisaCidade.class.getName()).log(Level.SEVERE, null, ex);
       }
        telaCidade.setVisible(true);
    }//GEN-LAST:event_campoCadastrarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        carregarTabela();
    }//GEN-LAST:event_formWindowGainedFocus

    private void campoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_campoCancelarActionPerformed

    private void tabelaCidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCidadeMouseClicked
        
    }//GEN-LAST:event_tabelaCidadeMouseClicked

    private void tabelaCidadeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCidadeMouseReleased
        selecionarCidade(evt);
        realizarAcao(evt);
    }//GEN-LAST:event_tabelaCidadeMouseReleased

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        pesquisarEspecifico();
       
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton campoCadastrar;
    private javax.swing.JButton campoCancelar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCidade;
    // End of variables declaration//GEN-END:variables
}
