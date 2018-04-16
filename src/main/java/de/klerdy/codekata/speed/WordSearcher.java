package de.klerdy.codekata.speed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This interface searches for words within a {@link Collection}.
 *
 * @author Norman Nusse
 */
public interface WordSearcher {

    /**
     * Creates a list with words of a certain length.
     * 
     * @param collection a collection with words
     * @param length the length of words we are looking for
     * @return a collection with words of the given length
     */
    List<String> getCollectionWithSpecificWordLength(Collection<String> collection, int length);

    /**
     * This method looking for all words of the length
     * {@link de.klerdy.codekata.constants.CodeKataConstants#WORD_LENGTH} which are composed of two
     * concatenated smaller words that exists in the given hash map.
     * 
     * @param words a list of words (length = {@link de.klerdy.codekata.constants.CodeKataConstants#WORD_LENGTH}).
     * @param allWords a map with all known words (length: 1 to {@link de.klerdy.codekata.constants.CodeKataConstants#WORD_LENGTH}).
     * @return a list with all matched words.
     */
    List<String> findWordsOfSpecificLengthAndConcatenatedSmallerWords(
            Collection<String> words, Map<String, Short> allWords);

}
