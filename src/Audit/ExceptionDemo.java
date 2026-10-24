package Audit;

import java.time.LocalDate;
import Exceptions.Checked.*;
import Exceptions.Unchecked.*;
/*
import Exceptions.ImportFailedException;
import Exceptions.Checked.BorrowLimitExceededException;
import Exceptions.Checked.ItemNotAvailableException;
import Exceptions.Checked.OutstandingFeesException;
import Exceptions.Checked.zLibraryException;
import Exceptions.Unchecked.DuplicateEntityException;
import Exceptions.Unchecked.EntityNotFoundException;*/

public class ExceptionDemo {

    // Stand-in for the library's borrow method (the real one comes on Day 4).
    // The finally block records "Attempt finished" whether the attempt succeeds or throws.
    static void attemptBorrow(AuditLog log, String scenario) throws ItemNotAvailableException,
                                                                    BorrowLimitExceededException, 
                                                                    OutstandingFeesException 
                                                                {

        log.add("Starting attempt: " + scenario);
        try {
            if (scenario.equals("unavailable")) {
                throw new ItemNotAvailableException(LocalDate.of(2026, 11, 5));
            }
            if (scenario.equals("limit")) {
                throw new BorrowLimitExceededException(3);
            }
            if (scenario.equals("fees")) {
                throw new OutstandingFeesException(7.5);
            }
            log.add("Attempt in try block");
        } finally {
            log.add("Attempt in finally block");   // runs on success AND on every throw above
        }
    }

    // Wraps a low-level exception in ImportFailedException, keeping the original as the cause.
    static void importRecord(String value) throws ImportFailedException {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ImportFailedException("Could not import record '" + value + "'", e);
        }
    }
    public static void main(String[] args) {

        try (AuditLog log = new AuditLog(); Session session = new Session("Exception tests")) {

            System.out.println("\n=== Borrow attempts ===");
            String[] scenarios = {"ok", "unavailable", "limit", "fees"};

            for (String scenario : scenarios) {
                try {
                    attemptBorrow(log, scenario);
                    System.out.println(scenario + ": success");
                } catch (ItemNotAvailableException e) {
                    System.out.println(scenario + ": " + e.getMessage());
                    System.out.println("  extra data -> expected return day: " + e.getExpectedReturnDay());
                } catch (BorrowLimitExceededException e) {
                    System.out.println(scenario + ": " + e.getMessage());
                    System.out.println("  extra data -> limit: " + e.getLimit());
                } catch (OutstandingFeesException e) {
                    System.out.println(scenario + ": " + e.getMessage());
                    System.out.println("  extra data -> amount owed: " + e.getAmountOwed());
                }
            }

            System.out.println("\n=== Other custom exceptions ===");

            try {
                throw new EntityNotFoundException("Item B999 not found");
            } catch (EntityNotFoundException e) {
                System.out.println("EntityNotFoundException: " + e.getMessage());
            }

            try {
                throw new DuplicateEntityException("Item B001 already exists");
            } catch (DuplicateEntityException e) {
                System.out.println("DuplicateEntityException: " + e.getMessage());
            }

            try {
                importRecord("abc");
            } catch (ImportFailedException e) {
                System.out.println("ImportFailedException: " + e.getMessage());
                System.out.println("  cause: " + e.getCause().getClass().getSimpleName()
                        + ": " + e.getCause().getMessage());
            }

            try {
                throw new zLibraryException("Generic library error", new IllegalStateException("root cause"));
            } catch (zLibraryException e) {
                System.out.println("zLibraryException: " + e.getMessage());
                System.out.println("  cause: " + e.getCause().getClass().getSimpleName()
                        + ": " + e.getCause().getMessage());
            }
        }
    }
}