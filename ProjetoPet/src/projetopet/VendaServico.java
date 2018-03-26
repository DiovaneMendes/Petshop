/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/

package projetopet;

import java.time.LocalDate;

public class VendaServico {
    private int tipoServico;
    private LocalDate dataEHora;
    private Cliente cliente;
    private Pet pet;
    private double valorTotal;
    
    public VendaServico(){
        
    }
    
    private void calculoDeVenda(){
        
    }
}
