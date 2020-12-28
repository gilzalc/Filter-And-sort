package filesprocessing;

import java.io.File;
import java.util.function.Predicate;

public  class FileAttributeFilter extends Filter {
    private static final String YES = "YES";
    private static final String NO = "NO";
    private final Predicate<File> predictor;

    public FileAttributeFilter(String filterString, int hashTagIndex,
                               Predicate<File> predictor) throws BadParametersError {
        super(filterString);
        String toCheck = filterString.substring(hashTagIndex);
        if (!toCheck.equals(HASHTAG + NO) && !toCheck.equals(HASHTAG + YES)) {
            throw new BadParametersError();
        }
        this.predictor = (toCheck.equals(HASHTAG + YES) ? predictor :
                predictor.negate());
    }

    @Override
    public boolean accept(File pathname) {
        if (hasNot) {
            return !predictor.test(pathname);
        }
        return predictor.test(pathname);
    }
}