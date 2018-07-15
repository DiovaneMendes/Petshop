
package petshopfx.controller;

import Model.TipoServico;
import Negocio.NegocioException;
import Negocio.TipoServicoNegocio;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import petshopfx.PetshopFX;
import petshopfx.view.PrintUtil;

/**
 * FXML Controller class
 *
 * @author Diovane
 */
public class MenuServicoController implements Initializable {    
    @FXML
    private VBox menuServico;    
    @FXML
    private TableView<TipoServico> tableViewServicos;
    @FXML
    private TableColumn<TipoServico, String> tableColumnNumero;
    @FXML
    private TableColumn<TipoServico, String> tableColumnNomeServico;
    @FXML
    private TableColumn<TipoServico, String> tableColumnTipoAtendimento;
    @FXML
    private TableColumn<TipoServico, String> tableColumnPreco;
    @FXML
    private AnchorPane painelFormularioServico;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldNome;
    @FXML
    private ChoiceBox seletorTipo;
    @FXML
    private TextField textFieldPreco;
    
    private int tela;
    private List<TipoServico> listaServicos;
    private TipoServico servicoSelecionado;
    
    private ObservableList<TipoServico> observableListaServicos;   
    
    private TipoServicoNegocio tipoServicoNegocio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoServicoNegocio = new TipoServicoNegocio();
        
        if (tableViewServicos != null) {
            carregarTableViewServicos();
        }
    }  

    private void carregarTableViewServicos() {
        tableColumnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableColumnNomeServico.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTipoAtendimento.setCellValueFactory(new PropertyValueFactory<>("tipo_atendimento"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        listaServicos = tipoServicoNegocio.listar();

        observableListaServicos = FXCollections.observableArrayList(listaServicos);
        tableViewServicos.setItems(observableListaServicos);
    }
    
    @FXML
    public void botaoCadastrar(ActionEvent event) throws IOException {
        servicoSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(PetshopFX.class.getResource("/petshopfx/view/FormularioServico.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(menuServico.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewServicos();
    }
    
    @FXML
    public void botaoEditar(ActionEvent event) throws IOException {
        TipoServico servicoSelec = tableViewServicos.getSelectionModel().getSelectedItem();
        if (servicoSelec != null) {
            FXMLLoader loader = new FXMLLoader(PetshopFX.class.getResource("/petshopfx/view/FormularioServico.fxml"));
            Parent root = (Parent) loader.load();

            MenuServicoController controller = (MenuServicoController) loader.getController();
            controller.setServicoSelecionado(servicoSelec);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(menuServico.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewServicos();
        } else {
            PrintUtil.printMessageError("Necessita selecionar um servico");
        }
    } 
    
    @FXML
    public void botaoRemover(ActionEvent event) throws IOException, NegocioException {
        TipoServico servicoSelec = tableViewServicos.getSelectionModel().getSelectedItem();
        if (servicoSelec != null) {
            try {
                tipoServicoNegocio.deletar(servicoSelec);
                this.carregarTableViewServicos();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }
        } else {
            PrintUtil.printMessageError("Necessita selecionar um servico");
        }
    }    
    
    @FXML
    public void botaoSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioServico.getScene().getWindow();
        
        if(servicoSelecionado == null){ 
            try {
                tipoServicoNegocio.salvar(new TipoServico( 
                        Integer.parseInt(textFieldNumero.getText()),
                        textFieldNome.getText(),
                        (String) seletorTipo.getValue(),
                        Double.parseDouble(textFieldPreco.getText())
                ));                
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }            
        }
        else { 
            try {
                servicoSelecionado.setNumeroServico(Integer.parseInt(textFieldNumero.getText()));                
                servicoSelecionado.setNomeServico(textFieldNome.getText());
                servicoSelecionado.setTipoDeAtendimento((String) seletorTipo.getValue());
                servicoSelecionado.setPrecoServico(Double.parseDouble(textFieldPreco.getText()));
                tipoServicoNegocio.atualizar(servicoSelecionado);
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }            
        } 
    }
    
    @FXML
    public void botaoCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioServico.getScene().getWindow();
        stage.close();
    }
    
    public TipoServico getServicoSelecionado() {
        return servicoSelecionado;
    }
    
    private void setServicoSelecionado(TipoServico servicoSelecionado){
        this.servicoSelecionado = servicoSelecionado;
        textFieldNumero.setText(String.valueOf(servicoSelecionado.getNumeroServico()));
        textFieldNome.setText(servicoSelecionado.getNomeServico());
        seletorTipo.setValue(String.valueOf(servicoSelecionado.getTipoDeAtendimento()));
        textFieldPreco.setText(String.valueOf(servicoSelecionado.getPrecoServico()));
    }   
    
    @FXML
    private void voltarMenuPrincipal(ActionEvent event) throws IOException {
        Parent painelPrincipal = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuPrincipal.fxml"));
        Stage janela = (Stage) menuServico.getScene().getWindow();
        janela.setScene(new Scene(painelPrincipal)); 
    }
}
