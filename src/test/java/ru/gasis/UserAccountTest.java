package ru.gasis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserAccountTest {

    private String accountName,firstName,lastName, city;
    private int age;
    private  UserAccount user;


    @Before
    public void initialize() {
        UserAccount userAccount = new UserAccount("testAc", "Oleg","Rakitin", 21, "SPB");
        user = new UserAccount("test", "testName","testLastName", 99, "testCity");
        accountName = userAccount.getUserAccountName();
        firstName = userAccount.getFirstName();
        lastName = userAccount.getLastName();
        age = userAccount.getAge();
        city = userAccount.getCity();
    }


    @Test
    public void getUserAccountName() {
        Assert.assertEquals("testAc",accountName);
    }

    @Test
    public void getFirstName() {
        Assert.assertEquals("Oleg", firstName);
    }

    @Test
    public void getLastName() {
        Assert.assertEquals("Rakitin",lastName);
    }

    @Test
    public void getCity() {
        Assert.assertEquals("SPB", city );
    }

    @Test
    public void getAge() {
        Assert.assertEquals(21,age);
    }

    @Test
    public void changeUserLastName() {
        user.changeUserLastName("testTest");
        String expected = user.getLastName();
        Assert.assertEquals("testTest",expected);
    }

    @After
    public void afterTest(){
        user.changeUserLastName("testLastName");
    }
}