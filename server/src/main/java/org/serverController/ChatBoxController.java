package org.serverController;

import org.MainServer;
import org.model.MyObjectOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatBoxController {
    private ServerSocket chatServerSocket;
    public static ArrayList<Socket> chatBoxSockets = new ArrayList<>();
    public static HashMap<String, Socket> tokensAndSockets = new HashMap<>();
    public ObjectOutputStream objectOutputStream;

    public ServerSocket getChatServerSocket() {
        return chatServerSocket;
    }


//    public void refreshMethod() {
//        initializeNetwork();
//    }


    public void initializeNetwork() {
        try {
            chatServerSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = chatServerSocket.accept();
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//                objectOutputStream.reset();
                System.out.println("sth");
                chatBoxSockets.add(socket);
//                startRefreshThread(socket);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void startRefreshThread(Socket socket) {
//
//        new Thread(() -> {
//            try {
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//                while (true) {
//                    objectOutputStream.reset();
//                    objectOutputStream.writeObject(MainServer.getMessages());
//                    System.out.println("ferestaadam bemola");
//                    objectOutputStream.flush();
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//
//    }

    public static void addMessage(String message) {
        MainServer.getMessages().add(message);
        System.out.println(message);
    }

    public static void  refresh(){
        for (Socket socket : chatBoxSockets){
            try {

                MyObjectOutputStream objectOutputStream = new MyObjectOutputStream(socket.getOutputStream());
//                objectOutputStream.reset();
                objectOutputStream.writeObject(MainServer.getMessages());
                objectOutputStream.flush();
//                objectOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
