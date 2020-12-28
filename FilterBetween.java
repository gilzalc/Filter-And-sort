package filesprocessing;

import java.io.File;

public class FilterBetween extends FilterSize {
    private double firstParam;
    private double secondParam;

    public FilterBetween(String filterString) throws BadParametersError {
        super(filterString);
    }

    @Override
    protected void checkParam(String paramString) throws BadParametersError {
        String[] parameters = paramString.split(HASHTAG);
        this.firstParam = Double.parseDouble(parameters[0]);
        this.secondParam = Double.parseDouble(parameters[1]);
        if (this.firstParam < 0 || this.secondParam < 0 || this.firstParam > this.secondParam)
            throw new BadParametersError();
    }


    @Override
    public boolean accept(File pathname) {
        double space = (double)pathname.length() / BYTES_IN_KILO;
        return (space >= this.firstParam && space <= this.secondParam);
    }
}
