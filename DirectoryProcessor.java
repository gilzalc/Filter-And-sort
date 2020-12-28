package filesprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class that process the directory, filters it and prints it
 *
 * @author gilzalc
 */
public class DirectoryProcessor {
    private static final int ARGS_NUMBER = 2;
    private static final String INVALID_USAGE = "invalid usage";
    private static final String IO_EXCEPTION_MSG = "Error: IO exception";

    public static void main(String[] args) throws IOException, Type2Error,
            BadNameError, BadParametersError { // handle here type II errors
        try {
            if (!checkArgs(args)) // check that args is valid
            {
                File[] filesDirectory = new File(args[0]).listFiles();
                if (filesDirectory == null)
                    return;
                ArrayList<Section> sections = createSections(new File(args[1]));
                for (Section sec : sections) {
                    ArrayList<File> files = getValidFiles(filesDirectory);
                    sec.printWarnings();
                    ArrayList<File> filtered = filterFiles(sec.getFilter(), files);
                    ArrayList<File> sorted = sortFiles(sec.getOrder(), filtered);
                    printFiles(sorted);
                }

            }
        } catch (Type2Error e){
            e.printMassage();
        }
        catch (IOException e){
            System.err.println(IO_EXCEPTION_MSG);
        }
    }

    private static void printFiles(ArrayList<File> filesToPrint) {
        for (File toPrint : filesToPrint)
            System.out.println(toPrint.getName());
    }

    private static ArrayList<File> sortFiles(Comparator<File> order,
                                             ArrayList<File> filtered) {
        Sort sorter = new Sort(order, filtered);
        return sorter.sortFiles();
    }

    private static ArrayList<File> filterFiles(Filter filter, ArrayList<File> files) {
        ArrayList<File> toAdd = new ArrayList<>();
        for (File file : files) {
            if (filter.accept(file))
                toAdd.add(file);
        }
        return toAdd;
    }

    private static void handleType1Error(Type1Error error) {
    }

    private static void handleType2Error(Type2Error error) {
    }

    private static ArrayList<Section> createSections(File commandFile) throws Type2Error,
            IOException, BadNameError, BadParametersError {
        Parser parser = new Parser(commandFile);
        return parser.parseFile();
    }

    private static ArrayList<File> getValidFiles(File[] files) {
        ArrayList<File> filesList = new ArrayList<>();
        for (File file : files) {
            if (!file.isDirectory())
                filesList.add(file);
        }
        return filesList;
    }


    private static boolean checkArgs(String[] args) throws Type2Error {
        if (args.length != ARGS_NUMBER) {
            throw new Type2Error(INVALID_USAGE);
        }
        if (new File(args[1]).isDirectory()) {
            throw new Type2Error(INVALID_USAGE);
        }
        return false;
    }
}
