
package Relatorio;

public class ServicoMaisVendido implements Comparable<ServicoMaisVendido>{
    private int quantidade;
    private String nomeServico;
    
    public ServicoMaisVendido(int quantidade, String nomeServico){
        this.quantidade = quantidade;
        this.nomeServico = nomeServico;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
    
    public String getNomeServico(){
        return nomeServico;
    }

    @Override
    public int compareTo(ServicoMaisVendido smv) {
        if(this.getQuantidade() < smv.getQuantidade()){
            return 1;
        }
        if(this.getQuantidade() > smv.getQuantidade()){
            return -1;
        }
        return 0;
    }
}
