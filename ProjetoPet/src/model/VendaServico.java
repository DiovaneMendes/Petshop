/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VendaServico {
    private LocalDateTime dataEHora;
    private Cliente cliente;
    private double valorTotal;
    private ArrayList<TipoServico> listaServico;
    
    public VendaServico(LocalDateTime dataEHora, Cliente cliente, ArrayList<TipoServico> listaServico, double valorTotal){
        this.dataEHora = dataEHora;
        this.cliente = cliente;
        this.listaServico = listaServico;
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataEHora() {
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
