package views;

import java.time.LocalDate;

import controllers.Vendedor_PessoalControl;
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
import models.Vendedor_Pessoal;

public class Vendedor_PessoalView extends Application {

    private TextField txtCadastroId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtInscricaoEstadual = new TextField();
    private TextField txtCpf = new TextField();
    private DatePicker dpDataNascimento = new DatePicker(LocalDate.now());
    private Vendedor_PessoalControl controlVendedorPessoal;
    private TableView<Vendedor_Pessoal> tableView = new TableView<>();

    public Vendedor_PessoalView() {
        controlVendedorPessoal = new Vendedor_PessoalControl();
    }

    @Override
    public void start(Stage stage) {
        BorderPane panePrincipal = new BorderPane();
        GridPane pane = new GridPane();

        // Layout dos campos
        pane.add(new Label("Cadastro ID: "), 0, 0);
        pane.add(txtCadastroId, 1, 0);
        pane.add(new Label("Nome: "), 0, 1);
        pane.add(txtNome, 1, 1);
        pane.add(new Label("Inscrição Estadual: "), 0, 2);
        pane.add(txtInscricaoEstadual, 1, 2);
        pane.add(new Label("CPF: "), 0, 3);
        pane.add(txtCpf, 1, 3);
        pane.add(new Label("Data de Nascimento: "), 0, 4);
        pane.add(dpDataNascimento, 1, 4);

        // Botões de ação
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            try {
                controlVendedorPessoal.gravar();
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
                controlVendedorPessoal.pesquisarPorCPF();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction(e -> controlVendedorPessoal.limparTudo());

        pane.add(btnSalvar, 0, 5);
        pane.add(btnPesquisar, 1, 5);
        pane.add(btnLimpar, 2, 0);

        // Configurações gerais da tabela
        generateColumns();
        vincularPropriedades();

        panePrincipal.setTop(pane);
        panePrincipal.setCenter(tableView);

        // Configuração da cena
        Scene scene = new Scene(panePrincipal, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Vendedores");
        stage.show();

        try {
            controlVendedorPessoal.pesquisarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateColumns() {
        TableColumn<Vendedor_Pessoal, Integer> col1 = new TableColumn<>("Cadastro ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("VendedorCadastro_Id"));

        TableColumn<Vendedor_Pessoal, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Vendedor_Pessoal, String> col3 = new TableColumn<>("Inscrição Estadual");
        col3.setCellValueFactory(new PropertyValueFactory<>("Inscricao_Estadual"));

        TableColumn<Vendedor_Pessoal, Integer> col4 = new TableColumn<>("CPF");
        col4.setCellValueFactory(new PropertyValueFactory<>("CPF"));

        TableColumn<Vendedor_Pessoal, LocalDate> col5 = new TableColumn<>("Data de Nascimento");
        col5.setCellValueFactory(new PropertyValueFactory<>("Data_Nascimento"));

        // Coluna de ações (Excluir)
        TableColumn<Vendedor_Pessoal, Void> col6 = new TableColumn<>("Ações");
        col6.setCellFactory(getButtonCellFactory());

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6);
        tableView.setItems(controlVendedorPessoal.getLista());

        tableView.getSelectionModel().selectedItemProperty()
            .addListener((obs, antigo, novo) -> controlVendedorPessoal.entidadeParaTela(novo));
    }

    private Callback<TableColumn<Vendedor_Pessoal, Void>, TableCell<Vendedor_Pessoal, Void>> getButtonCellFactory() {
        return param -> new TableCell<>() {
            private final Button btnExcluir = new Button("Apagar");

            {
                btnExcluir.setOnAction(e -> {
                    Vendedor_Pessoal item = getTableView().getItems().get(getIndex());
                    try {
                        controlVendedorPessoal.remover(item);
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
        Bindings.bindBidirectional(txtCadastroId.textProperty(), controlVendedorPessoal.vendedorCadastroIdProperty(),
                (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), controlVendedorPessoal.nomeProperty());
        Bindings.bindBidirectional(txtInscricaoEstadual.textProperty(), controlVendedorPessoal.inscricaoEstadualProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), controlVendedorPessoal.cpfProperty(),
                (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(dpDataNascimento.valueProperty(), controlVendedorPessoal.dataNascimentoProperty());
    }
}
