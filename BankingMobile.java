import java.util.Random;
import java.util.Scanner;

public class BankingMobile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0, accountNumber;
        double amount, balance;
        String accountName;

        Bank bank = new Bank("KrugThon Bank");

        while (option != 6) {
            System.out.println("\nMain Menu");
            System.out.println("1. Display all accounts.");
            System.out.println("2. Open new account.");
            System.out.println("3. Close existing account.");
            System.out.println("4. Deposit.");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            option = sc.nextInt();
            System.out.println();

            switch (option){
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    accountNumber = generateAccountNumber();
                    System.out.print("Enter account name: ");
                    accountName = sc.next();
                    System.out.print("Enter balance: ");
                    balance = sc.nextDouble();
                    bank.openAccount(accountNumber, accountName, balance);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextInt();
                    bank.closeAccount(accountNumber);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextInt();
                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();
                    bank.deposit(accountNumber, amount);
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextInt();
                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();
                    bank.withdraw(accountNumber, amount);
                    break;
                case 6:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static int generateAccountNumber(){
        Random rand = new Random();
        int generateNumber = 100000 + rand.nextInt(900000);
        return generateNumber;
    }
}