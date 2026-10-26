import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Audit.AuditLog;
import Exceptions.Checked.BorrowLimitExceededException;
import Exceptions.Checked.ItemNotAvailableException;
import Exceptions.Checked.OutstandingFeesException;
import Util.Repository;
import Items.AItem;
import Items.ItemStatus;
import Members.Person;
import Loan.Loan;

public class Library {

    private static final double FEE_BLOCK_THRESHOLD = 5.00;   // fees at or above this block borrowing
    private static final int MAX_FEED_SIZE = 10;

    private final Repository<AItem> itemRepository;
    private final Repository<Person> memberRepository;
    private final AuditLog auditLog;

    private LocalDate currentDate;
    private int nextLoanNumber = 1;
    
    private final Map<String, Loan> activeLoans = new HashMap<>();      // item ID -> the loan it is currently on
    private final Deque<Loan> undoHistory = new ArrayDeque<>();         // stack of borrows, newest on top
    private final LinkedList<String> activityFeed = new LinkedList<>();

     public Library(Repository<AItem> itemRepository, Repository<Person> memberRepository,
                   AuditLog auditLog, LocalDate startDate) {
        if (itemRepository == null || memberRepository == null) {
            throw new IllegalArgumentException("Repositories must not be null");
        }
        if (auditLog == null) {
            throw new IllegalArgumentException("Audit log must not be null");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date must not be null");
        }
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.auditLog = auditLog;
        this.currentDate = startDate;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void advanceClock(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days to advance must be positive, got: " + days);
        }
        currentDate = currentDate.plusDays(days);
    }

    // Read-only view, newest event first
    public List<String> getActivityFeed() {
        return Collections.unmodifiableList(activityFeed);
    }


    public Loan borrow(String memberId, String itemId) throws OutstandingFeesException,
                                                    BorrowLimitExceededException, 
                                                    ItemNotAvailableException {

        auditLog.add("Borrow attempt: member " + memberId + ", item " + itemId);
        try {

        if (memberId == null || memberId.isBlank()) {
                throw new IllegalArgumentException("Member ID must not be blank");
            }
        if (itemId == null || itemId.isBlank()) {
                throw new IllegalArgumentException("Item ID must not be blank");
            }

        Person member = memberRepository.findById(memberId);
        AItem item = itemRepository.findById(itemId);

        if (member.getOutstandingFee() >= FEE_BLOCK_THRESHOLD) {
                throw new OutstandingFeesException(member.getOutstandingFee());
            }

        if (!member.canBorrowMore()) {
                throw new BorrowLimitExceededException(member.getMaxItems());
            }

        if (item.getStatus() == ItemStatus.ON_LOAN) {
                Loan current = activeLoans.get(item.getID());
                if (current == null) {
                    // Status says ON_LOAN but no loan exists: the data is inconsistent, so this is a bug, not a user error
                    throw new IllegalStateException(
                        "Item " + item.getID() + " is marked ON_LOAN but has no active loan");
                    }
            throw new ItemNotAvailableException(current.getDueDate());
            }
        Loan loan = new Loan(String.format("L%03d", nextLoanNumber), item, member, currentDate);
            nextLoanNumber++;

            member.addLoan(loan);
            item.setStatus(ItemStatus.ON_LOAN);
            activeLoans.put(item.getID(), loan);
            undoHistory.push(loan);

            addToFeed(member.getName() + " (" + member.getID() + ") borrowed \""
                    + item.getTitle() + "\" (" + item.getID() + "), due " + loan.getDueDate());

            auditLog.add("Borrow succeeded: " + loan.getLoanID());

            return loan;
        } finally {
            auditLog.add("Borrow attempt finished");
        }
    }
    private void addToFeed(String event) {
        activityFeed.addFirst("[" + currentDate + "] " + event);
        if (activityFeed.size() > MAX_FEED_SIZE) {
            activityFeed.removeLast();
        }
    }
}
/*identify and declare fields for:
- Repositories: the existing repositories for library items and members.
- Clock: the current simulated date/time used by the library.
- Active loans: a collection of loans that have not been returned.
- Waitlists: a FIFO queue of members waiting for an item.
- Undo history: a stack or deque of operations that can be reversed.
- Activity feed: an ordered record of library events. */