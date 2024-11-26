package views;

import java.time.LocalDate;
import controllers.CartaoControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import models.Cartao;

public class CartaoView extends BorderPane {

	private ComboBox<String> txtTipo_Cartao = new ComboBox<String>();
	private TextField txtNumero = new TextField();
	private TextField txtNome_Cartao = new TextField();
	private DatePicker txtData_Vencimento = new DatePicker(LocalDate.now());
	private TextField txtCodigo_Seguranca = new TextField();
	private TextField txtClienteCPF = new TextField();
	private TextField txtClienteCadastroid = new TextField();
	
	private CartaoControl controll;
	
	public CartaoView(){
		try {
			controll = new CartaoControl();
		} catch (Exception e) {
			e.printStackTrace();
		}

		BorderPane principal = this;

		GridPane telaCadastro = new GridPane();
		GridPane junto = new GridPane();
		txtTipo_Cartao.getItems().addAll("Debito", "Credito");
		
		Button bntGravar = new Button("Gravar");
		bntGravar.setOnAction( e -> {
			try {
				controll.gravar();
				tableView.refresh();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		Button bntAtualizar = new Button("Atualizar");
		bntAtualizar.setOnAction(e -> {
			try {
				controll.pesquisarPorNome();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction( e -> controll.limparTudo() );

		telaCadastro.setVgap(5);
		telaCadastro.setHgap(15);
		telaCadastro.setPadding(new Insets(15));
		junto.setHgap(10);

		telaCadastro.add(new Label("Tipo do cartao: "), 0, 0);
		telaCadastro.add(txtTipo_Cartao, 1, 0);

		telaCadastro.add(new Label("Numero do cartao: "), 0, 1);
		telaCadastro.add(txtNumero, 1, 1);

		telaCadastro.add(new Label("Nome impresso: "), 0, 2);
		telaCadastro.add(txtNome_Cartao, 1, 2);

		junto.add(new Label("Validade: "), 0, 0);
		junto.add(txtData_Vencimento, 1, 0);
		junto.add(new Label("CVV:"), 2, 0);
		junto.add(txtCodigo_Seguranca, 3, 0);
		telaCadastro.add(junto, 0, 3, 2, 1);

		telaCadastro.add(new Label("CPF/CNPJ: "), 0, 4);
		telaCadastro.add(txtClienteCPF, 1, 4);

		telaCadastro.add(new Label("Cadastro id: "), 0, 5);
		telaCadastro.add(txtClienteCadastroid, 1, 5);

		telaCadastro.add(bntGravar, 0, 7);
		telaCadastro.add(bntAtualizar, 1, 7);
		telaCadastro.add(btnLimpar, 2, 7);

		generateColumns();
		vincularPropriedades();
		principal.setTop(telaCadastro);
		principal.setCenter(tableView);
		
		try { 
	        controll.pesquisarTodos();
	    } catch(Exception e) { 
	         e.printStackTrace();
	    }
	}

	private TableView<Cartao> tableView = new TableView<>();

	public void generateColumns() {

		TableColumn<Cartao, String> col1 = new TableColumn<>("Tipo");
		col1.setCellValueFactory(new PropertyValueFactory<Cartao, String>("Tipo_Cartao"));

		TableColumn<Cartao, Integer> col2 = new TableColumn<>("Numero");
		col2.setCellValueFactory(new PropertyValueFactory<Cartao, Integer>("Numero"));

		TableColumn<Cartao, String> col3 = new TableColumn<>("Nome_Cartao");
		col3.setCellValueFactory(new PropertyValueFactory<Cartao, String>("Nome_Cartao"));

		TableColumn<Cartao, LocalDate> col4 = new TableColumn<>("Data_Vencimento");
		col4.setCellValueFactory(new PropertyValueFactory<Cartao, LocalDate>("Data_Vencimento"));

		TableColumn<Cartao, Integer> col5 = new TableColumn<>("Codigo_Seguranca");
		col5.setCellValueFactory(new PropertyValueFactory<Cartao, Integer>("Codigo_Seguranca"));

		TableColumn<Cartao, Integer> col6 = new TableColumn<>("ClienteCPF");
		col6.setCellValueFactory(new PropertyValueFactory<Cartao, Integer>("ClienteCPF"));

		TableColumn<Cartao, Integer> col7 = new TableColumn<>("ClienteCadastroId");
		col7.setCellValueFactory(new PropertyValueFactory<Cartao, Integer>("ClienteCadastroid"));


		Callback<TableColumn<Cartao, Void>, TableCell<Cartao, Void>> callback = new Callback<>() {
			@Override
			public TableCell<Cartao, Void> call(TableColumn<Cartao, Void> param) {
				TableCell<Cartao, Void> tc = new TableCell<>() {
					final Button btnExcluir = new Button("Apagar");
					{
						btnExcluir.setOnAction(e -> {
							try {
								Cartao c = tableView.getItems().get(getIndex());
								controll.excluir(c);
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
		
	       TableColumn<Cartao, Void> col8 = new TableColumn<>("Ações");
	        col8.setCellFactory( callback );

	        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
	        tableView.setItems( controll.getLista() );

	        tableView.getSelectionModel().selectedItemProperty()
	        .addListener( (obs, antigo, novo) -> { 
	            System.out.println( "Selecionado o contato ==> " + novo);
	            controll.entidadeParaTela( novo );
	        });

	}

	public void vincularPropriedades() {
		
		Bindings.bindBidirectional(txtTipo_Cartao.valueProperty() , controll.getTipo_Cartao());
		
		Bindings.bindBidirectional(txtNumero.textProperty() , controll.getNumero(), (StringConverter) new IntegerStringConverter());
		
		Bindings.bindBidirectional(txtNome_Cartao.textProperty() , controll.getNome_Cartao());
		
		Bindings.bindBidirectional(txtData_Vencimento.valueProperty() , controll.getData_Vencimento());
		
		Bindings.bindBidirectional(txtCodigo_Seguranca.textProperty() , controll.getCodigo_Seguranca(), (StringConverter) new IntegerStringConverter());
		
		Bindings.bindBidirectional(txtClienteCPF.textProperty() , controll.getClienteCPF(), (StringConverter) new IntegerStringConverter());
		
		Bindings.bindBidirectional(txtClienteCadastroid.textProperty() , controll.getClienteCadastroid(), (StringConverter) new IntegerStringConverter());

	}

}