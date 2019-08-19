package ru.gasis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.gasis.api.UserAccountDAO;
import ru.gasis.exceptions.GasIsUserNotFoundException;
import ru.gasis.interfaces.IUserAccountDAO;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @InjectMocks
    UserAccountDAO userAccountDAO;
    @Mock
    IUserAccountDAO iUserAccountDAO;

    @Before
    public void init() {
        userAccountDAO = mock(UserAccountDAO.class);
        iUserAccountDAO = mock(IUserAccountDAO.class);

    }

    @Test
    public void getUserAccountTest() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);

        when(userAccountDAO.getUserAccount("test")).thenReturn(new UserAccount("test", "testName", "testLastName", 99, "testCity"));
        Assert.assertEquals(userAccountDAO.getUserAccount("test"), new UserAccount("test", "testName", "testLastName", 99, "testCity"));
        doReturn(new UserAccount("test", "testName", "testLastName", 99, "testCity")).when(userAccountDAO).getUserAccount("test");
        Assert.assertEquals(userAccountDAO.getUserAccount("test"), new UserAccount("test", "testName", "testLastName", 99, "testCity"));
    }

    @Test
    public void testCallMethod() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);

        when(userAccountDAO.getUserAccount("test")).thenReturn(new UserAccount("test", "testName", "testLastName", 99, "testCity"));
        when(userAccountDAO.getUserAccount("test")).thenReturn(new UserAccount("test", "testName", "testLastName", 99, "testCity"));

        Assert.assertEquals(userAccountDAO.getUserAccount("test"), new UserAccount("test", "testName", "testLastName", 99, "testCity"));
        Assert.assertEquals(userAccountDAO.getUserAccount("test"), new UserAccount("test", "testName", "testLastName", 99, "testCity"));

        verify(userAccountDAO, atLeastOnce()).getUserAccount("test");
        verify(userAccountDAO, atLeast(2)).getUserAccount("test");
        verify(userAccountDAO, times(2)).getUserAccount("test");
        verify(userAccountDAO, never()).changeUserLastName("test", "test");
    }

    @Test(expected = Exception.class)
    public void givenNull_AddThrows() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);
        doThrow().when(userAccountDAO).changeUserLastName((UserAccount) isNull(), isA(String.class));

        userAccountDAO.changeUserLastName((UserAccount) null, "test");
    }

    @Test
    public void whenChangeUserLastNameCallRealMethodString() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);
        doCallRealMethod().when(userAccountDAO).changeUserLastName(any(String.class), any(String.class));
        userAccountDAO.changeUserLastName("test", "real");

        verify(userAccountDAO, times(1)).changeUserLastName("test", "real");
    }

    @Test
    public void whenChangeUserLastNameCallRealMethodObject() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = mock(UserAccountDAO.class);
        doCallRealMethod().when(userAccountDAO).changeUserLastName(any(UserAccount.class), any(String.class));
        UserAccount userAccount=new UserAccount("test", "testName", "testLastName", 99, "testCity");
        userAccountDAO.changeUserLastName(userAccount, "real");

        verify(userAccountDAO, times(1)).changeUserLastName(userAccount, "real");
    }

    @After
    public void afterTest() throws SQLException, GasIsUserNotFoundException {
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        userAccountDAO.changeUserLastName("test", "testLastName");
    }

}
