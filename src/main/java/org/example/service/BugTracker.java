package org.example.service;

import org.example.model.Bug;

import java.util.List;
import java.util.Scanner;

public class BugTracker {

    private final BugService bugService;
    private final Scanner scanner;

    public BugTracker() {
        bugService = new BugService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n=====  Bug Tracker Menu =====");
            System.out.println("1. Add Bug");
            System.out.println("2. View All Bugs");
            System.out.println("3. Update Bug");
            System.out.println("4. Delete Bug");
            System.out.println("5. Search Bug by ID");
            System.out.println("6. Search Bug by Title");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBug();
                case 2 -> viewAllBugs();
                case 3 -> updateBug();
                case 4 -> deleteBug();
                case 5 -> searchById();
                case 6 -> searchByTitle();
                case 0 -> System.out.println(" Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    private void addBug() {
        System.out.print("Enter Bug ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Severity (Low/Medium/High): ");
        String severity = scanner.nextLine();

        System.out.print("Enter Priority (Low/Medium/High): ");
        String priority = scanner.nextLine();

        Bug bug = new Bug(id, title, description, severity, priority);
        bugService.addBug(bug);
        System.out.println(" Bug added successfully!");
    }

    private void viewAllBugs() {
        List<Bug> bugs = bugService.getAllBugs();

        if (bugs.isEmpty()) {
            System.out.println(" No bugs found.");
        } else {
            for (Bug bug : bugs) {
                System.out.println(bug);
            }
        }
    }

    private void updateBug() {
        System.out.print("Enter Bug ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Bug existing = bugService.getBugById(id);
        if (existing == null) {
            System.out.println(" Bug not found.");
            return;
        }

        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter New Severity: ");
        String severity = scanner.nextLine();

        System.out.print("Enter New Priority: ");
        String priority = scanner.nextLine();

        Bug updated = new Bug(id, title, description, severity, priority);
        boolean updatedResult = bugService.updateBug(id, updated);

        if (updatedResult) {
            System.out.println(" Bug updated successfully.");
        } else {
            System.out.println(" Update failed.");
        }
    }

    private void deleteBug() {
        System.out.print("Enter Bug ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = bugService.deleteBug(id);

        if (deleted) {
            System.out.println(" Bug deleted successfully.");
        } else {
            System.out.println(" Bug not found.");
        }
    }

    private void searchById() {
        System.out.print("Enter Bug ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Bug bug = bugService.getBugById(id);
        if (bug != null) {
            System.out.println(bug);
        } else {
            System.out.println(" Bug not found.");
        }
    }

    private void searchByTitle() {
        System.out.print("Enter Bug Title to search: ");
        String title = scanner.nextLine();

        List<Bug> bugs = bugService.getBugsByTitle(title);

        if (bugs.isEmpty()) {
            System.out.println(" No matching bugs found.");
        } else {
            for (Bug bug : bugs) {
                System.out.println(bug);
            }
        }
    }
}
