package Items;
//import Interfaces.Identifiable;
public class BBook extends AItem { 

    private String author;
    private int pageCount;

    public BBook(String ID, String Title, int Year, Genre Genre, ItemStatus Status, String author, int pageCount) {

        super(ID, Title,Year,Genre,Status);
        setAuthor(author);
        setCount(pageCount);
    }

    public BBook(String ID, String Title, ItemStatus Status, String author,int pageCount) {
        super(ID,Title,Status);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {return author;}
    public int getCount() {return pageCount;}

    public void setAuthor(String author) {
        if(author==null || author.equals("")) {
            throw new IllegalArgumentException("Author can't be blank");
        }
        this.author = author;
    }
    public void setCount(int pageCount) {
        if(pageCount<=0) {
            this.pageCount = pageCount;
        }
    }

    public void Loan() {
        if (getStatus()==ItemStatus.AVAILABLE) {

            setStatus(ItemStatus.ON_LOAN);
            System.out.println("The Book"+getTitle()+" is now loaned to");
        }
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }
    
    @Override
    public double getDailyFee() {
        return 0.50;
    }

    @Override
public String toString() {
    return "Book Details:\n" +
           "- ID: " + getID() + "\n" +
           "- Title: " + getTitle() + "\n" +
           "- Year: " + getYear() + "\n" +
           "- Genre: " + (getGenre() != null ? getGenre().getDisplayName() : "None") + "\n" +
           "- Status: " + getStatus() + "\n" +
           "- Author: " + this.author + "\n" +
           "- Pages: " + this.pageCount;
        }
}