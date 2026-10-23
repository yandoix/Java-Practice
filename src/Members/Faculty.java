package Members;

import java.util.List;

import Loan.Loan;

public class Faculty extends Person {

    public Faculty(String uniqueID, String name) {
        super(uniqueID, name);
    }

    public Faculty(String uniqueID, String name, double outstandingFee, List<Loan> loans) {
        super(uniqueID, name, outstandingFee, loans);
    }

    @Override
    public int getMaxItems() {
        return 8;
    }

    @Override
    public int getLoanBonusDays() {
        return 7;
    }

    @Override
    public String toString() {
        return "Faculty Details:\n" +
                "- ID: " + getID() + "\n" +
                "- Name: " + getName() + "\n" +
                "- Outstanding Fees: $" + getOutstandingFee() + "\n" +
                "- Active Loans: " + getLoans().size() + "\n" +
                "-Max Items Allowed: "+getMaxItems()+"\n"+
                "- Loan Bonus Days: +" + getLoanBonusDays();
    }
}