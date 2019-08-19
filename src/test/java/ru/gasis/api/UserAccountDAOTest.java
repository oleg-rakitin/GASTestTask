package ru.gasis.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.gasis.UserAccount;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public class UserAccountDAOTest {

    private UserAccount userAccount;
    private UserAccountDAO userAccountDAO;

    @Before
    public void initialize(){
        userAccountDAO=new UserAccountDAO();
        userAccount=new UserAccount("test","testName","testLastName",99,"testCity");
    }


    @Test
    public void getUserAccount() {
        UserAccount expectedUser=null;
        try {
            expectedUser = userAccountDAO.getUserAccount("test");
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Users not equals",userAccount,expectedUser);
    }

    @Test
    public void changeUserLastName() {
        String expected=null;
        try {
            userAccountDAO.changeUserLastName("test", "testLastNameNew");
             expected = userAccountDAO.getUserAccount("test").getLastName();
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Last Name is not equals!","testLastNameNew",expected);
    }

    @Test
    public void changeUserLastNameWithObject() throws SQLException, GasIsUserNotFoundException {
        userAccountDAO.changeUserLastName(userAccount,"testTest");
        String expected = userAccount.getLastName();
        Assert.assertEquals("testTest",expected);
    }

    @After
    public void afterTest() throws SQLException, GasIsUserNotFoundException {
        userAccountDAO.changeUserLastName(userAccount,"testLastName");
    }
}