/*
Cadastro de tipo de serviços: para cada serviço, anota-se um número, 
o nome do serviço, tipo de atendimento (clínico, estético - String) 
e o preço do serviço.
*/

package model;

public class TipoServico {
    private int id_tipoServico;
    private int numeroServico;
    private String nomeServico, tipoDeAtendimento;
    private double precoServico;
    
    public TipoServico(int numeroServico, String nomeServico, String tipoDeAtendimento, double precoServico){
        this.numeroServico = numeroServico;
        this.nomeServico = nomeServico;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.precoServico = precoServico;
    }
    
    public TipoServico(int id_tipoServico, int numeroServico, String nomeServico, String tipoDeAtendimento, double precoServico){
        this.id_tipoServico = id_tipoServico;
        this.numeroServico = numeroServico;
        this.nomeServico = nomeServico;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.precoServico = precoServico;
    }
    
    public int getId_tipoServico() {
        return id_tipoServico;
    }
    
    public void setId_tipoServico(int id_tipoServico) {
        this.id_tipoServico = id_tipoServico;
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
