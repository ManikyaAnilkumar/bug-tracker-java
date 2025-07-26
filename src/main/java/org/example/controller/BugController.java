package org.example.controller;

import org.example.model.Bug;
import org.example.service.BugTrackerService;

import java.util.List;
import java.util.Scanner;

public class BugController {
    private final BugTrackerService bugService;
    private final Scanner scanner;

    public BugController() {
        bugService = new BugTrackerService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n===== Bug Tracker Menu =====");
            System.out.println("1. Add Bug");
            System.out.println("2. View Bugs");
            System.out.println("3. Update Bug");
            System.out.println("4. Delete Bug");
            System.out.println("5. Search Bug by ID");
            System.out.println("6. Search Bug by Title");  // ✅ New option
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> addBug();
                case 2 -> bugService.viewBugs();
                case 3 -> updateBug();
                case 4 -> deleteBug();
                case 5 -> searchBugById();
                case 6 -> searchBugByTitle();  // ✅ New method
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private void addBug() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter Severity: ");
        String severity = scanner.nextLine();

        System.out.print("Enter Priority: ");
        String priority = scanner.nextLine();

        Bug bug = new Bug(id, title, desc, severity, priority);
        bugService.addBug(bug);
    }

    private void updateBug() {
        System.out.print("Enter Bug ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter New Description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter New Severity: ");
        String severity = scanner.nextLine();

        System.out.print("Enter New Priority: ");
        String priority = scanner.nextLine();

        Bug updatedBug = new Bug(id, title, desc, severity, priority);
        bugService.updateBug(id, updatedBug);
    }

    private void deleteBug() {
        System.out.print("Enter Bug ID to delete: ");
        int id = scanner.nextInt();
        bugService.deleteBug(id);
    }

    private void searchBugById() {
        System.out.print("Enter Bug ID to search: ");
        int id = scanner.nextInt();
        Bug bug = bugService.getBugById(id);
        if (bug != null) {
            System.out.println("Bug found: " + bug);
        } else {
            System.out.println("Bug not found.");
        }
    }

    private void searchBugByTitle() {
        System.out.print("Enter Bug Title to search: ");
        String title = scanner.nextLine();
        List<Bug> bugs = bugService.getBugsByTitle(title);
        if (bugs.isEmpty()) {
            System.out.println("No bugs found with title: " + title);
        } else {
            System.out.println("Matching bugs:");
            for (Bug bug : bugs) {
                System.out.println(bug);
            }
        }
    }
}
