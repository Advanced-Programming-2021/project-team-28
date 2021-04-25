package controller;

import model.User;
import view.MainMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {

    User user ;
    MainMenuView view = new MainMenuView( this );

    MainMenuController (User user){
        this.user = user;
    }




    public void run () {
        this.view.run();
    }





    public void processCommand (String command){

        if(command.equals("menu show-current")){
            view.showMenu();
            return;
        }
        else if ( command.equals("scoreboard show")){
            //TODO : complete the functions
        }
        else if (command.startsWith("menu enter")){
            Matcher matcher = getCommandMatcher( command , "^menu enter (.+)");
            if (matcher.group(1).equals("Duel")){

            }
            if (matcher.group(1).equals("Deck")){

            }
            if (matcher.group(1).equals("Profile")){
                ProfileMenuController profile = new ProfileMenuController ( this.user );
                profile.run();
            }
            if (matcher.group(1).equals("Shop")){

            }
            if (matcher.group(1).equals("Import/Export")){

            }
        }
    }



    private static Matcher getCommandMatcher( String command , String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return matcher;
        else
            return null;
    }
}
