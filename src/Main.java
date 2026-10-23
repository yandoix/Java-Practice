import Items.*;
public class Main {
    public static void main(String[] args) {

        AItem test = new BBook("S@0001","Book of Disquiet",2006,Genre.FICTION,ItemStatus.AVAILABLE,"Fernando Pessoa",120);

        System.out.println(test);
    }
    
}
