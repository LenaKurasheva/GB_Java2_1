package com.geekbrains.java2.homework.Lesson_8;

import com.geekbrains.java2.homework.Lesson_8.multiscene.ChatSceneApp;
import com.geekbrains.java2.homework.Lesson_8.multiscene.SceneFlow;
import com.geekbrains.java2.homework.Lesson_8.multiscene.Stageable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatController implements Stageable {
    private Stage stage;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public static ObservableList<String> nickListItems;
    Date time = new Date();


    @FXML
    TextArea messageArea;

    @FXML
    TextField newMessage;

    @FXML
    Button sendButton;

    @FXML
    ListView nickList;

    public void initialize() throws IOException {
        //принимаем сообщения от сервера:
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.available()>0) {
                            String strFromServer = in.readUTF();
                            System.out.println("From server: " + strFromServer);
                            if (strFromServer.equalsIgnoreCase("/end")) {
                                break;
                            }
                            if (strFromServer.startsWith("/clients ")) {
                                nickListItems.clear();
                                nickListItems.add("All");
                                String[] parts = strFromServer.split("\\s");

                                Platform.runLater(()->{  for(int i=1; i<parts.length; i++) {
                                    if (!parts[i].equals(ChatSceneApp.getScenes().get(SceneFlow.CHAT).getNick())) nickListItems.add(parts[i]);
                                }});
                            }
                            else Platform.runLater(()->{ messageArea.appendText(new SimpleDateFormat("hh:mm:ss a ").format(time) + strFromServer + System.lineSeparator());});

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        nickListItems = FXCollections.observableArrayList();
        nickListItems.add("All");
        socket = ChatSceneApp.getScenes().get(SceneFlow.CHAT).getSocket();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        String myNick = ChatSceneApp.getScenes().get(SceneFlow.CHAT).getNick();
        while (true) {
            if(in.available()>0) {
                String strFromServer = in.readUTF();
                if (strFromServer.startsWith("/clients")) {
                    String[] parts = strFromServer.split("\\s");
                    for(int i=1; i<parts.length; i++) {
                        if (!parts[i].equals(myNick)) nickListItems.add(parts[i]);
                    }
                    System.out.println("Authorized on server");
                    nickList.setItems(nickListItems);
                    nickList.getSelectionModel().select(0);
                    break;
                }
            }
        }

    }
    // отправляем сообщения на сервер:
    public void sendMessageTypeAction(ActionEvent actionEvent) {
        int selectedIndex = (Integer) nickList.getSelectionModel().getSelectedIndices().get(0);
        String messageText = newMessage.getText().trim();
        if(!messageText.isEmpty()) {
            System.out.println("message sent: " + messageText);
            if(selectedIndex!=0) {
                messageText = "/w " + nickList.getSelectionModel().getSelectedItems().get(0) + " " +messageText;
                System.out.println("message sent: " + messageText);
                selectedIndex = 0;   //?
            }
            try {
                out.writeUTF(messageText);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newMessage.clear();
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
