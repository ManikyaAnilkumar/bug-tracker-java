package org.example.service;

import org.example.model.Bug;

import java.util.*;

public class BugTrackerService {
    private final Map<Integer, Bug> bugMap;

    public BugTrackerService() {
        List<Bug> bugList = TrackerFile.loadBugs();
        bugMap = new HashMap<>();
        for (Bug bug : bugList) {
            bugMap.put(bug.getId(), bug);
        }
    }

    public void addBug(Bug bug) {
        bugMap.put(bug.getId(), bug);
        TrackerFile.saveBugs(bugMap.values());
        System.out.println("✅ Bug added successfully!");
    }

    public void viewBugs() {
        if (bugMap.isEmpty()) {
            System.out.println("🚫 No bugs found.");
        } else {
            for (Bug bug : bugMap.values()) {
                System.out.println(bug);
            }
        }
    }

    public void updateBug(int id, Bug updatedBug) {
        if (bugMap.containsKey(id)) {
            bugMap.put(id, updatedBug);
            TrackerFile.saveBugs(bugMap.values());
            System.out.println("✅ Bug updated successfully.");
        } else {
            System.out.println("🚫 Bug with ID " + id + " not found.");
        }
    }

    public void deleteBug(int id) {
        if (bugMap.containsKey(id)) {
            bugMap.remove(id);
            TrackerFile.saveBugs(bugMap.values());
            System.out.println("🗑️ Bug deleted successfully.");
        } else {
            System.out.println("🚫 Bug with ID " + id + " not found.");
        }
    }

    public Bug getBugById(int id) {
        return bugMap.get(id);
    }

    // ✅ New Feature: Search by Title
    public List<Bug> getBugsByTitle(String title) {
        List<Bug> result = new ArrayList<>();
        for (Bug bug : bugMap.values()) {
            if (bug.getTitle().equalsIgnoreCase(title)) {
                result.add(bug);
            }
        }
        return result;
    }
}
