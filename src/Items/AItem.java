package Items;
import Interfaces.Identifiable;

public abstract class AItem implements Identifiable {

    private static final int MIN_YEAR = 1450;
    private static final int MAX_YEAR = 2026;

    private static int itemCount = 0;

    private final String ID;
    private String Title;
    private int Year;
    private Genre genre;
    private ItemStatus Status;

    public AItem(String ID, String Title, ItemStatus Status) {
        this(ID, Title,0,null, Status);
    }

    public AItem(String ID, String Title, int Year, Genre genre, ItemStatus Status ) {
        this.ID = ID;
        this.Title = Title;
        this.Year = Year;
        this.genre = genre;
        this.Status = Status;

        itemCount++;
    }

    public static int getItemCount() { return itemCount;}
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
        if(Title ==null || Title==""){

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

                this.genre = genre;
    }
    public void setStatus(ItemStatus Status) {
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
        AItem aItem = (AItem) o;
        return this.ID.equals(aItem.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    public abstract void Loan();
    public abstract int getLoanPeriodDays();
    public abstract double getDailyFee();
}