import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import Util.LinkedQueue;
import Util.Repository;
import Items.AItem;
import Members.Person;
import Loan.Loan;
import Util.Pair;

public class Library {

    private final Repository<AItem> itemRepository;
    private final Repository<Person> memberRepository;;

    private LocalDate currentDate;
    
    private final List<Loan> loanList;
    private final List<String> activityFeed;

    private final Pair<String, LinkedQueue<Person>> waitlist;
    
    //private ArrayDeque<UndoAction> undoHistory;

    public Library(Repository<AItem> iRepository, Repository<Person> mRepository, List<Loan> loans, List<String> feed, Pair<String, LinkedQueue<Person>> waitlist) {

        this.itemRepository = iRepository;
        this.memberRepository = mRepository;
        this.loanList = loans;
        this.activityFeed = feed;
        this.waitlist = waitlist;
    }
    
}
/*identify and declare fields for:
- Repositories: the existing repositories for library items and members.
- Clock: the current simulated date/time used by the library.
- Active loans: a collection of loans that have not been returned.
- Waitlists: a FIFO queue of members waiting for an item.
- Undo history: a stack or deque of operations that can be reversed.
- Activity feed: an ordered record of library events. */