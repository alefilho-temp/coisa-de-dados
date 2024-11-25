package views;

import controllers.CuponControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.Cupon;


public class CuponView extends Application {

	private TextField txtNumero_Cupon = new TextField();
	private TextField txtPorcentagem_Desconto = new TextField();
	private TextField txtClienteCPF = new TextField();
	private TextField txtClienteCadastroid = new TextField();

	private TableView<Cupon> tableView = new TableView<>();

	private CuponControl control;

	public CuponView() {
		try {
			control = new CuponControl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane principal = new BorderPane();
		GridPane telaCadastro = new GridPane();

		Button bntGravar = new Button("Gravar");
		bntGravar.setOnAction(e -> {
			try {
				control.gravar();
				tableView.refresh();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		Button bntAtualizar = new Button("Atualizar");
		bntAtualizar.setOnAction(e -> {
			try {
				control.pesquisarPorPorcen();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> control.limparTudo());

		telaCadastro.setVgap(5);
		telaCadastro.setHgap(15);
		telaCadastro.setPadding(new Insets(15));

		telaCadastro.add(new Label("Numero do cupon: "), 0, 0);
		telaCadastro.add(txtNumero_Cupon, 1, 0);

		telaCadastro.add(new Label("Porcentagem de desconto: "), 0, 1);
		telaCadastro.add(txtPorcentagem_Desconto, 1, 1);

		telaCadastro.add(new Label("CPF/CNPJ do cliente: "), 0, 2);
		telaCadastro.add(txtClienteCPF, 1, 2);

		telaCadastro.add(new Label("ID do cliente: "), 0, 3);
		telaCadastro.add(txtClienteCadastroid, 1, 3);
		
		telaCadastro.add(bntGravar, 0, 4);
		telaCadastro.add(bntAtualizar, 1, 4);
		telaCadastro.add(btnLimpar, 2, 4);
		
		

		generateColumns();
		vincularPropriedades();

		principal.setTop(telaCadastro);
		principal.setCenter(tableView);

		Scene scn = new Scene(principal, 600, 600);
		primaryStage.setScene(scn);
		primaryStage.setTitle("Cadastro de CUPONS!");
		primaryStage.show();
	}

	public void generateColumns() {
		// 1 banco //2 control
		TableColumn<Cupon, Integer> col1 = new TableColumn<>("numero_cupon");
		col1.setCellValueFactory(new PropertyValueFactory<Cupon, Integer>("numero_cupon"));

		TableColumn<Cupon, Integer> col2 = new TableColumn<>("porcentagem_Desconto");
		col2.setCellValueFactory(new PropertyValueFactory<Cupon, Integer>("porcentagem_Desconto"));

		TableColumn<Cupon, Integer> col3 = new TableColumn<>("ClienteCPF");
		col3.setCellValueFactory(new PropertyValueFactory<Cupon, Integer>("ClienteCPF"));

		TableColumn<Cupon, Integer> col4 = new TableColumn<>("ClienteCadastroid");
		col4.setCellValueFactory(new PropertyValueFactory<Cupon, Integer>("ClienteCadastroid"));

		Callback<TableColumn<Cupon, Void>, TableCell<Cupon, Void>> callback = new Callback<>() {
			@Override
			public TableCell<Cupon, Void> call(TableColumn<Cupon, Void> param) {
				TableCell<Cupon, Void> tc = new TableCell<>() {
					final Button btnExcluir = new Button("Apagar");
					{
						btnExcluir.setOnAction(e -> {
							try {
								Cupon c = tableView.getItems().get(getIndex());
								control.excluir(c);
							} catch (Exception err) {
								System.err.println("Erro ao excluir");
							}
						});
					}

					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnExcluir);
						}
					}
				};
				return tc;
			}
		};

		TableColumn<Cupon, Void> col5 = new TableColumn<>("Ações");
		col5.setCellFactory(callback);

		tableView.getColumns().addAll(col1, col2, col3, col4, col5);
		tableView.setItems(control.getLista());

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			System.out.println("Selecionado o contato ==> " + novo);
			control.entidadeParaTela(novo);
		});

	}

	public void vincularPropriedades() {

		Bindings.bindBidirectional(txtNumero_Cupon.textProperty(), control.getNumero_cupon(),
				(StringConverter) new IntegerStringConverter());

		Bindings.bindBidirectional(txtPorcentagem_Desconto.textProperty(), control.getPorcentagem_Desconto(),
				(StringConverter) new IntegerStringConverter());

		Bindings.bindBidirectional(txtClienteCPF.textProperty(), control.getClienteCPF(),
				(StringConverter) new IntegerStringConverter());

		Bindings.bindBidirectional(txtClienteCadastroid.textProperty(), control.getClienteCadastroid(),
				(StringConverter) new IntegerStringConverter());
	}

	public static void main(String[] args) {
		Application.launch(CuponView.class, args);
	}

}