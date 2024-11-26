package views;
import java.time.LocalDate;

import controllers.controlCliente;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.Cadastro;
import models.Cliente;
import service.ClienteException;

public class ClienteView extends BorderPane {
    private Label lblId = new Label("");;

	private TextField cpf;
	private TextField usuario;
	private TextField nome;
	private TextField carrinhoid;
	private TextField cadastroId;
	private ComboBox<String> genero;
    private DatePicker nascimento;
	private controlCliente controlCliente;
    private TableView<Cliente> tableView = new TableView<>();

	public  ClienteView() {
	

		controlCliente = new controlCliente();
		cpf = new TextField();
		usuario = new TextField();
		nome = new TextField();
		cadastroId = new TextField();
		genero = new ComboBox<>();
		nascimento = new DatePicker(LocalDate.now());	
		carrinhoid = new TextField();
		genero.getItems().addAll("Masculino","Feminino","Outro","Prefiro nao Informar");
		
		BorderPane tela = this;
		VBox layout = new VBox(15); 
		HBox botoes = new HBox(50);
		 Button btnLimpar = new Button("*");
	        btnLimpar.setOnAction( e -> 
	        controlCliente.limparTudo() );
		HBox linha1 = new HBox();
		linha1.getChildren().addAll(new Label("CPF : "), cpf, btnLimpar);
		HBox linha2 = new HBox();
		linha2.getChildren().addAll(new Label("Nome de Usuario : "), usuario);
		HBox linha3 = new HBox();
		linha3.getChildren().addAll(new Label("Nome Completo"), nome);
		HBox linha4 = new HBox();
		linha4.getChildren().addAll(new Label("Genero : "), genero);
		HBox linha5 = new HBox();
		linha5.getChildren().addAll(new Label("Data de Nascimento : "), nascimento);		
		HBox linha6 = new HBox();
		linha6.getChildren().addAll(new Label("Id do carrinho : "), carrinhoid);
		HBox linha7 = new HBox();
		linha7.getChildren().addAll(new Label("Cadastro Id: "), cadastroId);
		
		Button Salvar = new Button("Salvar");
		Salvar.setOnAction(e -> {
			try {
				controlCliente.gravar();
			} catch (ClienteException e1) {
				e1.printStackTrace();
			}
		});
		Button Pesquisar = new Button("Pesquisar");
		Pesquisar.setOnAction(e -> {
			try {
				controlCliente.pesquisar();
			} catch (ClienteException e1) {
				e1.printStackTrace();
			}
		});
		Button Atualizar = new Button("Atualizar");
		Atualizar.setOnAction(e -> {
			try {
				controlCliente.atualizar();
			} catch (ClienteException e1) {
				e1.printStackTrace();
			}
		});
		botoes.getChildren().addAll(Salvar, Pesquisar, Atualizar);
		layout.getChildren().addAll(linha1, linha2, linha3 ,linha4, linha5, linha6, linha7);
		tela.setTop(layout);
		tela.setCenter(botoes);
		tela.setBottom(tableView);
		vincularPropriedes() ;
		Gerercoluna();

		try { 
	        controlCliente.pesquisarTodos();
	        } catch(ClienteException e) { 
	        	e.printStackTrace();
	        }
	}
	
	
	  private void Gerercoluna() {
		  TableColumn<Cliente, Integer> col1 = new TableColumn<>("CPF");
	        col1.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("cpf"));

	        TableColumn<Cliente, String> col2 = new TableColumn<>("Nome_Usuario");
	        col2.setCellValueFactory(new PropertyValueFactory<Cliente, String>("usuario"));

	        TableColumn<Cliente, String> col3 = new TableColumn<>("Nome");
	        col3.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));

	        TableColumn<Cliente, String> col4 = new TableColumn<>("Genero");
	        col4.setCellValueFactory(new PropertyValueFactory<Cliente, String>("genero"));

	        TableColumn<Cliente, LocalDate> col5 = new TableColumn<>("Data_Nascimento");
	        col5.setCellValueFactory(new PropertyValueFactory<Cliente, LocalDate>("nascimento"));		
	
	        TableColumn<Cliente, Integer> col6 = new TableColumn<>("CadastroId");
	        col6.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("cadastroId"));
	        
	        TableColumn<Cliente, Integer> col7 = new TableColumn<>("CarrinhoCodigo");
	        col7.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("carrinhoid"));

	        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> callback = 
	                new  Callback<>() {
	                    @Override
	                    public TableCell<Cliente, Void> call(TableColumn<Cliente, Void> param) {
	                        TableCell<Cliente, Void> tc = new TableCell<>() { 
	                            final Button btnExcluir = new Button("Apagar");
	                            {
	                                btnExcluir.setOnAction( 
	                                    e -> { 
	                                        try { 
	                                        	Cliente c = tableView.getItems().get( getIndex() );
	                                        	controlCliente.remover( c ); 
	                                        } catch (Exception err) { 
	                                            err.printStackTrace();
	                                        }  
	                                    }
	                                );
	                            }
	                            public void updateItem(Void item, boolean empty) { 
	                                super.updateItem(item, empty);
	                                if (empty) { 
	                                    setGraphic( null );
	                                } else { 
	                                    setGraphic( btnExcluir );
	                                }
	                            }
	                        };
	                        return tc;
	                    } 
	            };

	            TableColumn<Cliente, Void> col8 = new TableColumn<>("Ações");
	            col8.setCellFactory( callback );

	            tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
	            tableView.setItems( controlCliente.getLista() );

	            tableView.getSelectionModel().selectedItemProperty()
	            .addListener( (obs, antigo, novo) -> { 
	                System.out.println( "Selecionado o contato ==> " + novo);
	                controlCliente.entidadeParaTela( novo );
	            });
	        }
	  
	public void vincularPropriedes() { 
	        Bindings.bindBidirectional(cpf.textProperty(), controlCliente.cpfProperty(), 
	                    (StringConverter) new IntegerStringConverter());
	        Bindings.bindBidirectional(usuario.textProperty(), controlCliente.usuarioProperty());
	        Bindings.bindBidirectional(nome.textProperty(), controlCliente.nomeProperty());
	        Bindings.bindBidirectional(genero.valueProperty(), controlCliente.generoProperty());
	        Bindings.bindBidirectional(nascimento.valueProperty(), controlCliente.nascimentoProperty());
	        Bindings.bindBidirectional(cadastroId.textProperty(), controlCliente.cadastroProperty(), 
                    (StringConverter) new IntegerStringConverter());
	        Bindings.bindBidirectional(carrinhoid.textProperty(), controlCliente.carrinhoidProperty(), 
                    (StringConverter) new IntegerStringConverter());
	  }

}