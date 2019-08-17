package ru.gasis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    UserAccount userAccount;

    @InjectMocks
    UserAccount ua = new UserAccount();

    @Test
    public void testTest(){
        userAccount.setAge(2);
        //Mockito.doReturn(3).when(ua).getAge();
        //Mockito.when(ua.setAge(2)).;
    }

}
