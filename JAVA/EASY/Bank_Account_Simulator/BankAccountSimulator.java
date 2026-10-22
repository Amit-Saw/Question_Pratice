// package JAVA.EASY.Bank_Account_Simulator;
import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

class BankAccount {
  private String accountNumber;
  private String accountHolderName;
  private double balance;

  public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
    
    if(initialBalance < 0) {
      throw new IllegalArgumentException("Initial balance cannot be negative.");
    }
    
    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    if(amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive.");
    }
    balance += amount;
    System.out.println("Deposited: " + amount + ". New balance: " + balance);
  }

  public void withdraw(double amount) {
    if(amount <= 0) {
      throw new IllegalArgumentException("Withdrawal amount must be positive.");
    }
    if(amount > balance) {
      throw new IllegalArgumentException("Insufficient balance for withdrawal.");
    }
    balance -= amount;
    System.out.println("Withdrew: " + amount + ". New balance: " + balance);
  }


  public void displayAccountDetails() {
    System.out.println("Account Number: " + accountNumber);
    System.out.println("Account Holder: " + accountHolderName);
    System.out.println("Balance: " + balance);
  }
}

public class BankAccountSimulator {
  public static void main(String[] args) {
    System.out.println("Welcome to the Bank Account Simulator!");
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Account Number: ");
    String accountNumber = sc.nextLine().trim();
    System.out.print("Enter Account Holder Name: ");
    String accountHolderName = sc.nextLine().trim();
    System.out.print("Enter Initial Balance: ");
    double initialBalance = sc.nextDouble();
    BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
    sc.nextLine(); // Consume the newline character

    System.out.println("Account created successfully!");
    SwitchCase:
    while(true) {
      System.out.println("\nChoose an operation:");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Check Balance");
      System.out.println("4. Display Account Details");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      sc.nextLine(); // Consume the newline character

      switch(choice) {
        case 1:
          System.out.print("Enter amount to deposit: ");
          double depositAmount = sc.nextDouble();
          account.deposit(depositAmount);
          break;
        case 2:
          System.out.print("Enter amount to withdraw: ");
          double withdrawAmount = sc.nextDouble();
          account.withdraw(withdrawAmount);
          break;
        case 3:
          System.out.println("Current Balance: " + account.getBalance());
          break;
        case 4:
          account.displayAccountDetails();
          break;
        case 5:
          System.out.println("Exiting the simulator. Goodbye!");
          sc.close();
          break SwitchCase;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
    account.displayAccountDetails();
  }
}
