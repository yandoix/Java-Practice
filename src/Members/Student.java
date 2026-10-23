package Members;

import java.util.List;

import Loan.Loan;

public class Student extends Person {

    public Student(String uniqueID, String name) {
        super(uniqueID, name);
    }
    
    public Student(String uniqueID, String name, double outstandingFee, List<Loan> loans) {
        super(uniqueID, name, outstandingFee, loans);
    }

    @Override
    public int getMaxItems() {
        return 3;
    }

    @Override
    public int getLoanBonusDays() {
        return 0;
    }

    @Override
    public String toString() {
        return "Student Details:\n" +
                "- ID: " + getID() + "\n" +
                "- Name: " + getName() + "\n" +
                "- Outstanding Fees: $" + getOutstandingFee() + "\n" +
                "- Active Loans: " + getLoans().size() + "\n" +
                "- Max Items Allowed: " + getMaxItems();
    }
}