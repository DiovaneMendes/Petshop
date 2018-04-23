
package view;

import Util.Console;
import view_menu.PrincipalMenu;

/**
 *
 * @author Diovane
 */

//Executando menu principal
public class PrincipalMenuUI {
    public void executar() {
        int opcao = 0;
        do {
            System.out.println(PrincipalMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case PrincipalMenu.OP_CLIENTES:
                    new ClienteUI().executar();
                    break;
                case PrincipalMenu.OP_PETS:
                    new PetUI().executar();
                    break;
                case PrincipalMenu.OP_TIPOSERVICO:
                    new TipoServicoUI().executar();
                    break;
                case PrincipalMenu.OP_VENDAS:
                    new VendasUI().executar();
                    break;
                case PrincipalMenu.OP_RELATORIO:
                    //new HistoricoUI().executar();
                    break;
                case PrincipalMenu.OP_SAIR:
                    System.out.println("Aplicação finalizada!!!");
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        } while (opcao != PrincipalMenu.OP_SAIR);
    }
}
