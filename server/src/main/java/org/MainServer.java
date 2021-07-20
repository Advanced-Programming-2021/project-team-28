package org;

import org.model.*;
import org.serverController.LoginMenuController;
import org.serverController.MainMenuController;
import org.serverController.ScoreBoardController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainServer {
    private static final HashMap<String, String> TOKENS = new HashMap<>();
    private static final ArrayList<Socket> CLIENTS = new ArrayList<>();
    private static final LoginMenuController LOGIN_MENU_CONTROLLER = new LoginMenuController();
    private static final ScoreBoardController SCORE_BOARD_CONTROLLER = new ScoreBoardController();
    private static final MainMenuController MAIN_MENU_CONTROLLER = new MainMenuController();
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        MainServer server = new MainServer();
        try {
            restoreDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.initializeNetwork();
    }

    public static HashMap<String, String> getTokens() {
        return TOKENS;
    }

    private static void restoreDatabase() throws Exception {
        createResourceFileIfNeeded();
//        CreateNewCard.deserialize();
        AllCardsInitiator.fillAllCards();
        AllCardsInitiator.setPrices();
        MonsterCard.deserialize();
        SpellCard.deserialize();
        TrapCard.deserialize();
        User.deserialize();
    }

    private static void createResourceFileIfNeeded() throws IOException {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("src/UserOutput.json"));
        files.add(new File("src/MonsterCardsOutput.json"));
        files.add(new File("src/SpellCardsOutput.json"));
        files.add(new File("src/TrapCardsOutput.json"));
        files.add(new File("src/DecksOutput.json"));
//        files.add(new File("src/NewMonsterOutput.json"));
//        files.add(new File("src/NewTrapOutput.json"));
//        files.add(new File("src/NewSpellOutput.json"));

        for (File file : files)
            if (!file.exists()) {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("[]");
                writer.close();
            }

    }

    public void initializeNetwork() {
        try {
            serverSocket = new ServerSocket(1444);
            while (true) {
                Socket socket = serverSocket.accept(); //stop here while there is no client to connect
                CLIENTS.add(socket);
                System.out.println("Client connected");
                startNewThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startNewThread(Socket socket) {
        new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    String input = dataInputStream.readUTF();
                    Object result = process(input);
                    if(result == null) break;
                    objectOutputStream.reset();
                    objectOutputStream.writeObject(result);
                    objectOutputStream.flush();
                }
                dataInputStream.close();
                objectOutputStream.close();
                socket.close();
            } catch (SocketException | EOFException e){
                System.out.println("Client disconnected");
                CLIENTS.remove(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static Object process(String input) {
        Matcher[] matchers = getCommandMatchers(input);
        if (matchers[0].find()) {
           return LOGIN_MENU_CONTROLLER.controlCreateUserCommand(matchers[0].group("username"), matchers[0].group("password"), matchers[0].group("nickname"));
        } else if (matchers[1].find()) {
            return LOGIN_MENU_CONTROLLER.controlLoginUserCommand(matchers[1].group("username"), matchers[1].group("password"));
        } else if (matchers[2].find()) {
            User user = getUserByToken(matchers[2].group("token"));
            if(user == null) {
                return "";
            } else {
                return user;
            }
        }
        else if(matchers[3].find()){
            if(TOKENS.containsKey(matchers[3].group("token"))) {
                return SCORE_BOARD_CONTROLLER.returnSortedUsers();
            }
        } else if(matchers[4].find()) {
            return MAIN_MENU_CONTROLLER.logout(matchers[4].group("token"));
        }
        return "invalid";
    }

    private static Matcher[] getCommandMatchers(String command) {

        Pattern patternForCreateUser1 = Pattern.compile("^user create -u (?<username>.+?) -p (?<password>.+?) -n (?<nickname>.+?)$");
        Pattern patternForLoginUser1 = Pattern.compile("^user login -u (?<username>.+?) -p (?<password>.+?)$");
        Pattern patternForGetUser = Pattern.compile("^get user (?<token>.+?)$");
        Pattern patternForSortedUsers = Pattern.compile("^get sorted users (?<token>.+)$");
        Pattern patternForLogout = Pattern.compile("^user logout (?<token>.+?)$");
        Matcher[] commandMatchers = new Matcher[15];
        commandMatchers[0] = patternForCreateUser1.matcher(command);
        commandMatchers[1] = patternForLoginUser1.matcher(command);
        commandMatchers[2] = patternForGetUser.matcher(command);
        commandMatchers[3] = patternForSortedUsers.matcher(command);
        commandMatchers[4] = patternForLogout.matcher(command);
        return commandMatchers;
    }

    public static User getUserByToken(String token) {
        User user = User.getUserByUsername(TOKENS.get(token));
        if(user == null){
            System.out.println("User was null");
        }
        return user;
    }
}
