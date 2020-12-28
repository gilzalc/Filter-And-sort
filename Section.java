package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class Section {
    private static final int FILTER_ROW_NUM = 1;
    private static final int ORDER_ROW_NUM = 3;
    private Filter filter;
    private Comparator<File> order;
    private final ArrayList<String> warnings;
    private static final String WARNING_STRING = "Warning in line %o";

    public Section(int lineNumber, String filterString, String order) {
        warnings = new ArrayList<>();
        FilterFactory filterFac = FilterFactory.instance();
        try {
            this.filter = filterFac.createFilter(filterString);
        } catch (Type1Error error) {
            warnings.add(this.formatWarnings(lineNumber + FILTER_ROW_NUM));
            this.filter = filterFac.createDefaultFilter();
        }
        OrderFactory orderFactory = OrderFactory.instance(); // maybe in
        try {
            this.order = orderFactory.createOrder(order);
        } catch (BadNameError error) {
            warnings.add(this.formatWarnings(lineNumber + ORDER_ROW_NUM));
            this.order = orderFactory.createDefaultOrder();
        }

    }

    private String formatWarnings(int lineNum) {
        return String.format(WARNING_STRING, lineNum);
    }

    public Filter getFilter() {
        return filter;
    }

    public Comparator<File> getOrder() {
        return order;
    }

    public void printWarnings() {
        for (String warning : warnings)
            System.err.println(warning);
    }
}

