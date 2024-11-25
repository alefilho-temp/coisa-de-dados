package views;

import controllers.TelefoneControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import models.TelefoneModel;

public class TelefoneView extends Application {

    private TextField txtTelefone = new TextField("");
    private TextField txtCadastroId = new TextField("");
    
    private TableView<TelefoneModel> tableView = new TableView<>();
    
    private TelefoneControl control;
		
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panePrincipal = new BorderPane();
        control = new TelefoneControl();

        GridPane paneForm = new GridPane();
        paneForm.add(new Label("Telefone: "), 0, 0);
        paneForm.add(txtTelefone, 1, 0);
        paneForm.add(new Label("Cadastro ID: "), 0, 1);
        paneForm.add(txtCadastroId, 1, 1);

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction( e -> {
        try {
			control.gravar();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        tableView.refresh();
        });
        
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e -> { 
        try {
			control.pesquisarNumero();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
        });
        Button btnLimpar = new Button("*");
        btnLimpar.setOnAction( e -> control.limparTudo() );
        paneForm.add(btnGravar, 0, 2);
        paneForm.add(btnPesquisar, 1, 2);
        paneForm.add(btnLimpar, 2, 0);
        generateColumns();
        vincularPropriedes();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableView);

        Scene scn = new Scene(panePrincipal, 600, 400);
        stage.setScene(scn);
        stage.setTitle("Telefones");
        stage.show();
        try { 
        control.pesquisarTodos();
        } catch(Exception e) { 
            alert(AlertType.ERROR, "Erro ao pesquisar todos");
        }
    }

    public void alert(AlertType tipo, String msg) { 
        Alert alertWindow = new Alert(tipo);
        alertWindow.setHeaderText("Alerta");
        alertWindow.setContentText(msg);
        alertWindow.showAndWait();
    }

    public void generateColumns() { 
        TableColumn<TelefoneModel, Integer> col1 = new TableColumn<>("Telefone");
        col1.setCellValueFactory(new PropertyValueFactory<TelefoneModel, Integer>("Telefone"));

        TableColumn<TelefoneModel, String> col2 = new TableColumn<>("CadastroId");
        col2.setCellValueFactory(new PropertyValueFactory<TelefoneModel, String>("Cadastroid"));

        // Criar o fabricante da Celula
        Callback<TableColumn<TelefoneModel, Void>, TableCell<TelefoneModel, Void>> callback = 
            new  Callback<>() {
                @Override
                public TableCell<TelefoneModel, Void> call(TableColumn<TelefoneModel, Void> param) {
                    TableCell<TelefoneModel, Void> tc = new TableCell<>() { 
                        final Button btnExcluir = new Button("Apagar");
                        {
                            btnExcluir.setOnAction( 
                                e -> { 
                                    try { 
                                        TelefoneModel c = tableView.getItems().get( getIndex() );
                                        control.excluir( c ); 
                                    } catch (Exception err) { 
                                        alert(AlertType.ERROR, "Erro ao excluir");
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

        TableColumn<TelefoneModel, Void> col6 = new TableColumn<>("Ações");
        col6.setCellFactory( callback );

        tableView.getColumns().addAll(col1, col2, col6);
        tableView.setItems( control.getLista() );

        tableView.getSelectionModel().selectedItemProperty()
        .addListener( (obs, antigo, novo) -> { 
            System.out.println( "Selecionado o contato ==> " + novo);
	            control.entidadeParaTela( novo );
	        });
	    }

	    public void vincularPropriedes() { 
	        Bindings.bindBidirectional(txtTelefone.textProperty(), control.foneProperty(), 
	                    (StringConverter) new IntegerStringConverter());
	        Bindings.bindBidirectional(txtCadastroId.textProperty(), control.cadastroIdProperty(), 
	        			(StringConverter) new IntegerStringConverter());
	    }

	    public static void main(String[] args) {
	        Application.launch(TelefoneView.class, args);
	    }
}