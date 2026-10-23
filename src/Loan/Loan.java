package Loan;

import Members.*;
import Items.AItem;
import java.time.LocalDate;

public class Loan {
    private final String loanID;
    private final AItem item;
    private final Person member; // Replace Object with your Member class/interface later
    private final LocalDate startDate;
    private final LocalDate dueDate;

    // This can change when the item is actually returned
    private LocalDate returnDate;

    public Loan(String loanID, AItem item, Person member, LocalDate startDate) {
        this.loanID = loanID;
        this.item = item;
        this.member = member;
        this.startDate = startDate;

        // Automatically calculates due date using the runtime polymorphism you built!
        this.dueDate = startDate.plusDays(item.getLoanPeriodDays());
        this.returnDate = null;
    }

    public String getLoanID() { return loanID; }
    public AItem getItem() { return item; }
    public Object getMember() { return member; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan Details:\n" +
                "- Loan ID: " + loanID + "\n" +
                "- Item: " + item.getTitle() + "\n" +
                "- Start Date: " + startDate + "\n" +
                "- Due Date: " + dueDate + "\n" +
                "- Return Date: " + (returnDate != null ? returnDate : "Not yet returned");
    }
    
}
