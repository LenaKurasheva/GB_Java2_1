package com.geekbrains.java2.homework.Lesson_7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new AuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Server awaits clients");
                Socket socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException ex) {
            System.out.println("Server error");
        } finally {
            if(authService!=null) {
                authService.stop();
            }
        }
    }


    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    //удаляем данные повторно входящего клиента из списка clients (чтобы потом сделать subscribe с новыми данными):
    public synchronized void unsubscribeReLoggedClient( String name) {
        clients.removeIf(clientHandler -> clientHandler.getName().equals(name));
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void broadcast(String s) {
        for(ClientHandler client: clients) {
            client.sendMsg(s);
        }
    }

//    *Реализовать личные сообщения, если клиент пишет «/w nick3 Привет», то только клиенту с ником nick3
//    должно прийти сообщение «Привет».

    public synchronized void sendPrivateMsg(ClientHandler sender, String recipientName, String s){
        for(ClientHandler client: clients){
            if(client.getName().equals(recipientName)){
                client.sendMsg("from " + sender.getName() + ": " + s);
                sender.sendMsg("to " + recipientName + ": " + s);
                return;
            }
        }
        sender.sendMsg(recipientName + " is not available now.");
    }

    public synchronized boolean isNickLogged(String nick) {
        for(ClientHandler client: clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }
}
