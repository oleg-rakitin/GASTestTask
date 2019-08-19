package ru.gasis;

import ru.gasis.api.UserAccountDAO;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public class GasIsTask {
    public static void main(String[] args) {
        UserAccount olegr = null;
        UserAccount dan = null;
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        try {
            olegr = userAccountDAO.getUserAccount("olegr_");
            userAccountDAO.changeUserLastName(olegr,"RakitinTestLastName");
            userAccountDAO.changeUserLastName("dan3000","TEST2");
            dan = userAccountDAO.getUserAccount("dan3000");
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(olegr);
        System.out.println(dan);

    }
}
