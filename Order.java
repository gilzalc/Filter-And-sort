package filesprocessing;

import java.io.File;
import java.util.Comparator;

/**
 * An enum that represents the orders objects of the sections
 */
enum Order implements Comparator<File> {
    TYPE {
        @Override
        public int compare(File a, File b) {
            int typeDif = getExtension(a).compareTo(getExtension(b));
            return  typeDif==0?
                    Order.ABS.compare(a,b):typeDif;
        }
    }, ABS {
        @Override
        public int compare(File a, File b) {
            return a.getAbsolutePath().compareTo(b.getAbsolutePath());
        }
    },
    SIZE {
        @Override
        public int compare(File a, File b) {
            int sizeDif = (int) (a.length() - b.length());
            return sizeDif==0?
                    Order.ABS.compare(a,b):sizeDif;
        }
    };

    @Override
    public abstract int compare(File o1, File o2);


    private static String getExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}