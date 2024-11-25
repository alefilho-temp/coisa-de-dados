package components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Cursor;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.util.List;

import common.ViewController;
import views.SearchView;
import javafx.scene.text.Text;

/**
 * Componente de AutoComplete que fornece sugestões de preenchimento automático
 * para um campo de texto.
 * 
 * Este componente exibe uma lista de sugestões com base na entrada do usuário e
 * permite que o usuário selecione uma sugestão para navegação.
 */
public class AutoCompleteComponent extends VBox {

    private final TextField textField; // Campo de texto para entrada do usuário
    private final ListView<String> suggestionListView; // Lista de sugestões
    private final ObservableList<String> filteredSuggestions; // Sugestões filtradas
    private final Popup popup; // Popup para exibir sugestões

    private List<String> suggestions; // Lista de sugestões iniciais

    /**
     * Construtor do componente AutoComplete.
     *
     * @param placeholder Texto de espaço reservado para o campo de texto
     * @param initialSuggestions Lista de sugestões iniciais a serem exibidas
     */
    public AutoCompleteComponent(String placeholder, List<String> initialSuggestions) {
        this.suggestions = initialSuggestions;

        // Inicializa os componentes
        textField = new TextField();
        suggestionListView = new ListView<>();
        filteredSuggestions = FXCollections.observableArrayList();
        popup = new Popup();

        // Estiliza o TextField
        VBox.setVgrow(textField, javafx.scene.layout.Priority.NEVER);
        textField.setPrefHeight(30.0);
        textField.setPromptText(placeholder);
        textField.setStyle("-fx-background-radius: 10px 0px 0px 10px;");
        textField.setFont(new Font(18.0));

        // Configura o TextField
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSuggestions(newValue);
            if (!filteredSuggestions.isEmpty()) {
                suggestionListView.setPrefWidth(textField.getWidth() - 10); // Ajusta a largura da lista
                suggestionListView.setPrefHeight(Math.min(filteredSuggestions.size() * 60, 250)); // Define a altura com base nas sugestões
                try {
                    double x = textField.localToScreen(0, 0).getX() + 5;
                    double y = textField.localToScreen(0, 0).getY() + textField.getHeight();
                    popup.show(ViewController.getStage(), x, y);
                } catch (Exception e) { }
            } else {
                popup.hide();
            }
        });

        // Configura o ListView
        suggestionListView.setItems(filteredSuggestions);
        suggestionListView.setVisible(false);
        suggestionListView.setStyle("-fx-background-radius: 0px 0px 10px 10px; -fx-background-color: white; -fx-border-color: transparent; -fx-font-size: 20px; -fx-padding: 5px;");

        // Define a fábrica de células personalizada para estilização
        suggestionListView.setCellFactory(lv -> new SuggestionListCell());

        suggestionListView.setOnMouseClicked(event -> {
            String selectedItem = suggestionListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                popup.hide();
                ViewController.navigate(new SearchView(selectedItem));
            }
        });

        // Adiciona os componentes ao VBox
        getChildren().add(textField);
        popup.getContent().add(suggestionListView);
        popup.setAutoHide(true);
    }

    /**
     * Atualiza as sugestões com base na entrada do usuário.
     *
     * @param input A entrada do usuário no campo de texto
     */
    private void updateSuggestions(String input) {
        filteredSuggestions.clear();
        if (input.isEmpty()) {
            suggestionListView.setVisible(false);
            popup.hide();
            return;
        }

        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(input.toLowerCase())) {
                filteredSuggestions.add(suggestion);
                if (filteredSuggestions.size() >= 6) break; // Limita a 6 sugestões
            }
        }
        suggestionListView.setVisible(!filteredSuggestions.isEmpty());
    }

    /**
     * Obtém a lista de sugestões filtradas.
     *
     * @return Lista observável de sugestões filtradas
     */
    public ObservableList<String> getFilteredSuggestions() {
        return filteredSuggestions;
    }

    /**
     * Obtém o campo de texto do componente.
     *
     * @return O campo de texto do componente
     */
    public TextField getTextField() {
        return textField;
    }
}

/**
 * Célula personalizada para exibir sugestões na ListView.
 * 
 * Esta classe define o estilo e o comportamento das células na lista de sugestões,
 * incluindo efeitos de hover.
 */
class SuggestionListCell extends ListCell<String> {
    private static final String DEFAULT_STYLE = "-fx-background-color: transparent;";
    private static final String HOVER_STYLE = "-fx-background-color: rgba(173, 216, 230, 0.5);"; // Fundo azul claro ao passar o mouse

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            setStyle(DEFAULT_STYLE);
        } else {
            Text text = new Text(item);
            text.setFont(new Font(20)); // Define o tamanho da fonte
            text.setCursor(Cursor.HAND);
            setGraphic(text);
            setStyle(DEFAULT_STYLE);

            // Adiciona efeito de hover
            setOnMouseEntered(event -> setStyle(HOVER_STYLE));
            setOnMouseExited(event -> setStyle(DEFAULT_STYLE));
        }
    }
}
