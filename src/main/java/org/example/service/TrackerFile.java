package org.example.service;

import org.example.model.Bug;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrackerFile {
    private static final String FILE_NAME = "bugs.ser";

    public static void saveBugs(Collection<Bug> bugs) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(new ArrayList<>(bugs));  // Convert to list for serialization
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Bug> loadBugs() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Bug>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
