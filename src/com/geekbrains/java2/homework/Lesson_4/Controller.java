package com.geekbrains.java2.homework.Lesson_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {
    @FXML
    TextArea chatTextArea;

    @FXML
    TextField messageTextField;

    @FXML
    Button sendButton;

    public void sendMessage (ActionEvent actionEvent) {
        if (!(messageTextField.getLength() == 0)) {
            chatTextArea.setWrapText(true);
            chatTextArea.appendText("Your message: " + messageTextField.getText() + "\n");
            messageTextField.deleteText(0, messageTextField.getLength());
        }
    }
}
