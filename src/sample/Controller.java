package sample;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private File dir = new File(System.getProperty("user.home") + "/Desktop");

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button fxChooserBtnHandler;

    @FXML
    private Button swingChooserBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error UIManager.setLookAndFeel");
        }
    }

    @FXML
    private void fxChooserBtnHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        fileChooser.setInitialDirectory(dir);
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            System.out.println("Выбран файл: " + file);
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void swingChooserBtnHandler(ActionEvent event) {
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        Stage stage = new Stage();
        stage.setTitle("Открыть файлы");
        stage.setScene(new Scene(pane, 700, 500));
        stage.showAndWait();
    }

    private void createSwingContent(final SwingNode swingNode) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(dir);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);



//        int result = jFileChooser.showOpenDialog(new JFrame());
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = jFileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//        }

        SwingUtilities.invokeLater(() -> swingNode.setContent(jFileChooser));
    }
}
