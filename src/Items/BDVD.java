package Items;

public class BDVD extends AItem {


    //in minutes
    private int runtime;

   public BDVD(String ID, String Title, int Year, Genre Genre, ItemStatus Status, int runtime) {

        super(ID, Title,Year,Genre,Status);
        setRuntime(runtime);
    }

   public int getRuntime(){ return runtime; }

   public void setRuntime(int runtime) {
    if(runtime<1) {
        throw new IllegalArgumentException("This DVD is less than a minute long, thus cannot be sold");
    }
    this.runtime = runtime;
   }

   /*public void Loan() {
        if (getStatus()==ItemStatus.AVAILABLE) {

            setStatus(ItemStatus.ON_LOAN);
            System.out.println("The DVD "+getTitle()+" is now loaned to");
        }
    }*/

    @Override
    public int getLoanPeriodDays() {
        return 3;
    }

    @Override
    public double getDailyFee() {
        return 1.00;
    }

   @Override
public String toString() {
    return "DVD Details:\n" +
           "- ID: " + getID() + "\n" +
           "- Title: " + getTitle() + "\n" +
           "- Year: " + getYear() + "\n" +
           "- Genre: " + (getGenre() != null ? getGenre().getDisplayName() : "None") + "\n" +
           "- Status: " + getStatus() + "\n" +
           "- Runtime: " + getRuntime() +" minutes"+ "\n";
        }
    
}
