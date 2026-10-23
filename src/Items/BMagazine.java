package Items;

public class BMagazine extends AItem { 

    private int issueNumber;
    private String publisher;

    public BMagazine(String ID, String Title, int Year, Genre Genre, ItemStatus Status, int issueNumber, String publisher) {

        super(ID,Title,Year,Genre,Status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public int getIssueNumber() {return issueNumber;}
    public String getPublisher() {return publisher;}

    public void setIssueNumber(int issueNumber) {
        if(issueNumber<=0) {
            throw new IllegalArgumentException("Issue number can't be 0 or negative!");
        }
        this.issueNumber=issueNumber;
    }
    public void setPublisher(String publisher) {
        if(publisher==null || publisher.equals("")){
            throw new IllegalArgumentException("Publisher can't be unknown");
        }
        this.publisher = publisher;
    }
    public void Loan() {
        if (getStatus()==ItemStatus.AVAILABLE) {

            setStatus(ItemStatus.ON_LOAN);
            System.out.println("The Magazine "+getTitle()+" is now loaned to");
        }
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public double getDailyFee() {
        return 0.25;
    }

    @Override
public String toString() {
    return "Magazine Details:\n" +
           "- ID: " + getID() + "\n" +
           "- Title: " + getTitle() + "\n" +
           "- Year: " + getYear() + "\n" +
           "- Genre: " + (getGenre() != null ? getGenre().getDisplayName() : "None") + "\n" +
           "- Status: " + getStatus() + "\n" +
           "- Publisher: " + getPublisher() + "\n"+
           "- Issue Number:"+getIssueNumber()+"\n";
        }
}