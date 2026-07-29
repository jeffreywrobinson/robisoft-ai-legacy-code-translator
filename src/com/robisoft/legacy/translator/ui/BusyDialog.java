package com.robisoft.legacy.translator.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BusyDialog {

    private final Stage dialogStage;
    private final ProgressIndicator progressIndicator;
    private final Button cancelButton;

    public BusyDialog(Stage owner, String message) {
        dialogStage = new Stage();
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Please Wait");

        progressIndicator = new ProgressIndicator();
        Label label = new Label(message);

        cancelButton = new Button("Cancel");

        VBox root = new VBox(12, label, progressIndicator, cancelButton);
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(300);

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
    }

    public void setOnCancel(Runnable handler) {
        cancelButton.setOnAction(e -> {
            if (handler != null) handler.run();
            close();
        });
    }

    public void show() {
        dialogStage.show();
    }

    public void close() {
        dialogStage.close();
    }
}
