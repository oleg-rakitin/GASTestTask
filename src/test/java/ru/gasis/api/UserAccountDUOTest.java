package ru.gasis.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.gasis.UserAccount;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public class UserAccountDUOTest {

    private UserAccount userAccount;

    @Before
    public void initialize(){
        userAccount=new UserAccount("test","testName","testLastName",99,"testCity");
    }

    @Test
    public void getUserAccount() {
        UserAccount expectedUser=null;
        try {
            expectedUser = UserAccountDUO.getUserAccount("test");
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Users not equals",userAccount,expectedUser);
    }

    @Test
    public void changeUserLastName() {
        String expected=null;
        try {
            UserAccountDUO.changeUserLastName("test", "testLastNameNew");
             expected = UserAccountDUO.getUserAccount("test").getLastName();
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Last Name is not equals!","testLastNameNew",expected);
    }

    @After
    public void afterTest(){
        userAccount.changeUserLastName("testLastName");
    }
}