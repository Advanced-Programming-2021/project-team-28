package controller;

import model.User;
import view.ProfileMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {

    User user;
    ProfileMenuView view = new ProfileMenuView(this);
    Matcher matcher ;

    ProfileMenuController (User user){
        this.user = user;
    }

    public void run(){
        view.run();
    }

    public void processCommand(String command){
        if(command.startsWith("profile change nickname")){
            matcher = getCommandMatcher(command , "profile change nickname (.+)" );
            changeNickName(matcher.group(1));
        }
        else if (command.startsWith("profile change password")){
            matcher = getCommandMatcher(command , "^profile change password current (.+) new (.+)$");
            if(matcher != null)
                changePassword(matcher.group(1) , matcher.group(2));
            else {
                matcher = getCommandMatcher(command ,"^profile change password new (.+) current (.+)$" );
                changePassword(matcher.group(2) , matcher.group(1));
            }
        }
        else if(command.equals("menu show-current"))
            view.showMenu();

    }




    private static Matcher getCommandMatcher(String command , String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return matcher;
        else
            return null;
    }

    private void changeNickName (String nickname){
        if (User.checkNicknameValidity(nickname)) {
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
