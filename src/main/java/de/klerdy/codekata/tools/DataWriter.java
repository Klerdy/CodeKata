package de.klerdy.codekata.tools;

import java.io.IOException;
import java.util.Collection;

/**
 * This interface writes a list of Strings into a data source.
 *
 * @author Norman Nusse
 */
public interface DataWriter {
    /**
     * Writes a list of strings into a file.
     * @param words the wordlist.
     * @param fileName the name of the new file.
     * @throws IOException I/O error has occurred.
     */
    void writeWordsToFile(Collection<String> words, String fileName) throws IOException;
}
