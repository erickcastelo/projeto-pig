
package modelos;

public class Cliente {
    
    private long id;
    private String nomeCliente;
    private String endereco;
    private String telefone;
    private Cidade cidade;
    private EstadoCivil estadoCivil;
    private char sexo;

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return nomeCliente;
    }

    public Cliente() {
    }
    
    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Cliente(long id, String nomeCliente, String endereco, String telefone, Cidade cidade) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
