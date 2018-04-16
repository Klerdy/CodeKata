package de.klerdy.codekata.tools;

import java.io.IOException;
import java.util.List;

/**
 * This service loads the data of a given source and provides them in form of a specific data
 * structure.
 *
 * @author Norman Nusse
 */
public interface DataLoader {
    /** reads a file and put the result into a list. */
    List<String> readFile(final String filename) throws IOException;
}
