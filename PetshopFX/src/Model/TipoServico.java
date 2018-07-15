package Model;

/*
Cadastro de tipo de serviços: para cada serviço, anota-se um número, 
o nome do serviço, tipo de atendimento (clínico, estético - String) 
e o preço do serviço.
*/

public class TipoServico {
    private int numeroServico, id;
    private String nomeServico, tipoDeAtendimento;
    private double precoServico;
    
    public TipoServico(int numeroServico, String nomeServico, String tipoDeAtendimento, double precoServico){
        this.numeroServico = numeroServico;
        this.nomeServico = nomeServico;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.precoServico = precoServico;
    }
    
    public TipoServico(int id, int numeroServico, String nomeServico, String tipoDeAtendimento, double precoServico){
        this.id = id;
        this.numeroServico = numeroServico;
        this.nomeServico = nomeServico;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.precoServico = precoServico;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumeroServico() {
        return numeroServico;
    }
    
    public void setNumeroServico(int numeroServico) {
        this.numeroServico = numeroServico;
    }
    
    public String getNomeServico() {
        return nomeServico;
    }
    
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    
    public String getTipoDeAtendimento() {
        return tipoDeAtendimento;
    }
    
    public void setTipoDeAtendimento(String tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }
    
    @Override
    public String toString(){
        return getNomeServico();
    }
}
