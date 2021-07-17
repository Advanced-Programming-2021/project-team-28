import model.MonsterCard;
import model.SpellCard;
import model.TrapCard;
import model.User;
import serverController.LoginMenuController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainServer {
    private static final LoginMenuController LOGIN_MENU_CONTROLLER = new LoginMenuController();

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

    private static void restoreDatabase() throws Exception {
        createResourceFileIfNeeded();
//        CreateNewCard.deserialize();
//        AllCardsInitiator.fillAllCards();
//        AllCardsInitiator.setPrices();
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
            serverSocket = new ServerSocket(7877);
            while (true) {
                Socket socket = serverSocket.accept(); //stop here while there is no client to connect
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
                    if (result.equals("")) break;
                    objectOutputStream.writeObject(result);
                    objectOutputStream.flush();
                }
                dataInputStream.close();
                objectOutputStream.close();
                socket.close();
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
            return LOGIN_MENU_CONTROLLER.controlLoginUserCommand(matchers[1].group("username"), matchers[1].group("password"))
        }
        return "invalid";
    }

    private static Matcher[] getCommandMatchers(String command) {

        Pattern patternForCreateUser1 = Pattern.compile("^user create -u (?<username>.+?) -p (?<password>.+?) -n (?<nickname>.+?)$");
        Pattern patternForLoginUser1 = Pattern.compile("^user login -u (?<username>.+?) -p (?<password>.+?)$");
        Matcher[] commandMatchers = new Matcher[19];
        commandMatchers[0] = patternForCreateUser1.matcher(command);
        commandMatchers[1] = patternForLoginUser1.matcher(command);
        return commandMatchers;
    }

}
