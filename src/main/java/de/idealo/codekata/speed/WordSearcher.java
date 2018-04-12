package de.idealo.codekata.speed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This interface searches for words within a {@link Collection}.
 *
 * @author norman.nusse
 */
public interface WordSearcher {

    /**
     * Creates a list with words of a certain length.
     * @param collection a collection with words
     * @param length the length of words we are looking for
     * @return a collection with words of the given length
     */
    List<String> getCollectionWithSpecificWordLength(Collection<String> collection, int length);

    List<String> findWordsOfSpecificLengthAndCombination(final Collection<String> words, Map<String, Integer> allWords);

}
