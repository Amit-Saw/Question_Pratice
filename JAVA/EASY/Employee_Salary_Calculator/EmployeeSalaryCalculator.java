/*

Task 1 — Employee Salary Calculator

Objective: Build a salary calculation program for an employee.

Create a Java program that accepts an employee's name, basic salary, HRA percentage and deduction amount. Calculate HRA, gross salary and net salary.

Requirements:
- Use appropriate variables and data types.
- Validate that salary and percentages are non-negative.
- Calculate gross salary as basic salary + HRA.
- Calculate net salary as gross salary − deductions.
- Display a formatted salary slip.
- Reject invalid input with a meaningful message.
Expected outcome: A reusable calculation method and a readable salary slip printed to the console.

*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class EmployeeSalaryCalculator {
    public static double calculateSalary(double basicSalary, double hraPercentage) {
      return basicSalary * hraPercentage / 100.0;
  }

    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);

      try {
        System.out.println("Enter Employee Name.");
        String employeeName = sc.nextLine().trim();

        if(employeeName.isEmpty()) {
          System.out.println("Error : Employee name cannot be empty");
          return;
        }

          System.out.print("Enter basic salary: ");
          double basicSalary = sc.nextDouble();

          System.out.print("Enter HRA percentage: ");
          double hraPercentage = sc.nextDouble();

          System.out.print("Enter deduction amount: ");
          double deductions = sc.nextDouble();

          if(!Double.isFinite(basicSalary) || !Double.isFinite(hraPercentage) || !Double.isFinite(deductions)){
            System.out.println("Error: Values must be finite numbers.");
            return;
          }

          if (basicSalary < 0) {
            System.out.println("Error: Basic salary cannot be negative.");
            return;
          }

        if (hraPercentage < 0 || hraPercentage > 100) {
            System.out.println("Error: HRA percentage must be between 0 and 100.");
            return;
          }

        if (deductions < 0) {
            System.out.println("Error: Deductions cannot be negative.");
            return;
          }

        if (deductions < 0) {
              System.out.println("Error: Deductions cannot be negative.");
              return;
            }

        double hra = calculateSalary(basicSalary, hraPercentage);

        double grossSalary = basicSalary + hra;

        if (!Double.isFinite(grossSalary)) {
          System.out.println("Error: Gross salary is too large to calculate.");
          return;
        }

        if (deductions > grossSalary) {
          System.out.println("Error: Deductions cannot exceed gross salary.");
          return;
        }

        double netSalary = grossSalary - deductions;

        System.out.println("EMPLOYEE SALARY SLIP");
        System.out.println("Employee Name : " + employeeName);
        System.out.printf("Basic Salary  : Rs. %.2f%n", basicSalary);
        System.out.printf("HRA (%.2f%%)    : Rs. %.2f%n", hraPercentage, hra);
        System.out.printf("Gross Salary  : Rs. %.2f%n", grossSalary);
        System.out.printf("Deductions    : Rs. %.2f%n", deductions);

        System.out.println("----------------------------------");
        System.out.printf("Net Salary    : Rs. %.2f%n", netSalary);

        } catch(InputMismatchException e){
          System.out.println("Error: Please enter a valid number for salary, " + "HRA percentage, and deductions.");
        } finally {
          sc.close();
        }
    }
}