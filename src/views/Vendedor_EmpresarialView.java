package views;

import controllers.Vendedor_EmpresarialControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.Vendedor_Empresarial;

public class Vendedor_EmpresarialView extends Application {

    private TextField txtCadastroId = new TextField();
    private TextField txtCnpj = new TextField();
    private TextField txtRazaoSocial = new TextField();
    private TextField txtInformacaoCobranca = new TextField();
    private Vendedor_EmpresarialControl controlVendedorEmpresarial;
    private TableView<Vendedor_Empresarial> tableView = new TableView<>();

    public Vendedor_EmpresarialView() {
        controlVendedorEmpresarial = new Vendedor_EmpresarialControl();
    }

    @Override
    public void start(Stage stage) {
        BorderPane panePrincipal = new BorderPane();
        GridPane pane = new GridPane();

        // Layout dos campos
        pane.add(new Label("Cadastro ID: "), 0, 0);
        pane.add(txtCadastroId, 1, 0);
        pane.add(new Label("CNPJ: "), 0, 1);
        pane.add(txtCnpj, 1, 1);
        pane.add(new Label("Razão Social: "), 0, 2);
        pane.add(txtRazaoSocial, 1, 2);
        pane.add(new Label("Informação de Cobrança: "), 0, 3);
        pane.add(txtInformacaoCobranca, 1, 3);

        // Botões de ação
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            try {
                controlVendedorEmpresarial.gravar();
                tableView.refresh();
                new Alert(Alert.AlertType.INFORMATION, "Registro salvo com sucesso!").showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Erro ao salvar: " + ex.getMessage()).showAndWait();
            }
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
            try {
                controlVendedorEmpresarial.pesquisarPorCNPJ();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction(e -> controlVendedorEmpresarial.limparTudo());

        pane.add(btnSalvar, 0, 4);
        pane.add(btnPesquisar, 1, 4);
        pane.add(btnLimpar, 2, 4);

        // Configurações gerais da tabela
        generateColumns();
        vincularPropriedades();

        panePrincipal.setTop(pane);
        panePrincipal.setCenter(tableView);

        // Configuração da cena
        Scene scene = new Scene(panePrincipal, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Vendedores Empresariais");
        stage.show();

        try {
            controlVendedorEmpresarial.pesquisarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateColumns() {
        TableColumn<Vendedor_Empresarial, Integer> col1 = new TableColumn<>("Cadastro ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("VendedorCadastro_Id"));

        TableColumn<Vendedor_Empresarial, Integer> col2 = new TableColumn<>("CNPJ");
        col2.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));

        TableColumn<Vendedor_Empresarial, String> col3 = new TableColumn<>("Razão Social");
        col3.setCellValueFactory(new PropertyValueFactory<>("Razao_Social"));

        TableColumn<Vendedor_Empresarial, String> col4 = new TableColumn<>("Informação de Cobrança");
        col4.setCellValueFactory(new PropertyValueFactory<>("Informacao_cobranca"));

        // Coluna de ações (Excluir)
        TableColumn<Vendedor_Empresarial, Void> col5 = new TableColumn<>("Ações");
        col5.setCellFactory(getButtonCellFactory());

        tableView.getColumns().addAll(col1, col2, col3, col4, col5);
        tableView.setItems(controlVendedorEmpresarial.getLista());

        tableView.getSelectionModel().selectedItemProperty()
            .addListener((obs, antigo, novo) -> controlVendedorEmpresarial.entidadeParaTela(novo));
    }

    private Callback<TableColumn<Vendedor_Empresarial, Void>, TableCell<Vendedor_Empresarial, Void>> getButtonCellFactory() {
        return param -> new TableCell<>() {
            private final Button btnExcluir = new Button("Apagar");

            {
                btnExcluir.setOnAction(e -> {
                    Vendedor_Empresarial item = getTableView().getItems().get(getIndex());
                    try {
                        controlVendedorEmpresarial.remover(item);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnExcluir);
                }
            }
        };
    }

    private void vincularPropriedades() {
        Bindings.bindBidirectional(txtCadastroId.textProperty(), controlVendedorEmpresarial.vendedorCadastroIdProperty(),
                (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtCnpj.textProperty(), controlVendedorEmpresarial.cnpjProperty(),
                (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtRazaoSocial.textProperty(), controlVendedorEmpresarial.razaoSocialProperty());
        Bindings.bindBidirectional(txtInformacaoCobranca.textProperty(), controlVendedorEmpresarial.informacaoCobrancaProperty());
    }
}
