package filesprocessing;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for the parsing of the command file, and for the
 * categorization of its sections, including the warning massages array of
 * the section
 * @author gilzalc
 */
public class Parser {
    private static final String BAD_FILTER_SECTION = "wrong format of the " +
            "FILTER subsection String";
    private static final String BAD_ORDER_SECTION = "wrong format of the " +
            "ORDER subsection String";
    private static final String NO_FILTER_SECTION = "No FILTER subsection " +
            "found";
    private static final String IO_ERROR = "I/O ERROR accorded while parsing " +
            "command file";
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String ABS = "abs";
    private final ArrayList<Section> sections;
    private final LineNumberReader lineNumberReader;

    public Parser(File commandFile) throws FileNotFoundException {
         this.lineNumberReader =
                new LineNumberReader(new FileReader(commandFile.getAbsolutePath()));
        sections = new ArrayList<>();
    }

    /**
     * A method that iterates all over the command file and extracts it sections
     * @return the array of the sections that were created
     * @throws Type2Error Bad Sub section name, or lacking of one
     */
    public ArrayList<Section> parseFile() throws Type2Error {
        try {
            String line = this.lineNumberReader.readLine();
//            if (line == null)
//                throw new Type2Error(NO_FILTER_SECTION);
            while (line != null) {
                line = parseSection(line);
            }
            return sections;
        } catch (IOException e) {
            throw new Type2Error(IO_ERROR);
        }
    }

    /**
     * Responsible for the parsing of each section
     * @param line current line that we are in
     * @return current line when leaving method
     * @throws Type2Error Bad Sub section name, or lacking of one
     * @throws IOException in case bad format of command line
     */
    public String parseSection(String line) throws Type2Error, IOException {
        Section section; // new Section object to add
        int lineNumber = this.lineNumberReader.getLineNumber();
        if (!line.equals(FILTER))
            throw new Type2Error(BAD_FILTER_SECTION); // handle in main
        String filter = this.lineNumberReader.readLine();
        if ((line = this.lineNumberReader.readLine())==null || !line.equals(ORDER))
            throw new Type2Error(BAD_ORDER_SECTION); //handle in main
        line = this.lineNumberReader.readLine();
        if (line == null || line.equals(FILTER)) {
            section = new Section(lineNumber, filter, ABS);
        } else { //has four rows
            section = new Section(lineNumber, filter, line);
            line = this.lineNumberReader.readLine();
        }
        sections.add(section);
        return line;
    }

}
