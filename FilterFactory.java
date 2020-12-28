package filesprocessing;

import java.io.File;

/**
 * A factory class for filter objects, implements the singleton principle
 */
public class FilterFactory {
    private static final String HASHTAG = "#";
    private static final String BETWEEN = "between";
    private static final String ALL = "all";
    private static final String GREATER_THAN = "greater_than";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String FILE = "file";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";


    private static final FilterFactory filterFactory = new FilterFactory();

    private FilterFactory() {
    }

    public static FilterFactory instance() {
        return filterFactory;
    }

    /**
     * creates the default filter, in case an error occurred
     *
     * @return Filter obj
     */
    public filesprocessing.Filter createDefaultFilter() {
        return new Filter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
    }

    public filesprocessing.Filter createFilter(String type)
                                    throws BadParametersError, BadNameError {
        // same case for greater or smaller than
        int hashTagIndex = type.indexOf(HASHTAG);
        String nameOfFilter = hashTagIndex == (-1) ? type : //case no hashtag
                type.substring(0, hashTagIndex);
        switch (nameOfFilter) {
            case GREATER_THAN:
            case SMALLER_THAN:
                return new FilterSize(type);
            case BETWEEN:
                return new FilterBetween(type);
            case ALL: {
                return new Filter(type) {
                    @Override
                    public boolean accept(File pathname) {
                        return !this.hasNot;
                    }
                };
            }
            case FILE:
                return new FilterName(type, hashTagIndex,
                        (String::equals));
            case CONTAINS:
                return new FilterName(type, hashTagIndex,
                        (String::contains));
            case PREFIX:
                return new FilterName(type, hashTagIndex,
                        (String::startsWith));
            case SUFFIX:
                return new FilterName(type, hashTagIndex,
                        (String::endsWith));
            case WRITABLE:
                return new FileAttributeFilter(type,
                        hashTagIndex, (File::canWrite));
            case EXECUTABLE:
                return new FileAttributeFilter(type,
                        hashTagIndex, (File::canExecute));
            case HIDDEN:
                return new FileAttributeFilter(type,
                        hashTagIndex, (File::isHidden));
        }
        throw new BadNameError();
    }

}

