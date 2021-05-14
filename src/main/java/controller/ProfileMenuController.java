package controller;

import model.User;
import view.ProfileMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {

    User user;
    ProfileMenuView view = new ProfileMenuView(this);

    ProfileMenuController (User user){
        this.user = user;
    }

    public void run(){
        view.run();
    }

    public void processCommand(String command){
        Matcher[] commandMatchers = getCommandMatchers(command);
        if(command.equals("menu show-current")){
            view.showMenu();
        } else if(commandMatchers[0].find()){
            view.impossibleMenuNavigation();
        } else {
            for (int i=3; i<15; i++){
                if(commandMatchers[i].find()){
                    changePassword(commandMatchers[i].group("currentPassword"), commandMatchers[i].group("newPassword"));
                    return;
                }
            }
        }
        if(commandMatchers[1].find()){
            changeNickName(commandMatchers[1].group("nickname"));
        } else if(commandMatchers[2].find()){
            changeNickName(commandMatchers[2].group("nickname"));
        } else {
            view.invalidCommand();
        }
    }

    private Matcher[] getCommandMatchers(String command){
        Pattern patternForImpossibleMenuNavigation = Pattern.compile("^menu enter (Duel|Scoreboard|Import/Export|Shop|Deck)$");
        Pattern patternForChangeNickname1 = Pattern.compile("^profile change --nickname (?<nickname>.+?)$");
        Pattern patternForChangeNickname2 = Pattern.compile("^profile change -n (?<nickname>.+?)$");
        Pattern patternForChangePassword1 = Pattern.compile("^profile change --password --current (?<currentPassword>.+?) --new (?<newPassword>.+?)$");
        Pattern patternForChangePassword2 = Pattern.compile("^profile change --password --new (?<newPassword>.+?) --current (?<currentPassword>.+?)$");
        Pattern patternForChangePassword3 = Pattern.compile("^profile change --current (?<currentPassword>.+?) --password --new (?<newPassword>.+?)$");
        Pattern patternForChangePassword4 = Pattern.compile("^profile change --current (?<currentPassword>.+?) --new (?<newPassword>.+?) --password$");
        Pattern patternForChangePassword5 = Pattern.compile("^profile change --new (?<newPassword>.+?) --password --current (?<currentPassword>.+?)$");
        Pattern patternForChangePassword6 = Pattern.compile("^profile change --new (?<newPassword>.+?) --current (?<currentPassword>.+?) --password$");
        Pattern patternForChangePassword7 = Pattern.compile("^profile change -p -c (?<currentPassword>.+?) -n (?<newPassword>.+?)$");
        Pattern patternForChangePassword8 = Pattern.compile("^profile change -p -n (?<newPassword>.+?) -c (?<currentPassword>.+?)$");
        Pattern patternForChangePassword9 = Pattern.compile("^profile change -c (?<currentPassword>.+?) -p -n (?<newPassword>.+?)$");
        Pattern patternForChangePassword10 = Pattern.compile("^profile change -c (?<currentPassword>.+?) -n (?<newPassword>.+?) -p$");
        Pattern patternForChangePassword11 = Pattern.compile("^profile change -n (?<newPassword>.+?) -p -c (?<currentPassword>.+?)$");
        Pattern patternForChangePassword12 = Pattern.compile("^profile change -n (?<newPassword>.+?) -c (?<currentPassword>.+?) -p$");
        Matcher[] commandMatchers = new Matcher[15];
        commandMatchers[0] = patternForImpossibleMenuNavigation.matcher(command);
        commandMatchers[1] = patternForChangeNickname1.matcher(command);
        commandMatchers[2] = patternForChangeNickname2.matcher(command);
        commandMatchers[3] = patternForChangePassword1.matcher(command);
        commandMatchers[4] = patternForChangePassword2.matcher(command);
        commandMatchers[5] = patternForChangePassword3.matcher(command);
        commandMatchers[6] = patternForChangePassword4.matcher(command);
        commandMatchers[7] = patternForChangePassword5.matcher(command);
        commandMatchers[8] = patternForChangePassword6.matcher(command);
        commandMatchers[9] = patternForChangePassword7.matcher(command);
        commandMatchers[10] = patternForChangePassword8.matcher(command);
        commandMatchers[11] = patternForChangePassword9.matcher(command);
        commandMatchers[12] = patternForChangePassword10.matcher(command);
        commandMatchers[13] = patternForChangePassword11.matcher(command);
        commandMatchers[14] = patternForChangePassword12.matcher(command);
        return commandMatchers;
    }

    private void changeNickName (String nickname){
        if (User.isNicknameAvailable(nickname)) {
            user.setNickname(nickname);
            view.nicknameChanged();
        }
            else view.nicknameExists(nickname);
    }

    private void changePassword (String current , String alternate){
        if(user.getPassword().equals(current)) {
            if(current.equals(alternate)) {
                view.passwordIsTheSame();
                return;
            }
            user.setPassword(alternate);
            view.passwordChanged();
        }
        else view.wrongPassword();
    }
}
