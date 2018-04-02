
package Relatorio;

public class VendasPorMes implements Comparable<VendasPorMes>{
    private int quantidadeVenda, mes, ano;
    private double valorPorMes;
    
    public VendasPorMes(int mes, int ano, int quantidadeVenda, double valorPorMes){
        this.mes = mes;
        this.ano = ano;
        this.quantidadeVenda = quantidadeVenda;
        this.valorPorMes = valorPorMes;
    }
    
    public int getMes(){
        return mes;
    }
    
    public int getAno(){
        return ano;
    }
    
    public int getQuantidadeVenda(){
        return quantidadeVenda;
    }
    
    public double getValorPorMes(){
        return valorPorMes;
    }
    
    @Override
    public int compareTo(VendasPorMes vpm) {
        if(this.getMes() < vpm.getMes()){
            return 1;
        }
        if(this.getMes() > vpm.getMes()){
            return -1;
        }
        return 0;
    }
}
