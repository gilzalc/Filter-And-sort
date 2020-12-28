package filesprocessing;

/**
 * A general Exception class for all of the Type II errors, with a constructor
 * that defines its massage attribute
 */
public class Type2Error extends Exception {
    private static final long serialVersionUID = 1L;
    private final String massage;

    public Type2Error(String massage) {
        this.massage = massage;
    }

    public void printMassage() {
        System.err.println("ERROR: " + massage);
    }
}
