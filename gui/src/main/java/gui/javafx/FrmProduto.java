package gui.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FrmProduto implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label lblValorUnitario;

    @FXML
    private HBox hboSul;

    @FXML
    private BorderPane bdpPrincipal;

    @FXML
    private TextField txtNome;

    @FXML
    private GridPane grdEdicao;

    @FXML
    private FlowPane flpNorte;

    @FXML
    private TabPane tbpCentral;

    @FXML
    private ChoiceBox<String> chbBuscaPor;

    @FXML
    private TextField txtPalavraChave;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<?> tblProdutos;

    @FXML
    private Button btnExcluir;

    @FXML
    private ContextMenu ctxMenu;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblQtdeEstoque;

    @FXML
    private Tab tabProdutos;

    @FXML
    private MenuItem mniAlterar;

    @FXML
    private TextField txtQtdeEstoque;

    @FXML
    private TextField txtCodigo;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField txtValorUnitario;

    @FXML
    private Label lblPalavraChave;

    @FXML
    private Label lblBuscaPor;

    @FXML
    private Button btnAlterar;

    @FXML
    private MenuItem mniExcluir;

    @FXML
    private Tab tabEdicao;

    @FXML
    private Label lblCodigo;

    @FXML
    private Button btnNovo;

    @FXML
    void btnNovo_onAction(ActionEvent event) {
        setModoEdicao(true);
    }

    @FXML
    void btnConfirmar_onAction(ActionEvent event) {
        setModoEdicao(false);
    }

    @FXML
    void btnAlterar_onAction(ActionEvent event) {
        setModoEdicao(true);
    }

    @FXML
    void btnCancelar_onAction(ActionEvent event) {
        setModoEdicao(false);
    }

    @FXML
    void btnExcluir_onAction(ActionEvent event) {

    }

    @FXML
    void mniAlterar_onAction(ActionEvent event) {
        setModoEdicao(true);
    }

    @FXML
    void mniExcluir_onAction(ActionEvent event) {

    }

    @FXML
    void tabEdicao_onSelectionChanged(Event event) {
        if (tabEdicao.isSelected()) {
            System.out.println("Aba Edicao Selecionada");
        }
    }

    @FXML
    void txtPalavraChave_onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnPesquisar.fire();
    }

    @FXML
    void btnPesquisar_onAction(ActionEvent event) {
        tbpCentral.getSelectionModel().select(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setModoEdicao(false);
    }

    public void setModoEdicao(boolean habilitar) {
        btnNovo.setDisable(habilitar);
        btnConfirmar.setDisable(!habilitar);
        btnAlterar.setDisable(habilitar);
        mniAlterar.setDisable(habilitar);
        btnCancelar.setDisable(!habilitar);
        btnExcluir.setDisable(habilitar);
        mniExcluir.setDisable(habilitar);

    }

    public boolean isModoEdicao() {
        return btnNovo.isDisable();
    }
}
