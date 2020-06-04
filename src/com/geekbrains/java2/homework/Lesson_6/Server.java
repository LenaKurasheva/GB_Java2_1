package com.geekbrains.java2.homework.Lesson_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter output;
    private static ServerSocket serverSocket;
    private static BufferedReader reader = null;
    private static String serverMessage = null;
    private static String clientMessage = null;
    public static final String END = "/end";



    private static boolean serverAlive = false;

    public static void receiveMessage() {

        try {
            while (serverAlive){

                clientMessage = in.readLine();
                if(clientMessage == null){
                    throw new SocketException();
                }
                System.out.println("Клиент: " + clientMessage);
            }
        } catch (SocketException e) {
            if (!serverAlive) {
                System.out.println("Сервер остановлен.");
            } else {
                System.out.println("Клиент отключился.");
                //клиент отключился -> выключаем сервер
                serverAlive = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage() {
        try {
            while (serverAlive) {
                serverMessage = reader.readLine();
                if (!serverAlive){
                    break;
                }
                if (serverMessage.equalsIgnoreCase(END)) {
                    output.write(serverMessage + "\n"); // отправляем сообщение клиенту
                    serverAlive = false;

                    output.flush();
                    break;
                }
                output.write(serverMessage + "\n"); // отправляем сообщение клиенту
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(){
        try {
            clientSocket.close();
            in.close();
            output.close();
            serverSocket.close();
            System.out.println("Соединение закрыто. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {

            serverSocket = new ServerSocket(5100);
            System.out.println("Сервер запущен, ожидаем подключения...");

            clientSocket = serverSocket.accept(); //сервер ждет подключения
            serverAlive = true;
            System.out.println("Клиент подключился");

            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            Thread threadIn = new Thread(Server::receiveMessage);
            threadIn.start();

            sendMessage();

            closeAll();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
