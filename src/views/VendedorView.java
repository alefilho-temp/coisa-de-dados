package views;

import models.Vendedor;
import controllers.VendedorControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VendedorView extends BorderPane {
	private TextField txtId = new TextField("");
	private TextField txtNome = new TextField("");
	private TextField txtInformacao = new TextField("");
	
	private VendedorControl control;
	private TableView<Vendedor> tableView = new TableView<>();
	
	public VendedorView() {
		BorderPane panePrincipal = this;
		
		try {
			control = new VendedorControl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GridPane pane = new GridPane();
		pane.add(new Label("Id: "), 0, 0);
		pane.add(txtId, 1, 0);
		pane.add(new Label("Nome da loja: : "), 0, 1);
		pane.add(txtNome, 1, 1);
		pane.add(new Label("Informações da loja: "), 0, 2);
		pane.add(txtInformacao, 1, 2);
		
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
        TableColumn<Vendedor, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Vendedor, Integer>("cadastroId"));

        TableColumn<Vendedor, String> col2 = new TableColumn<>("Nome_Loja");
        col2.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("nomeLoja"));

        TableColumn<Vendedor, String> col3 = new TableColumn<>("Inforamções");
        col3.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("informacaoLoja"));


        // Criar o fabricante da Celula
        Callback<TableColumn<Vendedor, Void>, TableCell<Vendedor, Void>> callback = 
            new  Callback<>() {
                @Override
                public TableCell<Vendedor, Void> call(TableColumn<Vendedor, Void> param) {
                    TableCell<Vendedor, Void> tc = new TableCell<>() { 
                        final Button btnExcluir = new Button("Apagar");
                        {
                            btnExcluir.setOnAction( 
                                e -> { 
                                    try { 
                                    	Vendedor v = tableView.getItems().get( getIndex() );
                                        control.excluir( v ); 
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

        TableColumn<Vendedor, Void> col4 = new TableColumn<>("Ações");
        col4.setCellFactory( callback );

        tableView.getColumns().addAll(col1, col2, col3, col4);
        tableView.setItems( control.getLista() );

        tableView.getSelectionModel().selectedItemProperty()
        .addListener( (obs, antigo, novo) -> { 
            System.out.println( "Selecionado o vendedor ==> " + novo);
            control.entidadeParaTela( novo );
        });
    }

    public void vincularPropriedes() { 
        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), 
                    (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtInformacao.textProperty(), control.informacaoProperty());
    }
}