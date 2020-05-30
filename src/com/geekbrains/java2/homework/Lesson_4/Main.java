//Создать окно для клиентской части чата: большое текстовое поле для отображения переписки в центре окна.
//Однострочное текстовое поле для ввода сообщений и кнопка для отсылки сообщений на нижней панели.
//Сообщение должно отсылаться либо по нажатию кнопки на форме, либо по нажатию кнопки Enter.
//При «отсылке» сообщение перекидывается из нижнего поля в центральное.

package com.geekbrains.java2.homework.Lesson_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml")); //корневой узел, или корень (root)
        primaryStage.setTitle("Chat");
        Scene scene = new Scene(root, 300, 275); //установка сцены в окне и создане сцены
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

