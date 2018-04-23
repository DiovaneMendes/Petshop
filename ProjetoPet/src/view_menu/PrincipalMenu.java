
package view_menu;

/**
 *
 * @author Diovane
 */

//Menu principal da aplicação
public class PrincipalMenu {
    public static final int OP_CLIENTES = 1;
    public static final int OP_PETS = 2;
    public static final int OP_TIPOSERVICO= 3;
    public static final int OP_VENDAS = 4;
    public static final int OP_RELATORIO= 5;
    public static final int OP_SAIR = 0;
    
    //Mostra as opções
    public static String getOpcoes() {
        return ("\n======================================\n"
                + "1- Menu Clientes\n"
                + "2- Menu Pets\n"
                + "3- Menu Servicos\n"
                + "4- Menu Vendas\n"
                + "5- Menu Relatorio\n"
                + "0- Sair da Aplicação"
                + "\n======================================");
    }
} 
