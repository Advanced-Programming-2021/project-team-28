package server.controller;

import org.model.User;
import org.model.enums.Status;
import org.view.ProfileMenuView;

import java.io.File;
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
                    controlChangePassword(commandMatchers[i].group("currentPassword"), commandMatchers[i].group("newPassword"));
                    return;
                }
            }
        }
        if(commandMatchers[1].find()){
            controlChangeNickName(commandMatchers[1].group("nickname"));
        } else if(commandMatchers[2].find()){
            controlChangeNickName(commandMatchers[2].group("nickname"));
        } else if (commandMatchers[15].find()){
            controlChangeUsername(commandMatchers[15].group("username"));
        } else if (commandMatchers[16].find()){
            controlChangeUsername(commandMatchers[16].group("username"));
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
        Pattern patternForChangeUsername1 = Pattern.compile("^profile change --username (?<username>.+?)$");
        Pattern patternForChangeUsername2 = Pattern.compile("^profile change -u (?<username>.+?)$");
        Matcher[] commandMatchers = new Matcher[17];
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
        commandMatchers[15] = patternForChangeUsername1.matcher(command);
        commandMatchers[16] = patternForChangeUsername2.matcher(command);
        return commandMatchers;
    }

    public Status controlChangeNickName(String nickname){
        if(nickname.equals(""))
            return Status.PLEASE_ENTER_DATA_FIRST;
        if (User.isNicknameAvailable(nickname)) {
            user.setNickname(nickname);
            view.nicknameChanged();
            return Status.SUCCESS;
        }
            else {
            view.nicknameExists(nickname);
            return Status.REPEATED_NICKNAME;
        }
    }

    public Status controlChangePassword(String current , String alternate){
        if(user.getPassword().equals(current)) {
            if(current.equals(alternate)) {
                view.passwordIsTheSame();
                return Status.REPEATED_PASSWORD;
            }
            user.setPassword(alternate);
            view.passwordChanged();
            return Status.SUCCESS;
        }
        else {
            view.wrongPassword();
            return Status.WRONG_PASSWORD;
        }
    }

    private void controlChangeUsername(String username){
        if (User.isUsernameAvailable(username)) {
            user.setUsername(username);
            view.usernameChanged();
        }
        else view.usernameExists(username);
    }

    public User getUser() {
        return user;
    }

    public void changeProfilePic(File selectedFile){
        user.setProfilePicturePath(selectedFile.toURI().toString());
        user.setHasChangedProfilePicture(true);
    }
}
