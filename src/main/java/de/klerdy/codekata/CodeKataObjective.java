package de.klerdy.codekata;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * This interface provides a specific solution version for this codeKata (readable, speed or extendible).
 * @author Norman Nusse
 */
public interface CodeKataObjective {

    /**
     * This method looking for words in a dictionary with a specific length which are composed
     * of two concatenated smaller words (also from the dictionary).
     * @return a list of words with a specific length.
     * @throws IOException I/O error has occurred.
     */
    List<String> start() throws IOException;

}
