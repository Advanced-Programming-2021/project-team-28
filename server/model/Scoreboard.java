package server.model;

import java.util.ArrayList;
import java.util.Collections;

public class Scoreboard {
    ArrayList<User> users;

    public Scoreboard(ArrayList<User> users) {
        this.users = users;
    }

    public String toString() {
        StringBuilder scoreboardToStringBuilder = new StringBuilder();
        sortUsers();
        int upperTeamRanking = 1;
        int upperTeamScore = -1;
        for(int i=1; i <= users.size(); i++){
            if(users.get(i-1).getScore() == upperTeamScore){
                scoreboardToStringBuilder.append(upperTeamRanking);
            } else {
                upperTeamRanking = i;
                upperTeamScore = users.get(i-1).getScore();
                scoreboardToStringBuilder.append(i);
            }
            scoreboardToStringBuilder.append("- ");
            scoreboardToStringBuilder.append(users.get(i-1).getNickname());
            scoreboardToStringBuilder.append(": ");
            scoreboardToStringBuilder.append(users.get(i-1).getScore());
            scoreboardToStringBuilder.append("\n");
        }
        return scoreboardToStringBuilder.toString();
    }

    private void sortUsers() {
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size() - 1; j++) {
                if (users.get(j).getScore() < users.get(j + 1).getScore()) {
                    Collections.swap(users, j, j + 1);
                } else if (users.get(j).getScore() == users.get(j + 1).getScore() &&
                        Utilities.compareAlphabetical(users.get(j).getNickname(), users.get(j + 1).getNickname()) > 0) {
                    Collections.swap(users, j, j + 1);
                }
            }
        }
    }
}
