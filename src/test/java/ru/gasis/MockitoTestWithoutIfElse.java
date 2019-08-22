package ru.gasis;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.gasis.api.UserAccountDAO;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MockitoTestWithoutIfElse {

    UserAccountDAO userAccountDAO, spyUserAccountDAO;
    UserAccount userAccount;

    @BeforeTest
    public void init() {
        spyUserAccountDAO = spy(UserAccountDAO.class);
        userAccountDAO = mock(UserAccountDAO.class);
        userAccount = new UserAccount("test", "testName", "testLastName", 99,
                "testCity");
    }

    @Test(description = "Checks whether the received user matches the expected")
    public void testGetUserAccount() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);
        when(userAccountDAO.getUserAccount(any())).thenReturn(userAccount);
        Assert.assertEquals(userAccount, userAccountDAO.getUserAccount("test"));
        verify(userAccountDAO).getUserAccount("test");
        verifyNoMoreInteractions(userAccountDAO);
    }

    @Test(description = "For changeUserLastName(String,String). Checks the correct operation of the changeUserLastName method. \n" +
            "First, we use the tested method to change the last name of the account, then through the assert construction and" +
            " the call of the getUserAccount method, we get the account after the change and compare it with the expected " +
            "result.")
    public void testChangeUserLastNameWithString() throws SQLException, GasIsUserNotFoundException {
        spyUserAccountDAO.changeUserLastName("test", "newLastName");
        Assert.assertEquals(spyUserAccountDAO.getUserAccount("test").getLastName(), "newLastName");
        Mockito.verify(spyUserAccountDAO,atLeast(1)).changeUserLastName("test", "newLastName");
    }

    @Test(description = "For changeUserLastName(Object,String). Checks the correct operation of the changeUserLastName method. \n" +
            "First, we use the tested method to change the last name of the account, then through the assert construction and" +
            " the call of the getUserAccount method, we get the account after the change and compare it with the expected " +
            "result.")
    public void testChangeUserLastNameWithObject() throws SQLException, GasIsUserNotFoundException {
        spyUserAccountDAO.changeUserLastName(userAccount, "newLastName");
        Assert.assertEquals(spyUserAccountDAO.getUserAccount("test").getLastName(), "newLastName");
        Mockito.verify(spyUserAccountDAO).changeUserLastName(userAccount, "newLastName");
    }

    @Test(dataProvider = "changeUserLastNameWithIncorrectStringProvider" ,expectedExceptions =
            IllegalArgumentException.class,description = "Throws an Illegalargumentexception if an invalid parameter is entered")
    public void testChangeUserLastNameExceptionWithString(String userName,String lastName) throws SQLException, GasIsUserNotFoundException {
            doThrow(IllegalArgumentException.class).when(userAccountDAO).changeUserLastName((String) any(), any());
            userAccountDAO.changeUserLastName(userName, lastName);
    }

    @Test(dataProvider = "changeUserLastNameWithNullObjectProvider" ,expectedExceptions = IllegalArgumentException.class,description = "Throws an NullPointerException if the " +
            "parameter passed is null")
    public void testChangeUserLastNameExceptionWithObject(UserAccount user,String lastName) throws SQLException, GasIsUserNotFoundException {
        doThrow(IllegalArgumentException.class).when(userAccountDAO).changeUserLastName((UserAccount) any(), any());
        userAccountDAO.changeUserLastName( user, lastName);
    }

    @Test(dataProvider = "getUserAccountWithIncorrectParametersProvider",expectedExceptions = IllegalArgumentException.class,description = "Throws an IllegalArgumentException if " +
            "the parameter passed is empty or null")
    public void testGetUserAccountWithIncorrectParameters(String userName) throws SQLException, GasIsUserNotFoundException {
        doThrow(IllegalArgumentException.class).when(userAccountDAO).getUserAccount(any());
        userAccountDAO.getUserAccount(userName);
    }

    @DataProvider(name = "changeUserLastNameWithIncorrectStringProvider")
    public Object[][] changeUserLastNameWithIncorrectArguments(){
        return new Object[][]
                {{"","notExist"}, {"notExist", ""},{"notExist","notExist"}};
    }

    @DataProvider(name = "changeUserLastNameWithNullObjectProvider")
    public Object[][] changeUserLastNameWithIncorrectObject(){
        return new Object[][]
                {{null, "notExist"},{userAccount,null}};
    }


    @DataProvider(name = "getUserAccountWithIncorrectParametersProvider")
    public Object[][] getUserAccountWithIncorrectParams(){
        return new Object[][]
                {{""},{null}};
    }

    @DataProvider(name = "changeUserLastNameProvider")
    public Object[][] changeUserLastNameWithObject(){
        return new Object[][]
                {{"test","newLastName"},{userAccount,"newLastName"}};
    }

    @AfterTest
    public void afterTest() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        userAccountDAO.changeUserLastName("test", "testLastName");
    }
}
