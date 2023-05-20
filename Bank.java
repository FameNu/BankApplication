import java.sql.*;
import java.util.logging.Logger;

public class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }

    public void listAccount(){
        Connection connection = BankingConnection.connect();
        String sql = "SELECT * FROM account";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet results = statement.getResultSet();
            System.out.println("\nAccount Number\tAccount Name\tBalance");
            while (results.next()) {
                System.out.println(results.getInt(1) + " " + results.getString(2) + " " + results.getDouble(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void openAccount(int accountNumber, String accountName, double balance){
        Connection connection = BankingConnection.connect();
        String sql = "INSERT INTO account(accNumber, accName, balance) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, accountName);
            preparedStatement.setDouble(3, balance);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    public void closeAccount(int accountNumber){
        Connection connection = BankingConnection.connect();
        String sql = "DELETE FROM account WHERE accNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    public void deposit(int accountNumber, double amount){
        Account account = getAccount(accountNumber);
        account.deposit(amount);

        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET balance = ? WHERE accNumber = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, accountNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    public void withdraw(int accountNumber, double amount){
        Account account = getAccount(accountNumber);
        account.withdraw(amount);

        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET balance = ? WHERE accNumber = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, accountNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    public Account getAccount(int accountNumber){
        Account account = null;
        Connection connection = BankingConnection.connect();
        String sql = "SELECT * FROM account WHERE accNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            account = new Account(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
        } catch (SQLException e) {
            Logger.getLogger(Bank.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return account;
    }
}
