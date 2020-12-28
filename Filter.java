package filesprocessing;

import java.io.File;
import java.io.FileFilter;

public abstract class Filter implements FileFilter {
    protected static String NOT = "NOT";
    protected static String HASHTAG = "#";
    protected boolean hasNot;
    protected static final int NOT_LENGTH = 4;
    private String filterString;

    public Filter(){} // empty String;
    public Filter(String filterString) {
        hasNot = filterString.endsWith(HASHTAG+NOT);
        this.filterString = filterString;
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
    public abstract boolean accept(File pathname);

    private String getFilterString() {
        return filterString;
    }
}