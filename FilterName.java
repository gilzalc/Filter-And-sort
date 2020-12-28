package filesprocessing;
import java.io.File;
import java.util.function.BiPredicate;

public class FilterName extends Filter {
    private final String stringVal;
    private final BiPredicate<String, String> predictor;

    public FilterName(String filterString, int hashTagIndex,
                      BiPredicate<String, String> predictor) {
        super(filterString);
        this.predictor = (hasNot) ? predictor.negate() : predictor;
        this.stringVal = (this.hasNot)?
                filterString.substring(hashTagIndex+1,
                        filterString.length()-NOT_LENGTH):
                filterString.substring(hashTagIndex+1);
    }

    @Override
    public boolean accept(File pathname) {
        return predictor.test(pathname.getName(), this.stringVal);

    }
}
