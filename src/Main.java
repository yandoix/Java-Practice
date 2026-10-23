
import Items.*;
import Members.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // 1. Mixed-list demonstration
        List<AItem> items = new ArrayList<>();

        items.add(new BBook(
                "B001", "Book of Disquiet", 2006,
                Genre.FICTION, ItemStatus.AVAILABLE,
                "Fernando Pessoa", 120));

        items.add(new BMagazine(
                "M001", "Science Monthly", 2024,
                Genre.SCIENCE, ItemStatus.AVAILABLE,
                12, "Science Publications"));

        items.add(new BDVD(
                "D001", "The Matrix", 1999,
                Genre.TECH, ItemStatus.AVAILABLE,
                136));

        int daysLate = 5;

        System.out.println("=== MIXED-LIST DEMO ===");

        for (AItem item : items) {
            System.out.println(item);
            System.out.printf(
                    "Late fee for %d days: €%.2f%n%n",
                    daysLate, item.calculateLateFee(daysLate));
        }

        // Verify the fee cap
        System.out.printf(
                "DVD fee for 20 days: €%.2f%n%n",
                items.get(2).calculateLateFee(20));

        // 2. Same-ID equality demonstration for items
        System.out.println("=== ITEM EQUALITY DEMO ===");

        Set<AItem> itemSet = new HashSet<>();

        itemSet.add(new BBook(
                "SAME1", "First title", 2020,
                Genre.FICTION, ItemStatus.AVAILABLE,
                "Author One", 100));

        itemSet.add(new BBook(
                "SAME1", "Different title", 2021,
                Genre.HISTORY, ItemStatus.AVAILABLE,
                "Author Two", 200));

        System.out.println(
                "Two books with the same ID: " + itemSet.size());

        itemSet.clear();

        itemSet.add(new BBook(
                "SAME2", "A book", 2020,
                Genre.FICTION, ItemStatus.AVAILABLE,
                "Author", 100));

        itemSet.add(new BDVD(
                "SAME2", "A DVD", 2020,
                Genre.TECH, ItemStatus.AVAILABLE,
                120));

        System.out.println(
                "Book and DVD with the same ID: " + itemSet.size());

        // 3. Same-ID equality demonstration for people
        System.out.println("\n=== PERSON EQUALITY DEMO ===");

        Set<Person> people = new HashSet<>();

        people.add(new Student("P001", "Alice"));
        people.add(new Student("P001", "Bob"));

        System.out.println(
                "Two students with the same ID: " + people.size());

        people.clear();

        people.add(new Student("P002", "Charlie"));
        people.add(new Faculty("P002", "Dr. Smith"));

        System.out.println(
                "Student and faculty with the same ID: "
                        + people.size());

        // 4. Total number of AItem objects constructed
        System.out.println("\n=== ITEM COUNTER ===");
        System.out.println(
                "Total items created: " + AItem.getItemCount());
    }
}
