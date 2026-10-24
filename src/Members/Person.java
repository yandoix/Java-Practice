package Members;

import java.util.List;
import java.util.Collections; 
import java.util.ArrayList;

import Interfaces.Feeable;
import Interfaces.Identifiable;
import Loan.Loan;
public abstract class Person implements Identifiable, Feeable {

    private final String uniqueID;
    private String name;
    private double outstandingFee;
    private final List<Loan> loans = new ArrayList<>();

    public Person(String uniqueID, String name) {
        this(uniqueID, name, 0.0, new ArrayList<>());
    }

    public Person(String uniqueID, String name, double outstandingFee, List<Loan> loans) {
        
        if (uniqueID == null || uniqueID.isBlank()) {
            throw new IllegalArgumentException("ID must not be blank");
        }
        this.uniqueID = uniqueID;
        setName(name);

        if (outstandingFee < 0) {
            throw new IllegalArgumentException(
                "Outstanding fee must not be negative, got: " + outstandingFee);
        }
        this.outstandingFee = outstandingFee;

        if (loans != null) {
            this.loans.addAll(loans); // Safely copies the loans list
        }

    }
    @Override
    public String getID() {return uniqueID;}
    public String getName() { return name; }
    public double getOutstandingFee() {  return outstandingFee; }
    public List<Loan> getLoans() { return Collections.unmodifiableList(loans); }
    
    public void setName(String name) { 
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("setName value is empty.");
        }
        this.name=name;
    }

    public void addLoan(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("Loan must not be null");
        }
        loans.add(loan);
    }

    public boolean removeLoan(Loan loan) {
        return loans.remove(loan);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return uniqueID.equals(person.uniqueID);
    }

    @Override
    public int hashCode() {
        return uniqueID.hashCode();
    }

    @Override
    public String toString() {
        return "Member Details:\n" +
                "- ID: " + uniqueID + "\n" +
                "- Name: " + name + "\n" +
                "- Outstanding Fees: $" + outstandingFee + "\n" +
                "- Active Loans: " + loans.size();
    }

    @Override 
    public void payFee(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        if (amount > outstandingFee) {
            throw new IllegalArgumentException("Cannot pay more than the outstanding fee balance.");
        }
        this.outstandingFee -= amount;
    }

    @Override
    public void addFee(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount added is 0 or less than 0");
        }
        this.outstandingFee += amount;
    }

    public abstract int getMaxItems();
    public abstract int getLoanBonusDays();
}
