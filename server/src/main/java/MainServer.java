import model.MonsterCard;
import model.SpellCard;
import model.TrapCard;
import model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainServer {

    private ServerSocket serverSocket;

    public static void main(String[] args){
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
        ArrayList<File> files= new ArrayList<>();
        files.add(new File("src/UserOutput.json"));
        files.add(new File("src/MonsterCardsOutput.json"));
        files.add(new File("src/SpellCardsOutput.json"));
        files.add(new File("src/TrapCardsOutput.json"));
        files.add(new File("src/DecksOutput.json"));
//        files.add(new File("src/NewMonsterOutput.json"));
//        files.add(new File("src/NewTrapOutput.json"));
//        files.add(new File("src/NewSpellOutput.json"));

        for (File file : files)
            if(!file.exists()){
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("[]");
                writer.close();
            }

    }

    public void initializeNetwork() {
        try {
            serverSocket = new ServerSocket(7877);
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
        Matcher[] matchers = getCommandMatchers(input);
        if(matchers[2].find()) {
            return matchers[2].group("username") + " " + matchers[2].group("password") + " " + matchers[2].group("nickname");
        } return "nashod";
    }

    private static Matcher[] getCommandMatchers(String command) {
        Pattern patternForExit = Pattern.compile("^menu exit$");
        Pattern patternForShowCurrentMenu = Pattern.compile("^menu show-current$");
        Pattern patternForCreateUser1 = Pattern.compile("^user create -u (?<username>.+?) -p (?<password>.+?) -n (?<nickname>.+?)$");
        Pattern patternForCreateUser2 = Pattern.compile("^user create -u (?<username>.+?) -n (?<nickname>.+?) -p (?<password>.+?)$");
        Pattern patternForCreateUser3 = Pattern.compile("^user create -p (?<password>.+?) -u (?<username>.+?) -n (?<nickname>.+?)$");
        Pattern patternForCreateUser4 = Pattern.compile("^user create -p (?<password>.+?) -n (?<nickname>.+?) -u (?<username>.+?)$");
        Pattern patternForCreateUser5 = Pattern.compile("^user create -n (?<nickname>.+?) -p (?<password>.+?) -u (?<username>.+?)$");
        Pattern patternForCreateUser6 = Pattern.compile("^user create -n (?<nickname>.+?) -u (?<username>.+?) -p (?<password>.+?)$");
        Pattern patternForCreateUser7 = Pattern.compile("^user create --username (?<username>.+?) --password (?<password>.+?) --nickname (?<nickname>.+?)$");
        Pattern patternForCreateUser8 = Pattern.compile("^user create --username (?<username>.+?) --nickname (?<nickname>.+?) --password (?<password>.+?)$");
        Pattern patternForCreateUser9 = Pattern.compile("^user create --password (?<password>.+?) --username (?<username>.+?) --nickname (?<nickname>.+?)$");
        Pattern patternForCreateUser10 = Pattern.compile("^user create --password (?<password>.+?) --nickname (?<nickname>.+?) --username (?<username>.+?)$");
        Pattern patternForCreateUser11 = Pattern.compile("^user create --nickname (?<nickname>.+?) --password (?<password>.+?) --username (?<username>.+?)$");
        Pattern patternForCreateUser12 = Pattern.compile("^user create --nickname (?<nickname>.+?) --username (?<username>.+?) --password (?<password>.+?)$");
        Pattern patternForLoginUser1 = Pattern.compile("^user login --username (?<username>.+?) --password (?<password>.+?)$");
        Pattern patternForLoginUser2 = Pattern.compile("^user login --password (?<password>.+?) --username (?<username>.+?)$");
        Pattern patternForLoginUser3 = Pattern.compile("^user login -u (?<username>.+?) -p (?<password>.+?)$");
        Pattern patternForLoginUser4 = Pattern.compile("^user login -p (?<password>.+?) -u (?<username>.+?)$");
        Pattern patternForEnterAnotherMenu = Pattern.compile("^menu enter (Duel|Scoreboard|Deck|Import/Export|Shop|Profile)$");
        Matcher[] commandMatchers = new Matcher[19];
        commandMatchers[0] = patternForExit.matcher(command);
        commandMatchers[1] = patternForShowCurrentMenu.matcher(command);
        commandMatchers[2] = patternForCreateUser1.matcher(command);
        commandMatchers[3] = patternForCreateUser2.matcher(command);
        commandMatchers[4] = patternForCreateUser3.matcher(command);
        commandMatchers[5] = patternForCreateUser4.matcher(command);
        commandMatchers[6] = patternForCreateUser5.matcher(command);
        commandMatchers[7] = patternForCreateUser6.matcher(command);
        commandMatchers[8] = patternForCreateUser7.matcher(command);
        commandMatchers[9] = patternForCreateUser8.matcher(command);
        commandMatchers[10] = patternForCreateUser9.matcher(command);
        commandMatchers[11] = patternForCreateUser10.matcher(command);
        commandMatchers[12] = patternForCreateUser11.matcher(command);
        commandMatchers[13] = patternForCreateUser12.matcher(command);
        commandMatchers[14] = patternForLoginUser1.matcher(command);
        commandMatchers[15] = patternForLoginUser2.matcher(command);
        commandMatchers[16] = patternForLoginUser3.matcher(command);
        commandMatchers[17] = patternForLoginUser4.matcher(command);
        commandMatchers[18] = patternForEnterAnotherMenu.matcher(command);
        return commandMatchers;
    }

}
