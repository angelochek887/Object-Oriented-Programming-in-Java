package ua.edu.sumdu.j2se.pr9;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.UUID;

public class MainApp extends Application {
    private Store store = new Store();
    private ListView<String> phoneListView = new ListView<>();
    private TextArea searchResultArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Phone Store GUI - UUID Search");

        VBox addBox = new VBox(10);
        addBox.setPadding(new Insets(10));
        addBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
        
        Label addTitle = new Label("Додати новий об'єкт:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("SmartPhone", "KeypadPhone");
        typeBox.setValue("SmartPhone");

        TextField brandInput = new TextField();
        brandInput.setPromptText("Brand (напр. Apple)");
        
        TextField modelInput = new TextField();
        modelInput.setPromptText("Model (напр. iPhone 15)");
        
        TextField storageInput = new TextField();
        storageInput.setPromptText("Storage (GB)");
        
        TextField priceInput = new TextField();
        priceInput.setPromptText("Price ($)");

        TextField extraInput = new TextField();
        extraInput.setPromptText("Camera MP (напр. 48)");

        typeBox.setOnAction(e -> {
            if (typeBox.getValue().equals("SmartPhone")) {
                extraInput.setPromptText("Camera MP (напр. 48)");
            } else {
                extraInput.setPromptText("Flashlight (true/false)");
            }
        });

        Button btnAdd = new Button("Додати");
        btnAdd.setOnAction(e -> {
            try {
                String brand = brandInput.getText();
                String model = modelInput.getText();
                int storage = Integer.parseInt(storageInput.getText());
                double price = Double.parseDouble(priceInput.getText());

                if (typeBox.getValue().equals("SmartPhone")) {
                    int cameraMp = Integer.parseInt(extraInput.getText());
                    store.addPhone(new SmartPhone(brand, model, storage, price, cameraMp));
                } else {
                    boolean hasFlash = Boolean.parseBoolean(extraInput.getText());
                    store.addPhone(new KeypadPhone(brand, model, storage, price, hasFlash));
                }
                
                updatePhoneList();
                
                brandInput.clear(); modelInput.clear(); storageInput.clear(); priceInput.clear(); extraInput.clear();
            } catch (Exception ex) {
                showAlert("Помилка вводу", "Перевірте правильність введених числових даних. Можливо пусте поле.");
            }
        });

        addBox.getChildren().addAll(addTitle, typeBox, brandInput, modelInput, storageInput, priceInput, extraInput, btnAdd);

        VBox listBox = new VBox(10);
        listBox.setPadding(new Insets(10));
        Label listTitle = new Label("Колекція (Короткий вивід):");
        phoneListView.setPrefHeight(150);
        listBox.getChildren().addAll(listTitle, phoneListView);

        VBox searchBox = new VBox(10);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
        
        Label searchTitle = new Label("Пошук за UUID:");
        TextField searchInput = new TextField();
        searchInput.setPromptText("Введіть UUID сюди...");
        Button btnSearch = new Button("Знайти");
        
        searchResultArea.setPrefHeight(100);
        searchResultArea.setEditable(false);

        btnSearch.setOnAction(e -> {
            String uuidStr = searchInput.getText();
            try {
                UUID searchId = UUID.fromString(uuidStr);
                boolean found = false;
                
                for (Phone phone : store.getPhones()) {
                    if (phone.getUuid().equals(searchId)) {
                        searchResultArea.setText("Знайдено:\n" + phone.toString());
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    searchResultArea.setText("Об'єкт з таким UUID не знайдено.");
                }
            } catch (IllegalArgumentException ex) {
                searchResultArea.setText("Помилка: Некоректний формат UUID!");
            }
        });

        searchBox.getChildren().addAll(searchTitle, searchInput, btnSearch, searchResultArea);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(addBox, listBox, searchBox);

        Scene scene = new Scene(root, 450, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updatePhoneList() {
        phoneListView.getItems().clear();
        for (Phone phone : store.getPhones()) {
            String typeName = phone.getClass().getSimpleName();
            String shortInfo = typeName + ": " + phone.getBrand() + " " + phone.getModel() + " | UUID: " + phone.getUuid().toString();
            phoneListView.getItems().add(shortInfo);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}