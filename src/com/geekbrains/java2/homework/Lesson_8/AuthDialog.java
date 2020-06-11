package com.geekbrains.java2.homework.Lesson_8;

import com.geekbrains.java2.homework.Lesson_8.multiscene.ChatSceneApp;
import com.geekbrains.java2.homework.Lesson_8.multiscene.SceneFlow;
import com.geekbrains.java2.homework.Lesson_8.multiscene.Stageable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthDialog implements Stageable, Initializable {
    private Stage stage;
    public Socket socket = ChatSceneApp.getScenes().get(SceneFlow.CHAT).getSocket();

    private static final int CLOSETIME = 10000;
    public static long startTime;
    static boolean authOk;

    @FXML
    AnchorPane rootPane;

    @FXML
    TextField userName;

    @FXML
    PasswordField userPassword;

    @FXML
    Button buttonOk;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTime = System.currentTimeMillis();
        System.out.println("Timer starts.");
        new Thread(()->{
            while (!authOk) {
                if (System.currentTimeMillis() - startTime > CLOSETIME) {
                    Platform.runLater(() -> {
                        try {
                            ((Stage)rootPane.getScene().getWindow()).close();
                            socket.close();
                            System.out.println("Timer stopped, connection closed.");
                        } catch (IOException | NullPointerException e) {
//                            e.printStackTrace();
                            System.out.println("Login window is already closed");
                        }
                    });
                    break;
                }
            }
        }).start();

    }


    public void submitUserPassword(ActionEvent actionEvent) {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String authMessage = "/auth " + userName.getText() + " " + userPassword.getText();
            out.writeUTF(authMessage);
            while (true) {
                if (in.available() > 0) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.startsWith("/authOk")) {
                        System.out.println("Authorized on server");
                        authOk = true;
                        ChatSceneApp.getScenes().get(SceneFlow.CHAT).setNick(strFromServer.split("\\s")[1]);
                        break;
                    }
                    if(strFromServer.startsWith("Incorrect")){
                        System.out.println("Wrong login/password");
                    }
                }
            }
            stage.setScene(ChatSceneApp.getScenes().get(SceneFlow.CHAT).getScene());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void exit(ActionEvent actionEvent) {
        ((Stage)rootPane.getScene().getWindow()).close();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
