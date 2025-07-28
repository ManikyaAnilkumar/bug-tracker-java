package org.example.service;

import org.example.model.Bug;
import org.example.service.TrackerFile;

import java.util.*;

public class BugService {
    private final List<Bug> bugList;

    public BugService() {
        bugList = TrackerFile.loadBugs();
    }

    public void addBug(Bug bug) {
        bugList.add(bug);
        TrackerFile.saveBugs(bugList);
    }

    public List<Bug> getAllBugs() {
        return bugList;
    }

    public Bug getBugById(int id) {
        for (Bug bug : bugList) {
            if (bug.getId() == id) {
                return bug;
            }
        }
        return null;
    }

    public List<Bug> getBugsByTitle(String title) {
        List<Bug> matched = new ArrayList<>();
        for (Bug bug : bugList) {
            if (bug.getTitle().equalsIgnoreCase(title)) {
                matched.add(bug);
            }
        }
        return matched;
    }

    public boolean updateBug(int id, Bug updatedBug) {
        for (int i = 0; i < bugList.size(); i++) {
            if (bugList.get(i).getId() == id) {
                bugList.set(i, updatedBug);
                TrackerFile.saveBugs(bugList);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBug(int id) {
        Iterator<Bug> iterator = bugList.iterator();
        while (iterator.hasNext()) {
            Bug bug = iterator.next();
            if (bug.getId() == id) {
                iterator.remove();
                TrackerFile.saveBugs(bugList);
                return true;
            }
        }
        return false;
    }
}
