package filesprocessing;

import java.io.File;
import java.util.Comparator;

/**
 * A factory of
 */
public class OrderFactory {
    static final String ABSOLUT = "abs";
    static final String SIZE = "size";
    static final String TYPE = "type";
    static final String HASHTAG = "#";
    static final String REVERSED = "REVERSE";
    private static final OrderFactory OrderFactory = new OrderFactory();

    private OrderFactory() {
    }

    public static OrderFactory instance() {
        return OrderFactory;
    }

    public filesprocessing.Order createDefaultOrder() {
        return Order.ABS;
    }

    public Comparator<File> createOrder(String type) throws BadNameError {
        switch (type) {
            case SIZE + HASHTAG + REVERSED:
                return Order.SIZE.reversed();
            case TYPE + HASHTAG + REVERSED:
                return Order.TYPE.reversed();
            case ABSOLUT + HASHTAG + REVERSED:
                return Order.ABS.reversed();
            case SIZE:
                return Order.SIZE;
            case TYPE:
                return Order.TYPE;
            default:
                if (!type.equals(ABSOLUT))
                    throw new BadNameError();
                return Order.ABS;
        }
    }
}
