import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentRecordFileSystem {

    public static void addStudent(Scanner input) {
        System.out.print("Enter student ID: ");
        String studentId = input.nextLine();

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter course: ");
        String course = input.nextLine();

        System.out.print("Enter mark: ");
        String markText = input.nextLine();

        try {
            double mark = Double.parseDouble(markText);

            if (mark < 0 || mark > 100) {
                System.out.println("Invalid mark. Mark must be between 0 and 100.");
                return;
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("student_records.csv", true))) {
                writer.println(studentId + "," + name + "," + course + "," + mark);
                System.out.println("Student record saved successfully.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid mark. Please enter a number.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void viewStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_records.csv"))) {
            String line;
            int count = 1;

            System.out.println();
            System.out.println("Student Records");
            System.out.println("---------------");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String studentId = parts[0];
                    String name = parts[1];
                    String course = parts[2];

                    try {
                        double mark = Double.parseDouble(parts[3]);

                        System.out.println("Record " + count);
                        System.out.println("Student ID: " + studentId);
                        System.out.println("Name      : " + name);
                        System.out.println("Course    : " + course);
                        System.out.printf("Mark      : %.2f%n", mark);
                        System.out.println("Grade     : " + getGrade(mark));
                        System.out.println();

                        count++;

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark in record: " + line);
                    }

                } else {
                    System.out.println("Invalid record skipped: " + line);
                }
            }

            if (count == 1) {
                System.out.println("No student records found.");
            }

        } catch (IOException e) {
            System.out.println("No student records file found yet.");
        }
    }

    public static String getGrade(double mark) {
        if (mark >= 80) {
            return "A";
        } else if (mark >= 70) {
            return "B";
        } else if (mark >= 60) {
            return "C";
        } else if (mark >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static int readChoice(Scanner input) {
        try {
            System.out.print("Enter your choice: ");
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("Student Record File System");
            System.out.println("--------------------------");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");

            choice = readChoice(input);

            switch (choice) {
                case 1:
                    addStudent(input);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        input.close();
    }
}
