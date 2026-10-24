package Audit;

public class Session implements AutoCloseable {

    private final String name;

    public Session(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Session name must not be blank");
        }
        this.name = name;
        System.out.println("[Session '" + name + "'] opened");   // after validation, so a failed session never prints "opened"
    }

    public String getName() {
        return name;
    }

    @Override
    public void close() {
        System.out.println("[Session '" + name + "'] closed");
    }
}