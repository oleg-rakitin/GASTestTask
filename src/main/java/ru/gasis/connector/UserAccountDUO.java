package ru.gasis.connector;

import ru.gasis.GasIsConst;
import ru.gasis.exceptions.GasIsUserNotFoundException;
import ru.gasis.UserAccount;

import java.sql.*;

public class UserAccountDUO {

    public static UserAccount getUserAccount(String userName) throws SQLException , GasIsUserNotFoundException {
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

    public static void changeUserLastName(String userName, String lastName) throws SQLException, GasIsUserNotFoundException {
        try (Connection con = DriverManager.getConnection(GasIsConst.CONNECTION_URL);
             PreparedStatement preparedStatement = con.prepareStatement(GasIsConst.QUERY_UPDATE_LASTNAME);) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, userName);

            int result = preparedStatement.executeUpdate();
            if (result != 1)
                throw new GasIsUserNotFoundException();
        }
    }
}
