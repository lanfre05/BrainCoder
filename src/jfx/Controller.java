package jfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Stage stage;

    @FXML
    private TextArea textArea; // Main part of the text editor
    private final FileChooser fileChooser = new FileChooser(); // File selector for opening and saving

    public void init(Stage myStage) {
        this.stage = myStage;
    }

    @FXML // Exiting application
    public void exit() {

        // Simply exit the application if the text area is empty
        if (textArea.getText().isEmpty()) {
            Platform.exit();
            return;
        }

        // User confirm the exit action
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit without saving?",
                ButtonType.YES,
                ButtonType.NO,
                ButtonType.CANCEL);
        alert.setTitle("Confirm");
        alert.showAndWait();

        // Exit the application based on the user previous choice
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        if (alert.getResult() == ButtonType.NO) {
            save();
            Platform.exit();
        }
    }

    @FXML // Saving the source code as a file
    private void save() {

        // Filtering the extension to save
        try {
            fileChooser.setTitle("Save As");
            fileChooser.getExtensionFilters().setAll(
                    new FileChooser.ExtensionFilter("Binary Files", "*.bf"),
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            // File save dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                String path = file.getAbsolutePath();
                if (!path.endsWith(".bf") && !path.endsWith(".txt")) {
                    // File extension if not provided by the user
                    file = new File(path + ".bf");
                }
                // Pasting the text area content to the selected file
                PrintWriter txt = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(txt);
                out.write(textArea.getText());
                out.close();
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("Error: " + fnf);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML // Opening and existing source code file
    public void openFile() {

        // Filtering the extensions to search
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().setAll(
                new FileChooser.ExtensionFilter("Binary Files", "*.bf"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        // Show the file open dialog
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // Clearing the editor and reading the contents of the selected file
            textArea.clear();
            readText(file);
        }
    }

    // Pasting the content from a source file to the text editor
    private void readText(File file) {
        String text;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            // Append to the text area each file line
            while ((text = buffReader.readLine()) != null) {
                textArea.appendText(text + "\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML // Creating a new source code file
    public void newFile() {
        // Clearing the editor
        textArea.clear();
    }

    @FXML // Font sizing
    public void fontSize(ActionEvent e) {

        // Getting the user choice from the menu
        String choice = ((CheckMenuItem) e.getSource()).getId();

        switch (choice) {
            case "small":
                textArea.setStyle("-fx-font-size: 14px");
                break;
            case "default":
                textArea.setStyle("-fx-font-size: 22px");
                break;
            case "large":
                textArea.setStyle("-fx-font-size: 30px");
                break;
            default:
                textArea.setStyle("-fx-font-size: 22px");
        }
    }

    @FXML // Compiling the source code
    synchronized public boolean compile(ActionEvent e) {

        return false;

    }

    @FXML // Running the source code
    synchronized public void run(ActionEvent e) {

        String code = "<<+>>";
        String exePath = "src\\c\\bf.exe";

    }

    @FXML // Both compiling and running together the source code
    public void compileRun(ActionEvent e) {
        if(compile(e)){
            run(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    } // idk, this is required even if not necessary
}
