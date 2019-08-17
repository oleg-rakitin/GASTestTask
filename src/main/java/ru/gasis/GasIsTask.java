package ru.gasis;

import ru.gasis.api.UserAccountDUO;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public class GasIsTask {
    public static void main(String[] args) {
        UserAccount olegr = null;
        UserAccount dan = null;
        try {
            olegr = UserAccountDUO.getUserAccount("olegr_");
            olegr.changeUserLastName("Ракитин");
            UserAccountDUO.changeUserLastName("dan3000","TEST2");
            dan = UserAccountDUO.getUserAccount("dan3000");
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(olegr);
        System.out.println(dan);

    }
}
