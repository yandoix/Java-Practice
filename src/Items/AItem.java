package Items;
import Interfaces.Identifiable;

public abstract class AItem implements Identifiable {

    private static final int MIN_YEAR = 1450;
    private static final int MAX_YEAR = 2026;
    public static final double LATE_FEE_CAP = 10.00;

    private static int itemCount = 0;

    private final String ID;
    private String Title;
    private int Year;
    private Genre genre;
    private ItemStatus Status;


    public AItem(String ID, String Title, int Year, Genre genre, ItemStatus Status ) {
        if (ID == null || ID.isBlank()) {
            throw new IllegalArgumentException("ID must not be blank");
        }
        this.ID = ID;
        setTitle(Title);
        setYear(Year);
        setGenre(genre);
        setStatus(Status);
        itemCount++;
    }

    public static int getItemCount() { return itemCount;}
    @Override 
    public String getID() {return ID;}
    public String getTitle() {return Title;}
    public int getYear() {return Year;}
    public Genre getGenre() {return genre;}
    public ItemStatus getStatus() {return Status;}

    /*public void setID(String ID) {
        if(ID !=null){
            this.ID = ID;
        }
    }*/
    public void setTitle(String Title) {
        if(Title ==null || Title.isBlank()){

            throw new IllegalArgumentException("Title can't be blank");
        }
        this.Title = Title;
    }
    public void setYear(int Year) {
        if(Year<MIN_YEAR || Year>MAX_YEAR){
            throw new IllegalArgumentException("Year is not in valid limit");
        }
        this.Year = Year;
    }
    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("The genre is null for the book");
        }

                this.genre = genre;
    }
    public void setStatus(ItemStatus Status) {
        if (Status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
        this.Status= Status;
    }
    @Override
    public String toString() {
    return "Item Details:\n" +
           "- ID: " + getID() + "\n" +
           "- Title: " + getTitle() + "\n" +
           "- Year: " + getYear() + "\n" +
           "- Genre: " + (getGenre() != null ? getGenre().getDisplayName() : "None") + "\n" +
           "- Status: " + getStatus() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AItem other = (AItem) o;
        return this.ID.equals(other.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    //public abstract void Loan();

    public double calculateLateFee(int daysLate) {
        if (daysLate<=0) {
            return 0.00;
        }
        double fee = daysLate * getDailyFee();
        return Math.min(fee,LATE_FEE_CAP);

    }
    public abstract int getLoanPeriodDays();
    public abstract double getDailyFee();
}