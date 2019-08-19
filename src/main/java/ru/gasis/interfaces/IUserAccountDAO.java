package ru.gasis.interfaces;

import ru.gasis.UserAccount;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public interface IUserAccountDAO {

    UserAccount getUserAccount(String userName) throws SQLException, GasIsUserNotFoundException;

    void changeUserLastName(String userName, String lastName) throws SQLException, GasIsUserNotFoundException;

    void changeUserLastName(UserAccount user, String lastName) throws SQLException, GasIsUserNotFoundException;

}
