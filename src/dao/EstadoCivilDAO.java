
package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import modelos.Cliente;
import modelos.EstadoCivil;
import modelos.ItemVenda;
import modelos.Produto;
import modelos.Venda;
import util.ControlaConexao;

public class EstadoCivilDAO {  
    
    private Connection conexao;
         
     
    public List<EstadoCivil> pesquisar(EstadoCivil estadoCivil) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        //Este comando mostra os resultados por nome parcial, mas também um 
        //registro apenas com um ID específico.
        
        String sql = " SELECT ETCID, ETCDESCRICAO"
                + " FROM estadocivil"
                + " WHERE ETCDESCRICAO LIKE ?";                   
        
        //Deixar o nome vazio, se for nulo, assim, mostra todos os registros.
        estadoCivil.setDescricao(estadoCivil.getDescricao()==null?"":estadoCivil.getDescricao());

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            instrucao.setString(1,"%"+estadoCivil.getDescricao()+"%");
            
            resultados = instrucao.executeQuery();            
            
            List<EstadoCivil> estadoCivis=new ArrayList<>();
            EstadoCivil estadoCivilSaida;
            
            while(resultados.next()){
                estadoCivilSaida = new EstadoCivil();
                
                estadoCivilSaida.setId(resultados.getInt("ETCID"));
                estadoCivilSaida.setDescricao(resultados.getString("ETCDESCRICAO"));
                
                estadoCivis.add(estadoCivilSaida);
            }
            
            return estadoCivis;

        } catch (SQLException ex) {
           ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
            
        } finally {
            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }    
     
    
    private Venda preencherParametros(PreparedStatement instrucao, Venda venda) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        
        if (venda.getCliente().getId() != 0 ){           
            instrucao.setLong(1, venda.getCliente().getId());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
        
        if (venda.getFormaPagto().getId() != 0){           
            instrucao.setLong(2, venda.getFormaPagto().getId());
        } else {
            instrucao.setNull(2, java.sql.Types.VARCHAR);
        }
        return venda;
    }

}
