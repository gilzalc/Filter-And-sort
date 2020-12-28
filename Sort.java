package filesprocessing;

import java.io.File;
import java.util.*;

/**
 * A quick sort class for the program;
 * used to sort the files in the required order before printing them.
 */
public class Sort {
    private final Comparator<File> order;
    private final ArrayList<File> files;

    /**
     * constructor of a sorting object;
     *
     * @param order The comparator to use for the sorting
     * @param files The array of the files to sort
     */
    public Sort(Comparator<File> order, ArrayList<File> files) {
        this.order = order;
        this.files = files;
    }

    public ArrayList<File> sortFiles() {
        sortFilesHelper(0, files.size() - 1);
        return this.files;
    }

    private void sortFilesHelper(int start,
                                 int end) {
        if (start < end) {
            int p = partition(start, end);
            sortFilesHelper(start, p-1);
            sortFilesHelper(p+1, end);
        }
    }

    private int getPivot(int start, int end) {
        Random random = new Random();
        return random.nextInt((end - start) + 1) + start;
    }

    private int partition(int start, int end) {
        Collections.swap(this.files, start, (getPivot(start, end)));
        int border = start;
        for (int i = start; i <= end; i++) {
            if ((this.order.compare(this.files.get(i), this.files.get(start)) < 0))
                Collections.swap(this.files, i, (++border));
        }
        Collections.swap(this.files, start, border);
        return border;
    }

}
