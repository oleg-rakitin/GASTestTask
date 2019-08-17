package ru.gasis;

import ru.gasis.connector.UserAccountDUO;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class UserAccount {

    private String userAccountName,firstName,lastName,city;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (age != that.age) return false;
        if (!userAccountName.equals(that.userAccountName)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        return city.equals(that.city);
    }

    @Override
    public int hashCode() {
        int result = userAccountName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + age;
        return result;
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public UserAccount setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAccount setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAccount setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserAccount setCity(String city) {
        this.city = city;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserAccount setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userAccountName='" + userAccountName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    public UserAccount() {
    }

    public UserAccount(String userAccountName, String firstName, String lastName, int age, String city) {
        this.userAccountName = userAccountName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;

    }

    public void changeUserLastName(String lastName) {
        try {
            UserAccountDUO.changeUserLastName(this.getUserAccountName(), lastName);
            setLastName(lastName);
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
