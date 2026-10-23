package Items;

public enum Genre {
    FICTION("Fiction"),
    SCIENCE("Science"),
    HISTORY("History"),
    TECH("Technology"),
    KIDS("Kids");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}