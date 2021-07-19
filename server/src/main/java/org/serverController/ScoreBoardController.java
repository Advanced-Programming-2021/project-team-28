package org.serverController;

import org.model.User;
import org.model.Utilities;

import java.util.ArrayList;

public class ScoreBoardController {

    public ArrayList<User> returnSortedUsers(){
        ArrayList<User> sortedUsers = User.getUsers();
        sortedUsers.sort((o1, o2) -> {
            int result = Integer.compare( o2.getScore() , o1.getScore());
            if(result != 0) return result;
            else return Utilities.compareAlphabetical(o1.getUsername(), o2.getUsername());
        });
        return sortedUsers;

    }
}
