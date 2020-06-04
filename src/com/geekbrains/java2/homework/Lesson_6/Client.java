package com.geekbrains.java2.homework.Lesson_6;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Socket clientSocket;
    private static String clientMessage;
    private static String serverMessage;
    private static BufferedReader reader;
    private static final String END = "/end";

    private static boolean clientAlive = false;


    public static void receiveMessage(){
        try {
            while (clientAlive){

                serverMessage = in.readLine();
                if(serverMessage == null){
                    throw new SocketException();
                }
                if (serverMessage.equals(END)) {
                    System.out.println("Сервер: " + serverMessage);
                    throw new SocketException();
                }
                System.out.println("Сервер: " + serverMessage);
            }
        }
        catch(SocketException e){
            if (clientAlive) {
                System.out.println("Сервер остановлен.");
                clientAlive = false;
            } else {
                System.out.println("Клиент отключился.");
            }
        } catch (IOException e){
            System.out.println("Сервер недоступен.");
            clientAlive = false;
        }
    }

    public static void sendMessage() {
        try {
            while (clientAlive) {
                clientMessage = reader.readLine();
                if (!clientAlive){
                    break;
                }
                if (clientMessage.equalsIgnoreCase(END)) {
                    clientAlive = false;
                    break;
                }
                out.write(clientMessage + "\n"); // отправляем сообщение серверу
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(){
        try {
            clientSocket.close();
            in.close();
            out.close();
            System.out.println("Соединение закрыто. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {

            try {
                clientSocket = new Socket("localhost", 5100);
                clientAlive = true;

                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать сообщения на сервер
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                Thread threadIn = new Thread(Client::receiveMessage);
                threadIn.start();

                sendMessage();
                closeAll();

            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
