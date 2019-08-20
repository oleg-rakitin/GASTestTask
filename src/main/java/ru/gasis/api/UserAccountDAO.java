package ru.gasis.api;

import ru.gasis.GasIsConst;
import ru.gasis.UserAccount;
import ru.gasis.exceptions.GasIsUserNotFoundException;
import ru.gasis.interfaces.IUserAccountDAO;

import java.sql.*;

public class UserAccountDAO implements IUserAccountDAO {

    @Override
    public UserAccount getUserAccount(String userName) throws SQLException, GasIsUserNotFoundException {
        if(userName == null)
            throw new NullPointerException();
        else if( userName.equals(""))
            throw new IllegalArgumentException();

        ResultSet rs;
        try (Connection con = DriverManager.getConnection(GasIsConst.CONNECTION_URL);
             PreparedStatement preparedStatement = con.prepareStatement(GasIsConst.QUERY_GET_USER_ACCOUNT_BY_UA_NAME);) {
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                String city = rs.getString("city");

                UserAccount account = new UserAccount(userName, firstName, lastName, age, city);
                return account;
            }
        }
        throw new GasIsUserNotFoundException();
    }

    @Override
    public void changeUserLastName(String userName, String lastName) throws SQLException, GasIsUserNotFoundException,IllegalArgumentException {
        if (userName == null || lastName == null)
            throw new NullPointerException();
        else if (userName.equals("") || lastName.equals(""))
            throw new IllegalArgumentException();
        try (Connection con = DriverManager.getConnection(GasIsConst.CONNECTION_URL);
             PreparedStatement preparedStatement = con.prepareStatement(GasIsConst.QUERY_UPDATE_LASTNAME);) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, userName);

            int result = preparedStatement.executeUpdate();
            if (result != 1)
                throw new GasIsUserNotFoundException();
        }
    }

    @Override
    public void changeUserLastName(UserAccount user, String lastName) throws SQLException, GasIsUserNotFoundException {
        changeUserLastName(user.getUserAccountName(), lastName);
        user.setLastName(lastName);
    }
}
