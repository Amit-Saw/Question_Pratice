import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CSVEmployeeReader {

  static class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    Employee(int id, String name, String department, double salary) {
      this.id = id;
      this.name = name;
      this.department = department;
      this.salary = salary;
    }
  }

    static class DepartmentStats {
      double totalSalary = 0;
      int employeeCount = 0;

      void addSalary(double salary) {
        totalSalary += salary;
        employeeCount++;
      }

      double getAverageSalary() {
        return employeeCount == 0 ? 0 : totalSalary / employeeCount;
      }

      static Employee parseEmployee(String line, int lineNumber) throws IOException {
        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
          throw new IOException("Invalid CSV format at line " + lineNumber);
        }
        try {
          int id = Integer.parseInt(parts[0].trim());
          String name = parts[1].trim();
          String department = parts[2].trim();
          double salary = Double.parseDouble(parts[3].trim());

          if(name.isEmpty() || department.isEmpty()) {
            throw new IOException("Name or department cannot be empty at line " + lineNumber);
          }

          return new Employee(id, name, department, salary);
        } catch (NumberFormatException e) {
          throw new IOException("Invalid number format at line " + lineNumber, e);
        }
      }
    }

      static void processFile(Path filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Map<String, DepartmentStats> departmentStatsMap = new HashMap<>();

        int validEmployeeCount = 0;
        int invalidEmployeeCount = 0;
        int lineNumber = 0;

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
          String line = reader.readLine(); 
          lineNumber++;

          if(line == null || !line.equals("id,name,department,salary")) {
            throw new IOException("Invalid CSV header");
          }

          while ((line = reader.readLine()) != null) {
            lineNumber++;

            if(line.trim().isEmpty()) {
              System.out.println("Warning: Empty line at line " + lineNumber);
              invalidEmployeeCount++;
              continue; 
            } 

            try {

              Employee employee = DepartmentStats.parseEmployee(line, lineNumber);
              employees.add(employee);
              validEmployeeCount++;

              departmentStatsMap.putIfAbsent(employee.department, new DepartmentStats());
              departmentStatsMap.get(employee.department).addSalary(employee.salary);

            } catch (IOException e) {
              System.out.println("Error processing line " + lineNumber + ": " + e.getMessage());
              invalidEmployeeCount++;
            }          
        }
      }
        catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        return;
      }
      
        System.out.println("Total valid employees: " + validEmployeeCount);
        System.out.println("Total invalid employees: " + invalidEmployeeCount);
        System.out.println("Average salary per department:");
        for (Map.Entry<String, DepartmentStats> entry : departmentStatsMap.entrySet()) {
          String department = entry.getKey();
          double averageSalary = entry.getValue().getAverageSalary();
          System.out.printf("%s: %.2f%n", department, averageSalary);
        }

        System.out.println("\nEmployee Details:");
        for(Employee employee : employees) {
          System.out.printf("ID: %d, Name: %s, Department: %s, Salary: %.2f%n", 
                            employee.id, employee.name, employee.department, employee.salary);
        }

        System.out.println("\nSummary:");
        System.out.println("Total employees: " + (validEmployeeCount + invalidEmployeeCount));
        System.out.println("Valid employees: " + validEmployeeCount);
        System.out.println("Invalid employees: " + invalidEmployeeCount);
    }

    public static void main(String[] args) {
        Path filePath = Path.of("employees.csv");
        try {
            processFile(filePath);
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
