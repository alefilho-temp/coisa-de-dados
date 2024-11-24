package components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.util.List;

import common.ViewController;
import views.SearchView;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;

public class AutoCompleteComponent extends VBox {

    private final TextField textField;
    private final ListView<String> suggestionListView;
    private final ObservableList<String> filteredSuggestions;
    private final Popup popup;

    private List<String> suggestions = List.of();

    public AutoCompleteComponent(String placeholder, List<String> initialSuggestions) {
        this.suggestions = initialSuggestions;

        // Initialize components
        textField = new TextField();
        suggestionListView = new ListView<>();
        filteredSuggestions = FXCollections.observableArrayList();


        popup = new Popup();

        // Style the TextField
        VBox.setVgrow(textField, javafx.scene.layout.Priority.NEVER);
        textField.setPrefHeight(30.0);
        textField.setPromptText(placeholder);
        textField.setStyle("-fx-background-radius: 10px 0px 0px 10px;");
        textField.setFont(new Font(18.0));

        // Set up the TextField
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSuggestions(newValue);
            if (!filteredSuggestions.isEmpty()) {
                suggestionListView.setPrefWidth(textField.getWidth() - 10); // Match width to TextField
                suggestionListView.setPrefHeight(Math.min(filteredSuggestions.size() * 60, 250)); // Set height based on suggestions
                double x = textField.localToScreen(0, 0).getX() + 5;
                double y = textField.localToScreen(0, 0).getY() + textField.getHeight();
                popup.show(ViewController.getStage(), x, y);
            } else {
                popup.hide();
            }
        });

        // Set up the ListView
        suggestionListView.setItems(filteredSuggestions);
        suggestionListView.setVisible(false);
        suggestionListView.setStyle("-fx-background-radius: 0px 0px 10px 10px; -fx-background-color: white; -fx-border-color: transparent; -fx-font-size: 20px; -fx-padding: 5px;");

        // Set custom cell factory for styling
        suggestionListView.setCellFactory(lv -> new SuggestionListCell());

        suggestionListView.setOnMouseClicked(event -> {
            String selectedItem = suggestionListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                popup.hide();
                ViewController.navigate(new SearchView(selectedItem));
            }
        });

        // Add components to the VBox
        getChildren().add(textField);
        popup.getContent().add(suggestionListView);
        popup.setAutoHide(true);
    }

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
                if (filteredSuggestions.size() >= 6) break; // Limit to 6 suggestions
            }
        }
        suggestionListView.setVisible(!filteredSuggestions.isEmpty());
    }

    public ObservableList<String> getFilteredSuggestions() {
        return filteredSuggestions;
    }

    public TextField getTextField() {
        return textField;
    }
}

class SuggestionListCell extends ListCell<String> {
    private static final String DEFAULT_STYLE = "-fx-background-color: transparent;";
    private static final String HOVER_STYLE = "-fx-background-color: rgba(173, 216, 230, 0.5);"; // Light blue background on hover

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            setStyle(DEFAULT_STYLE);
        } else {
            Text text = new Text(item);
            text.setFont(new Font(20)); // Set font size
            text.setCursor(Cursor.HAND);
            setGraphic(text);
            setStyle(DEFAULT_STYLE);

            // Add hover effect
            setOnMouseEntered(event -> setStyle(HOVER_STYLE));
            setOnMouseExited(event -> setStyle(DEFAULT_STYLE));
        }
    }
}
