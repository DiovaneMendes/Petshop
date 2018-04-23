
package view_menu;

/**
 *
 * @author Diovane
 */

public class TipoServicoMenu {
    public static final int OP_ADICIONAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n======================================\n"
                + "1- Adicionar Servicos\n"
                + "2- Listar Servicos\n"
                + "0- Voltar"
                + "\n======================================");
    } 
}
