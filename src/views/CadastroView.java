package views;

import models.Cadastro;

import controllers.CadastroControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.Alert;

public class CadastroView extends BorderPane {
	private Label lbId = new Label("");
	private TextField txtEmail = new TextField("");
	private TextField txtLogradouro = new TextField("");
	private TextField txtNumero = new TextField("");
	private TextField txtBairro = new TextField("");
	private TextField txtCep = new TextField("");
	
	private CadastroControl control;
	private TableView<Cadastro> tableView = new TableView<>();
	
	public CadastroView() {
		BorderPane panePrincipal = this;
		
		try {
			control = new CadastroControl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GridPane pane = new GridPane();
		pane.add(new Label("Id: "), 0, 0);
		pane.add(lbId, 1, 0);
		pane.add(new Label("Email: "), 0, 1);
		pane.add(txtEmail, 1, 1);
		pane.add(new Label("Logradouro: "), 0, 2);
		pane.add(txtLogradouro, 1, 2);
		pane.add(new Label("Numero: "), 0, 3);
		pane.add(txtNumero, 1, 3);
		pane.add(new Label("Bairro: "), 0, 4);
		pane.add(txtBairro, 1, 4);
		pane.add(new Label("Cep: "), 0, 5);
		pane.add(txtCep, 1, 5);
		
		Button btnGravar = new Button("Salvar");
		btnGravar.setOnAction(e -> {
			try {
		        control.gravar();
		        tableView.refresh();
		        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cadastro realizado com sucesso!");
		        alert.showAndWait();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar o cadastro: " + ex.getMessage());
		        alert.showAndWait();
		    }
		});
		Button btnPesquisar = new Button("Pesuisar");
		btnPesquisar.setOnAction(e -> {
			try {
				control.pesquisarPorEmail();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> control.limparTudo());
		
		pane.add(btnGravar, 0, 6);
		pane.add(btnPesquisar, 1, 6);
		pane.add(btnLimpar, 2, 0);
		
		generateColumns();
		vincularPropriedes();
		
		panePrincipal.setTop( pane );
	    panePrincipal.setCenter(tableView);

	    try { 
	        control.pesquisarTodos();
	    } catch(Exception e) { 
	         e.printStackTrace();
	    }
	}
	public void generateColumns() { 
        TableColumn<Cadastro, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Cadastro, Integer>("id"));

        TableColumn<Cadastro, String> col2 = new TableColumn<>("Email");
        col2.setCellValueFactory(new PropertyValueFactory<Cadastro, String>("email"));

        TableColumn<Cadastro, String> col3 = new TableColumn<>("Logradouro");
        col3.setCellValueFactory(new PropertyValueFactory<Cadastro, String>("Logradouro"));

        TableColumn<Cadastro, Integer> col4 = new TableColumn<>("Numero");
        col4.setCellValueFactory(new PropertyValueFactory<Cadastro, Integer>("Numero"));

        TableColumn<Cadastro, String> col5 = new TableColumn<>("Bairro");
        col5.setCellValueFactory(new PropertyValueFactory<Cadastro, String>("Bairro"));
        
        TableColumn<Cadastro, Integer> col6 = new TableColumn<>("CEP");
        col6.setCellValueFactory(new PropertyValueFactory<Cadastro, Integer>("cep"));


        // Criar o fabricante da Celula
        Callback<TableColumn<Cadastro, Void>, TableCell<Cadastro, Void>> callback = 
            new  Callback<>() {
                @Override
                public TableCell<Cadastro, Void> call(TableColumn<Cadastro, Void> param) {
                    TableCell<Cadastro, Void> tc = new TableCell<>() { 
                        final Button btnExcluir = new Button("Apagar");
                        {
                            btnExcluir.setOnAction( 
                                e -> { 
                                    try { 
                                    	Cadastro c = tableView.getItems().get( getIndex() );
                                        control.excluir( c ); 
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

        TableColumn<Cadastro, Void> col7 = new TableColumn<>("Ações");
        col7.setCellFactory( callback );

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
        tableView.setItems( control.getLista() );

        tableView.getSelectionModel().selectedItemProperty()
        .addListener( (obs, antigo, novo) -> { 
            System.out.println( "Selecionado o contato ==> " + novo);
            control.entidadeParaTela( novo );
        });
	}

    public void vincularPropriedes() { 
        Bindings.bindBidirectional(lbId.textProperty(), control.idProperty(), 
                    (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(txtLogradouro.textProperty(), control.logradouroPrperty());
        Bindings.bindBidirectional(txtNumero.textProperty(), control.numeroProperty(), 
                (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtBairro.textProperty(), control.bairroProperty());
        Bindings.bindBidirectional(txtCep.textProperty(), control.cepProperty(), 
                (StringConverter) new IntegerStringConverter());
    }
}