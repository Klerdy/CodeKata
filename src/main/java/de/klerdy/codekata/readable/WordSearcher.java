package de.klerdy.codekata.readable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This interface searches for words within a {@link java.util.Collection}.
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

    /**
     * Creates a list of lists, each list containing words of a fixed length.
     * @param collection a collection with all words
     * @param length the count of collections that we want to extract.
     * @return a collection with collections
     */
    ArrayList<ArrayList<String>> getListsOfWordsGroupedByLength(Collection<String> collection, int length);

    /**
     * Tries to find a word in a collection.
     * @param collection the collection.
     * @param word the word we are looking for.
     * @return <code>true</code> if the collection contains the word, otherwise <code>false</code>.
     */
    boolean isWordInList(Collection<String> collection, String word);



}
