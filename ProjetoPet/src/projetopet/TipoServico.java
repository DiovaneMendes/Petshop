/*
Cadastro de tipo de serviços: para cada serviço, anota-se um número, 
o nome do serviço, tipo de atendimento (clínico, estético - String) 
e o preço do serviço.
*/

package projetopet;

public class TipoServico {
    private int numeroServico;
    private String nomeServico, tipoDeAtendimento;
    private double precoServico;
    
    public TipoServico(int numeroServico, String nomeServico, String tipoDeAtendimento, double precoServico){
        this.numeroServico = numeroServico;
        this.nomeServico = nomeServico;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.precoServico = precoServico;
    }
    
    public int getNumeroServico(){
        return numeroServico;
    }
    
    public String getNomeServico(){
        return nomeServico;
    }
    
    public String getTipoDeAtendimento(){
        return tipoDeAtendimento;
    }
    
    public double getPrecoServico(){
        return precoServico;
    }
}
