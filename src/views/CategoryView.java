package views;

import controllers.CategoryControl;
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
import models.CategoryModel;

public class CategoryView extends BorderPane {

	private Label lblId = new Label("");
    private TextField txtName = new TextField("");
    private TextField txtColor = new TextField("");
    private TextField txtimagePath = new TextField("");
    
    private TableView<CategoryModel> tableView = new TableView<>();
    
    private CategoryControl control;
		
	public CategoryView() {
        BorderPane panePrincipal = this;
        control = new CategoryControl();

        GridPane paneForm = new GridPane();
        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lblId, 1, 0);
        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtName, 1, 1);
        paneForm.add(new Label("Cor: "), 0, 2);
        paneForm.add(txtColor, 1, 2);
        paneForm.add(new Label("Imagem (Caminho): "), 0, 3);
        paneForm.add(txtimagePath, 1, 3);

        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction( e -> {
        try {
			control.gravar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        tableView.refresh();
        });
        
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e -> { 
        try {
			control.pesquisarNome();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        });

        Button btnLimpar = new Button("*");
        btnLimpar.setOnAction( e -> control.limparTudo() );

        paneForm.add(btnGravar, 0, 5);
        paneForm.add(btnPesquisar, 1, 5);
        paneForm.add(btnLimpar, 2, 0);

        generateColumns();
        vincularPropriedes();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableView);

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
        TableColumn<CategoryModel, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<CategoryModel, Integer>("id"));

        TableColumn<CategoryModel, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<CategoryModel, String>("name"));

        TableColumn<CategoryModel, String> col3 = new TableColumn<>("Cor");
        col3.setCellValueFactory(new PropertyValueFactory<CategoryModel, String>("color"));

        TableColumn<CategoryModel, String> col4 = new TableColumn<>("ImagemCaminho");
        col4.setCellValueFactory(new PropertyValueFactory<CategoryModel, String>("imagePath"));


        // Criar o fabricante da Celula
        Callback<TableColumn<CategoryModel, Void>, TableCell<CategoryModel, Void>> callback = 
            new  Callback<>() {
                @Override
                public TableCell<CategoryModel, Void> call(TableColumn<CategoryModel, Void> param) {
                    TableCell<CategoryModel, Void> tc = new TableCell<>() { 
                        final Button btnExcluir = new Button("Apagar");
                        {
                            btnExcluir.setOnAction( 
                                e -> { 
                                    try { 
                                        CategoryModel c = tableView.getItems().get( getIndex() );
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

        TableColumn<CategoryModel, Void> col6 = new TableColumn<>("Ações");
        col6.setCellFactory( callback );

        tableView.getColumns().addAll(col1, col2, col3, col4, col6);
        tableView.setItems( control.getLista() );

        tableView.getSelectionModel().selectedItemProperty()
        .addListener( (obs, antigo, novo) -> { 
            System.out.println( "Selecionado o contato ==> " + novo);
            control.entidadeParaTela( novo );
        });
    }

    public void vincularPropriedes() { 
        Bindings.bindBidirectional(lblId.textProperty(), control.idProperty(), 
                    (StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtName.textProperty(), control.nameProperty());
        Bindings.bindBidirectional(txtColor.textProperty(), control.colorProperty());
        Bindings.bindBidirectional(txtimagePath.textProperty(), control.imagePathProperty());
    }
}