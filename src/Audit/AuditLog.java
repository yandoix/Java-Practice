package Audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuditLog implements AutoCloseable {



    private final List<String> entries = new ArrayList<>();

    public AuditLog() {
        System.out.println("--------[AuditLog] opened--------");
    }

    public void add(String entry) {
        if (entry == null || entry.isBlank()) {
            throw new IllegalArgumentException("Audit entry must not be blank");
        }
        entries.add(entry);
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    @Override 
    public void close() {
        System.out.println("Resource has been successfully closed! Total entries : "+entries.size());
        for(String entry : entries) {
            System.out.println("  - "+entry);
        }
    }
}
