
package view_menu;

/**
 *
 * @author Diovane
 */

public class TipoServicoMenu{
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Servico\n"
                + "2- Deletar Servico\n"
                + "3- Atualizar dados do Servico\n"
                + "4- Listar Servicos\n"
                + "5- Consultar Servicos por Nome\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }
}
