package org.example.model;

import java.io.Serializable;

public class Bug implements Serializable {
    private int id;
    private String title;
    private String description;
    private String severity;
    private String priority;

    public Bug(int id, String title, String description, String severity, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSeverity() {
        return severity;
    }

    public String getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "ID=" + id +
                ", Title='" + title + '\'' +
                ", Description='" + description + '\'' +
                ", Severity='" + severity + '\'' +
                ", Priority='" + priority + '\'' +
                '}';
    }
}
