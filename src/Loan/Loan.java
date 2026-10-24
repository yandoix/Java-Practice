package Loan;

import Members.Person;
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

         if (loanID == null || loanID.isBlank()) {
        throw new IllegalArgumentException("Loan ID must not be blank");
    }
    if (item == null) {
        throw new IllegalArgumentException("Item must not be null");
    }
    if (member == null) {
        throw new IllegalArgumentException("Member must not be null");
    }
    if (startDate == null) {
        throw new IllegalArgumentException("Start date must not be null");
    }
        this.loanID = loanID;
        this.item = item;
        this.member = member;
        this.startDate = startDate;

        // Automatically calculates due date using the runtime polymorphism you built!
        this.dueDate = startDate.plusDays(item.getLoanPeriodDays()+ member.getLoanBonusDays());
        this.returnDate = null;
    }

    public String getLoanID() { return loanID; }
    public AItem getItem() { return item; }
    public Person getMember() { return member; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public boolean isReturned() { return returnDate != null; }

    public void setReturnDate(LocalDate returnDate) {
        if (returnDate == null || loanID.isBlank()) {
            throw new IllegalArgumentException("Return date must not be null");
        }
        if (isReturned()) {
            throw new IllegalStateException("Loan " + loanID + " has already been returned");
        }
        if (returnDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                "Return date " + returnDate + " is before start date " + startDate);
        }
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
