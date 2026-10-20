
import java.util.Scanner;

class Student{
  int studentId;
  String studentName;
  double[] marks;

  private static final int SUBJECTS_COUNT = 5;
  private static final double PASSING_MARKS = 35.0;

  Student(int studentId, String studentName, double[] marks) {
    validateMarks(marks);

    this.studentId = studentId;
    this.studentName = studentName;
    this.marks = marks.clone();
  }

  private void validateMarks(double[] marks) {
    if(marks == null || marks.length != SUBJECTS_COUNT) {
      throw new IllegalArgumentException("Marks array must contain exactly " + SUBJECTS_COUNT + " subjects.");
    }

    for(double mark : marks){
      if(!Double.isFinite(mark) || mark < 0 || mark > 100) {
        throw new IllegalArgumentException("Marks must be between 0 and 100.");
      }
    }
  }

  public double calculateTotalMarks() {
    double total = 0;
    for(double mark : marks) {
      total += mark;
    }
    return total;
  }

  public double calculatePercentage() {
    return (calculateTotalMarks() / (SUBJECTS_COUNT * 100)) * 100;
  }

  public String determineGrade() {
    double percentage = calculatePercentage();
    if(percentage >= 90) return "A+";
    else if(percentage >= 80) return "A";
    else if(percentage >= 70) return "B";
    else if(percentage >= 60) return "C";
    else if(percentage >= PASSING_MARKS) return "D";
    else return "F";
  }


  public boolean hasPassed() {
    for(double mark : marks) {
      if(mark < PASSING_MARKS) {
        return false;
      }
    }
    return true;
  }

  public void displayReportCard() {
    System.out.println("\n--- Report Card ---");
    System.out.println("Student ID: " + studentId);
    System.out.println("Student Name: " + studentName);
    String[] subjects = {"Maths", "Science", "English", "History", "Geography"};
    for(int i = 0; i < marks.length; i++) {
      System.out.println(String.format("%s: %.2f", subjects[i], marks[i]));
    }
    System.out.println("Total Marks: " + calculateTotalMarks());
    System.out.println("Percentage: " + String.format("%.2f", calculatePercentage()) + "%");
    System.out.println("Grade: " + determineGrade());
    if(hasPassed()) {
      System.out.println("Result: Passed");
    } else {
      System.out.println("Result: Failed");
    }
    System.out.println("-------------------\n");
  }
}

public class StudentGradeManagement {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Student ID: ");

    if (!sc.hasNextInt()) {
      System.out.println("Error: Invalid input. Please enter a valid integer for Student ID.");
      sc.close();
      return;
    }

      int studentId = sc.nextInt();
      sc.nextLine();

      System.out.print("Enter Student Name: ");
      String studentName = sc.nextLine().trim();

      if (studentName.isEmpty()) {
        System.out.println("Error: Student name cannot be empty.");
        sc.close();
        return;
      }

      String[] subjects = {"Maths", "Science", "English", "History", "Geography"};

      double[] marks = new double[5];

      for(int i = 0; i < marks.length; i++) {
        System.out.print("Enter marks for " + subjects[i] + ": ");

        if (!sc.hasNextDouble()){
          System.out.println("Error: Invalid input. Please enter a valid double for each mark.");
          sc.close();
          return;
        }
        marks[i] = sc.nextDouble();

        if(!Double.isFinite(marks[i]) || marks[i] < 0 || marks[i] > 100) {
          System.out.println("Error: Marks must be between 0 and 100.");
          sc.close();
          return;
        }
      }
      try {
        Student student = new Student(studentId, studentName, marks);
        System.out.println("Student created successfully.");
        student.displayReportCard();
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      }
      sc.close();
    }
  }
