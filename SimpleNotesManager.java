import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SimpleNotesManager {

    public static void addNote(Scanner input) {
        System.out.print("Enter your note: ");
        String note = input.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter("notes.txt", true))) {
            writer.println(note);
            System.out.println("Note saved successfully.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("notes.txt"))) {
            String line;
            int count = 1;

            System.out.println();
            System.out.println("Saved Notes");
            System.out.println("-----------");

            while ((line = reader.readLine()) != null) {
                System.out.println(count + ". " + line);
                count++;
            }

            if (count == 1) {
                System.out.println("No notes found.");
            }

        } catch (IOException e) {
            System.out.println("No notes file found yet.");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice;

        do {
            System.out.println();
            System.out.println("Simple Notes Manager");
            System.out.println("--------------------");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    addNote(input);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        input.close();
    }
}
