package pl.dm.taskmanager;

public enum Priority {
    HIGH("High Priority"),
    MEDIUM("Medium Priority"),
    LOW("Low Priority");

    private String priorityName;

    Priority(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getPriorityName() {
        return priorityName;
    }
}
