
package Relatorio;

public class MaisGastam implements Comparable<MaisGastam> {
    private String cliente;
    private double valorTotal;
    
    public MaisGastam(String cliente, double valorTotal){
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }
    
    public String getCliente(){
        return cliente;
    }
    
    public double getValorTotal(){
        return valorTotal;
    }

    @Override
    public int compareTo(MaisGastam ms) {
        if(this.getValorTotal() < ms.getValorTotal()){
            return 1;
        }
        if(this.getValorTotal() > ms.getValorTotal()){
            return -1;
        }
        return 0;     
    }
}
