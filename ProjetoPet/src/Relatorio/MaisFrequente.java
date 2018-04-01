
package Relatorio;

import model.Cliente;

public class MaisFrequente implements Comparable<MaisFrequente>{
    private int quantidade;
    private String cliente;
    public MaisFrequente(int quantidade, String cliente){
        this.quantidade = quantidade;
        this.cliente = cliente;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
    
    public String getCliente(){
        return cliente;
    }

    @Override
    public int compareTo(MaisFrequente mf) {
        if(this.getQuantidade() < mf.getQuantidade()){
            return 1;
        }
        if(this.getQuantidade() > mf.getQuantidade()){
            return -1;
        }
        return 0;
    }
}
