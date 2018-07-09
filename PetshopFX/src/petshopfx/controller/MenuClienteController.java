
package petshopfx.controller;

import petshopfx.view.PrintUtil;
import Negocio.ClienteNegocio;
import Negocio.NegocioException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import petshopfx.PetshopFX;

/**
 * FXML Controller class
 *
 * @author Diovane
 */
public class MenuClienteController implements Initializable {
    @FXML
    VBox menuCliente;
    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnRg;
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;
    @FXML
    private AnchorPane painelFormularioCliente;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldRg;
    @FXML
    private TextField textFieldTelefone;
    
    private int tela;
    private List<Cliente> listaClientes;
    private Cliente clienteSelecionado;
    
    private ObservableList<Cliente> observableListaPacientes;
    private ClienteNegocio clienteNegocio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteNegocio = new ClienteNegocio();
        
        if (tableViewClientes != null) {
            carregarTableViewClientes();
        }
    }
    
    private void carregarTableViewClientes() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        listaClientes = clienteNegocio.listar();

        observableListaPacientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListaPacientes);
    }
    
    @FXML
    private void botaoCadastrar(ActionEvent event) throws IOException {
        clienteSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(PetshopFX.class.getResource("/petshopfx/view/FormularioCliente.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(menuCliente.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewClientes();
    }
    
    @FXML
    private void botaoEditar(ActionEvent event) throws IOException, IllegalArgumentException, IllegalAccessException {
        Cliente clienteSelecionado = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            FXMLLoader loader = new FXMLLoader(PetshopFX.class.getResource("/petshopfx/view/FormularioCliente.fxml"));
            Parent root = (Parent) loader.load();

            MenuClienteController controller = (MenuClienteController) loader.getController();
            controller.setClienteSelecionado(clienteSelecionado);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(menuCliente.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewClientes();
        } else {
            PrintUtil.printMessageError("Necessita selecionar um cliente");
        }
    } 
    
    @FXML
    private void botaoRemover(ActionEvent event) throws IOException, NegocioException {
        Cliente clienteSelecionado = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            try {
                clienteNegocio.deletar(clienteSelecionado);
                this.carregarTableViewClientes();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }
        } else {
            PrintUtil.printMessageError("Necessita selecionar um cliente");
        }
    }
    
    @FXML
    public void botaoSalvar(ActionEvent event) throws IOException{
        Stage stage = (Stage) painelFormularioCliente.getScene().getWindow();
        
        if(clienteSelecionado == null){ //Se for cadastrar
            try {
                clienteNegocio.salvar(new Cliente( 
                        textFieldNome.getText(),
                        Long.parseLong(textFieldRg.getText()),
                        Long.parseLong(textFieldTelefone.getText())
                ));                
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }            
        }
        else { //se for editar
            try {
                clienteSelecionado.setNome(textFieldNome.getText());
                clienteSelecionado.setTelefone(Long.parseLong(textFieldTelefone.getText()));
                clienteNegocio.atualizar(clienteSelecionado);
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }            
        } 
    }
    
    @FXML
    public void botaoCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioCliente.getScene().getWindow();
        stage.close();
    }

    private void setClienteSelecionado(Cliente clienteSelecionado){
        this.clienteSelecionado = clienteSelecionado;
        textFieldNome.setText(clienteSelecionado.getNome());
        textFieldRg.setText(String.valueOf(clienteSelecionado.getRg()));
        textFieldTelefone.setText(String.valueOf(clienteSelecionado.getTelefone()));
    }
    
    @FXML
    private void voltarMenuPrincipal(ActionEvent event) throws IOException {
        Parent painelPrincipal = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuPrincipal.fxml"));
        Stage janela = (Stage) menuCliente.getScene().getWindow();
        janela.setScene(new Scene(painelPrincipal));
 
    }
}
