package ru.gasis.api;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.gasis.UserAccount;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public class UserAccountDAOTest {

    private UserAccount userAccount;
    private UserAccountDAO userAccountDAO;

    @BeforeTest
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
        Assert.assertEquals(userAccount, expectedUser, "Users not equals");
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
        Assert.assertEquals("testLastNameNew",expected,"Last Name is not equals!");
    }

    @Test
    public void changeUserLastNameWithObject() throws SQLException, GasIsUserNotFoundException {
        userAccountDAO.changeUserLastName(userAccount,"testTest");
        String expected = userAccount.getLastName();
        Assert.assertEquals("testTest",expected);
    }

    @AfterTest
    public void afterTest() throws SQLException, GasIsUserNotFoundException {
        userAccountDAO.changeUserLastName(userAccount,"testLastName");
    }
}