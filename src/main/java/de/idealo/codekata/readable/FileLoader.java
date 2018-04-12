package de.idealo.codekata.readable;

import java.io.IOException;
import java.util.List;

/**
 * This service reads a file and provides the data in a {@link java.awt.List}.
 *
 * @author norman.nusse
 */
public interface FileLoader {
    /** reads a file and put the result into a list. */
    List<String> readFile(final String filename) throws IOException;
}
