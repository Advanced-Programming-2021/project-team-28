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
        if(command.equals("profile change nickname")){
            Matcher matcher = getCommandMatcher(command , "profile change nickname (.+)" );
            changeNickName(matcher.group(1));
        }
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
        if (User.checkNicknameValidity(nickname))
            user.setNickname(nickname);
            else view.nicknameExists(nickname);
    }
}
