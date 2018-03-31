/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/
package projetopet;

import java.util.ArrayList;

public class VendaServico {
    private String dataEHora;
    private Cliente cliente;
    //private TipoServico tipoServico;
    private double valorTotal;
    private ArrayList<TipoServico> listaServico;
    
    public VendaServico(String dataEHora, Cliente cliente, ArrayList<TipoServico> listaServico, double valorTotal){
        this.dataEHora = dataEHora;
        this.cliente = cliente;
        this.listaServico = listaServico;
        this.valorTotal = valorTotal;
    }

    public String getDataEHora() {
        return dataEHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<TipoServico> getListaServico() {
        return listaServico;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    @Override
    public String toString(){
        return "" + cliente.getNome();
    }
}
