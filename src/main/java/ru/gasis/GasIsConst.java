package ru.gasis;

public class GasIsConst {

    public final static String CONNECTION_URL = "jdbc:mysql://144.76.140.143/testmyprog?user=beautifulcraft&password=I4PrEtcaQIUcb0Mydz6le";
    public final static String QUERY_GET_USER_ACCOUNT_BY_UA_NAME = "SELECT firstName, lastName, age, city FROM UserAccounts WHERE userAccountName = ?";
    //public final static String QUERY_UPDATE_LASTNAME = "INSERT INTO UserAccounts(userAccountName, firstName, lastName, age, city) VALUES (?,?,?,?,?)";
    public final static String QUERY_UPDATE_LASTNAME = "UPDATE UserAccounts SET lastName = ? WHERE userAccountName = ?";
}
