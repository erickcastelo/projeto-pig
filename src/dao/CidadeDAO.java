
package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Cidade;
import modelos.Estado;
import util.ControlaConexao;

public class CidadeDAO {  
    
    private Connection conexao;
     public void inserir(Cidade cidade) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO cidade (CIDNOME, CIDESTID) VALUES (?,?)";

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            preencherParametros(instrucao, cidade);

            instrucao.execute();

        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
           
            
        } finally {
            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }

    }
     
     public List<Cidade> cidade() throws BDException{
        List<Cidade> cidades = new ArrayList<>();
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            String sql = "SELECT CIDID, CIDNOME, CIDESTID "+
                    "FROM cidade";
            instrucao.execute(sql);
            
            ResultSet resultado = instrucao.getResultSet(); 
            
            EstadoDAO dao = new EstadoDAO();
            
            while(resultado.next()){
                Cidade cidade = new Cidade();
                
                cidade.setId(resultado.getInt("CIDID"));
                cidade.setNomeCidade(resultado.getString("CIDNOME"));    
               
                int estid = resultado.getInt("CIDESTID");
                cidade.setEstado(dao.selecionar(estid));
                
                cidades.add(cidade);
            }
                      
            System.out.print("executou");
            
            return cidades;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException("Ocorreu um problema ao tentar pesquisar os dados!"+ ex);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public Cidade selecionar(int cidid) throws BDException{
       
        Cidade cidade = null;       
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            String sql = "SELECT CIDID, CIDNOME FROM cidade WHERE CIDID = " +cidid;
            instrucao.executeQuery(sql);
            
            ResultSet resultado = instrucao.getResultSet();            
            if(resultado.next()){
                cidade = new Cidade();
                cidade.setId(resultado.getInt("CIDID"));
                cidade.setNomeCidade(resultado.getString("CIDNOME"));
                
            } 
            return cidade;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }
    } 
    
    public List<Cidade> pesquisar(Cidade cidade) throws BDException{
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        String sql = "SELECT CIDID, CIDNOME, CIDESTID FROM cidade WHERE CIDNOME LIKE ?";
        
        cidade.setNomeCidade(cidade.getNomeCidade()==null?"":cidade.getNomeCidade());
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            
            //JOptionPane.showMessageDialog(null, estado.getNome());
            
            instrucao.setString(1, "%"+cidade.getNomeCidade()+"%");
            resultados = instrucao.executeQuery();
            
            List<Cidade> cidades = new ArrayList<>();
            Cidade cidadeSaida;
            
            EstadoDAO dao = new EstadoDAO();
            
            while (resultados.next()) {     
                int estid = resultados.getInt("CIDESTID");
                cidadeSaida = new Cidade(resultados.getInt("CIDID"), resultados.getString("CIDNOME"), dao.selecionar(estid));
                cidades.add(cidadeSaida);
            }
            return cidades;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public void alterar(Cidade cidade) throws BDException{
        
         PreparedStatement instrucao = null;        
         
        try {
            conexao = ControlaConexao.getConexao();
            
            String sql = "UPDATE cidade SET CIDNOME = ?, CIDESTID = ? WHERE CIDID = ?";
            
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, cidade.getNomeCidade());
            instrucao.setInt(2, (int) cidade.getEstado().getId());
            instrucao.setInt(3, (int) cidade.getId());
            
            instrucao.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public void excluir(Cidade cidade) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM cidade WHERE CIDID = " + cidade.getId();
            instrucao.execute(sql);
           
                 
        } catch (SQLException ex) {
            //ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }
    }    
    
    
    private void preencherParametros(PreparedStatement instrucao, Cidade cidade) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        if (cidade.getNomeCidade() != null) {
            instrucao.setString(1, cidade.getNomeCidade());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
        
        if (cidade.getEstado().getId() != 0) {
            instrucao.setInt(2, (int) cidade.getEstado().getId());
        } else {
            instrucao.setNull(2, java.sql.Types.VARCHAR);
        }
        
    }

}
