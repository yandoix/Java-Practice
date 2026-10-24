package Audit;

public class AuditDemo {

    public static void main(String[] args) {

        System.out.println("=== 1. Normal run ===");
        try (AuditLog log = new AuditLog(); Session session = new Session("Borrow Session")) {
            log.add("Session " + session.getName() + " started");
            log.add("Member S1 borrowed item B001");
            System.out.println("Body finished");
        }

      /*   System.out.println("\n=== 2. Declaration order swapped ===");
        try (Session session = new Session("Return session"); AuditLog log = new AuditLog()) {
            log.add("Item B001 returned");
        }

        System.out.println("\n=== 3. Body throws ===");
        try (AuditLog log = new AuditLog(); Session session = new Session("Failing session")) {
            log.add("Running in " + session.getName());
            throw new IllegalStateException("Something went wrong in the body");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }*/
    }
}