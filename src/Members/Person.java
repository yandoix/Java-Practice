package Members;

import java.util.List;

import Interfaces.Feeable;
import Interfaces.Identifiable;
import Loan.Loan;

import java.util.ArrayList;

public abstract class Person implements Identifiable, Feeable {

    private String uniqueID;
    private String name;
    private double outstandingFee;
    private List<Loan> loans = new ArrayList<>();

    public Person(String uniqueID, String name) {
        this(uniqueID, name, 0.0, new ArrayList<>());
    }

    public Person(String uniqueID, String name, double outstandingFee, List<Loan> loans) {

        this.uniqueID = uniqueID;
        this.name = name;
        this.outstandingFee =outstandingFee;
        this.loans = new ArrayList<>();

        if (loans != null) {
            this.loans.addAll(loans); // Safely copies the loans list
        }

    }
    @Override
    public String getID() {return uniqueID;}
    public String getName() { return name; }
    public double getOutstandingFee() {  return outstandingFee; }
    public List<Loan> getLoans() { return this.loans;  }
    
    public void setName(String name) { 
        if (name != null && !name.trim().isEmpty()) {
            this.name = name; 
        }
    }
    public void setOutstandingFee(double outstandingFee) { 
        this.outstandingFee = outstandingFee; 
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
        if (amount > 0) {
            this.outstandingFee += amount;
        }
    }

    public abstract int getMaxItems();
    public abstract int getLoanBonusDays();
}
