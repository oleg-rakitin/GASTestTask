package ru.gasis;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.gasis.api.UserAccountDAO;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MockitoTests {

    public enum TypeOfInputParameter {
        FIRST_NAME_EMPTY,
        LAST_NAME_EMPTY,
        FIRST_NAME_NULL,
        LAST_NAME_NULL,
        BOTH
    }

    UserAccountDAO userAccountDAO;
    UserAccount userAccount;

    @BeforeTest
    public void init() {
        userAccountDAO = mock(UserAccountDAO.class);
        userAccount = new UserAccount("test", "testName", "testLastName", 99,
                "testCity");
    }

    @Test(description = "Checks whether the received user matches the expected")
    public void testGetUserAccount() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);
        UserAccount userAccount = new UserAccount("test", "testFirstName",
                "testLastName", 99, "testCity");
        when(userAccountDAO.getUserAccount(any())).thenReturn(userAccount);
        Assert.assertEquals(userAccount, userAccountDAO.getUserAccount("test"));
        verify(userAccountDAO).getUserAccount("test");
        verifyNoMoreInteractions(userAccountDAO);
    }

    @Test(expectedExceptions = NullPointerException.class,description = "Throws an NullPointerException if the " +
            "parameter passed is null")
    public void testGetUserAccountWithNullArgument() throws SQLException, GasIsUserNotFoundException {
        testGetUserAccountWithIncorrectParameters(TypeOfInputParameter.FIRST_NAME_NULL);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,description = "Throws an IllegalArgumentException if " +
            "the parameter passed is empty")
    public void testGetUserAccountWithEmptyArgument() throws SQLException, GasIsUserNotFoundException {
        testGetUserAccountWithIncorrectParameters(TypeOfInputParameter.FIRST_NAME_EMPTY);
    }

    @Test(expectedExceptions = GasIsUserNotFoundException.class, description = "Throws an " +
            "GasIsUserNotFoundException if an account with the same name is not found.")
     public void testChangeUserLastNameWithInvalidArguments() throws SQLException, GasIsUserNotFoundException {
        testChangeUserLastNameThrowException(TypeOfInputParameter.BOTH);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, description = "Throws an IllegalArgumentException if" +
            " the first name is entered as an empty string")
    public void testChangeUserLastNameWithInvalidUserNameArgument() throws SQLException, GasIsUserNotFoundException {
        testChangeUserLastNameThrowException(TypeOfInputParameter.FIRST_NAME_EMPTY);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, description = "Throws an IllegalArgumentException if" +
            " the last name is entered as an empty string")
    public void testChangeUserLastNameWithInvalidLastNameArgument() throws SQLException, GasIsUserNotFoundException {
        testChangeUserLastNameThrowException(TypeOfInputParameter.LAST_NAME_EMPTY);
    }

    @Test(expectedExceptions = NullPointerException.class, description = "Throws an NullPointerException if the first " +
            "name is entered as an null object")
    public void testChangeUserLastNameWithFirstNameNullArgument() throws SQLException, GasIsUserNotFoundException {
        testChangeUserLastNameThrowException(TypeOfInputParameter.FIRST_NAME_NULL);
    }

    @Test(expectedExceptions = NullPointerException.class, description = "Throws an NullPointerException if the last" +
            " name is entered as an null object")
    public void testChangeUserLastNameWithLastNameNullArgument() throws SQLException, GasIsUserNotFoundException {
        testChangeUserLastNameThrowException(TypeOfInputParameter.LAST_NAME_NULL);
    }


    private void testGetUserAccountWithIncorrectParameters(TypeOfInputParameter type) throws SQLException,
            GasIsUserNotFoundException {
        switch (type) {
            case FIRST_NAME_NULL:
                doThrow(NullPointerException.class).when(userAccountDAO).getUserAccount(isNull());
                userAccountDAO.getUserAccount(null);
                break;
            case FIRST_NAME_EMPTY:
                doThrow(IllegalArgumentException.class).when(userAccountDAO).getUserAccount(any());
                userAccountDAO.getUserAccount("");
                break;
        }
    }

    private void testChangeUserLastNameThrowException(TypeOfInputParameter type) throws SQLException,
            GasIsUserNotFoundException {
        switch (type) {
            case FIRST_NAME_EMPTY:
                doThrow(IllegalArgumentException.class).when(userAccountDAO).changeUserLastName((String) any(),any());
                userAccountDAO.changeUserLastName("", "notExist");
                break;
            case LAST_NAME_EMPTY:
                doThrow(IllegalArgumentException.class).when(userAccountDAO).changeUserLastName((String) any(),any());
                userAccountDAO.changeUserLastName("notExist", "");
                break;
            case FIRST_NAME_NULL:
                doThrow(NullPointerException.class).when(userAccountDAO).changeUserLastName((UserAccount) isNull(),any());
                userAccountDAO.changeUserLastName((UserAccount) null, "notExist");
                break;
            case LAST_NAME_NULL:
                doThrow(NullPointerException.class).when(userAccountDAO).changeUserLastName((UserAccount) any(),isNull());
                userAccountDAO.changeUserLastName(userAccount, null);
                break;
            default:
                doThrow(GasIsUserNotFoundException.class).when(userAccountDAO).changeUserLastName((String) any(),any());
                userAccountDAO.changeUserLastName("notExist", "notExist");
                break;
        }
    }

    @AfterTest
    public void afterTest() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        userAccountDAO.changeUserLastName("test", "testLastName");
    }
}
