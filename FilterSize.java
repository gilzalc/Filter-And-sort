package filesprocessing;


import java.io.File;

public class FilterSize extends Filter {
    private final boolean greater; // true if greater_Than filter, false if smaller
    private static final String GREATER = "greater_than";
    private final String paramString;
    private double param;
    protected static final long BYTES_IN_KILO = 1024;

    public FilterSize(String filterString) throws BadParametersError {
        super(filterString);
        greater = filterString.startsWith(GREATER+HASHTAG);
        int length = filterString.length();
        int paramIndex = filterString.indexOf(HASHTAG);
        this.paramString = this.hasNot ? filterString.substring(paramIndex + 1,
                length - NOT_LENGTH)
                : filterString.substring(paramIndex + 1);
        checkParam(paramString);

    }

    protected void checkParam(String paramString) throws BadParametersError {
        this.param = Double.parseDouble(this.paramString);
        if (param < 0)
            throw new BadParametersError();
    }

    /**
     * Tests whether or not the specified abstract pathname should be
     * included in a pathname list.
     *
     * @param pathname The abstract pathname to be tested
     * @return <code>true</code> if and only if <code>pathname</code>
     * should be included
     */
    @Override
    public boolean accept(File pathname) {

        double space = (double) pathname.length() / BYTES_IN_KILO;
        if (greater)
            return hasNot == (space <= this.param);
        else {
            return hasNot ? (this.param <= space) : (space < this.param);
        }
    }
}
