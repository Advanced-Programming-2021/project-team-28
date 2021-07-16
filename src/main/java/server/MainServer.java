package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    private ServerSocket serverSocket;

    public static void main(String[] args){
        MainServer server = new MainServer();
        server.initializeNetwork();
    }

    public void initializeNetwork() {
        try {
            serverSocket = new ServerSocket(7677);
            while(true){
                Socket socket = serverSocket.accept();
                startNewThread(socket);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void startNewThread(Socket socket) {
        new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String input = dataInputStream.readUTF();
                    String result = process(input);
                    if (result.equals("")) break;
                    dataOutputStream.writeUTF(result);
                    dataOutputStream.flush();
                }
                dataInputStream.close();
                socket.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    private static String process(String input) {
        return String.valueOf(Integer.parseInt(input) * 2);
    }
}
