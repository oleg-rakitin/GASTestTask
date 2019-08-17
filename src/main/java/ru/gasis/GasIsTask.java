package ru.gasis;

import ru.gasis.api.UserAccountDUO;
import ru.gasis.exceptions.GasIsUserNotFoundException;

import java.sql.SQLException;

public class GasIsTask {
    public static void main(String[] args) {
        UserAccount userAccount;
        try {
            userAccount = UserAccountDUO.getUserAccount("olegr_ц");
            System.out.println(userAccount);
            userAccount.changeUserLastName("zhopa");
            UserAccountDUO.changeUserLastName("olegr_","TEST2");
            System.out.println(userAccount);
        } catch (SQLException | GasIsUserNotFoundException e) {
            e.printStackTrace();
        }

    }
}
