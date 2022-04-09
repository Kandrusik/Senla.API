package utilsAPI;

public enum Status {

    AVAILABLE ("available"),
    PENDING ("pending"),
    SOLD ("sold"),
    TRUE ("true");


    public final String label;

    private Status(String label) {
        this.label = label;
    }

}