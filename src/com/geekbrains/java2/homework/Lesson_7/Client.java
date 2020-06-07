package com.geekbrains.java2.homework.Lesson_7;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void setAuthorized(boolean authorized) {
        Client.authorized = authorized;
    }
    public static DataOutputStream out;
    public static DataInputStream  in;
    static boolean authorized;
    static Scanner sc = new Scanner(System.in);
    private static Socket socket;
    private static String message;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Write login");
            String login = sc.next();
            System.out.println("Write password");
            String parol = sc.next();
            out.writeUTF("/auth "+ login + " " + parol);
            setAuthorized(false);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //аутентификация
                        while (true) {
                            if(in.available()>0) {
                                String strFromServer = in.readUTF();
                                if (strFromServer.startsWith("/authOk")) {
                                    setAuthorized(true);
                                    System.out.println("Authorized on server");
                                    break;
                                }
                                System.out.println(strFromServer + "\n");
                            }
                        }
                        //чтение сообщений
                        while (true) {
                            if (in.available()>0) {
                                String strFromServer = in.readUTF();
                                if (strFromServer.equalsIgnoreCase("/end")) {
                                    break;
                                }
                                System.out.println(strFromServer);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            //зачем демон?
            t.setDaemon(true);
            t.start();
            //отправка сообщений:
            sendMessages();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket!=null) socket.close();
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


   public static void sendMessages() throws IOException {
       Scanner scanner = new Scanner(System.in);
       while (true) {
           try {
               String message = scanner.nextLine();
               if (message.equals("/end")) {
                   out.writeUTF(message);
                   break;
               } else {
                   out.writeUTF(message);
               }
           } catch (NullPointerException e){
               e.printStackTrace();
           }
       }
    }
}

