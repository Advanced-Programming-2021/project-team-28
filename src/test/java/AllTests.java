import org.controller.LoginMenuController;
import org.model.Deck;
import org.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class AllTests {

    @BeforeEach
    public void removeDatabase() {
        User.getUsers().clear();
        Deck.getAllDecks().clear();
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("src/UserOutput.json"));
        files.add(new File("src/MonsterCardsOutput.json"));
        files.add(new File("src/SpellCardsOutput.json"));
        files.add(new File("src/TrapCardsOutput.json"));
        files.add(new File("src/DecksOutput.json"));
        for (File file : files)
            if (file.exists()) {
                file.delete();
            }
    }

    @Test
    public void createUserTest() throws Exception {
        LoginMenuController controller = new LoginMenuController();
        controller.processCommand("user create -u firstUsername -n firstNickname -p firstPassword");
        controller.processCommand("user create -p password -u firstUsername -n nickname");
        controller.processCommand("user create -u reza -p mammad -n jaafar");
        controller.processCommand("user create -p reza -n mammad -u jaafar");
        controller.processCommand("user create");
        Assertions.assertEquals(3, User.getUsers().size());
        Assertions.assertNull(User.getUserByUsername("newUser"));
        Assertions.assertNull(User.getUserByNickName("nickname"));
        Assertions.assertNotNull(User.getUserByUsername("firstUsername"));
        Assertions.assertFalse(User.isNicknameAvailable("firstNickname"));
        Assertions.assertTrue(User.isPasswordCorrect("firstUsername", "firstPassword"));
        Assertions.assertEquals(User.getUserByUsername("reza"), User.getUserByNickName("jaafar"));
    }
}


