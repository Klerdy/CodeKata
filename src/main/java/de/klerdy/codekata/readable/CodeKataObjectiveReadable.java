package de.klerdy.codekata.readable;

import de.klerdy.codekata.CodeKataObjective;
import de.klerdy.codekata.config.CodeKataSpringConfiguration;
import de.klerdy.codekata.constants.CodeKataConstants;
import de.klerdy.codekata.tools.DataLoader;
import de.klerdy.codekata.tools.StopWatch;
import de.klerdy.codekata.tools.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the sub-objective: readable.
 * 
 * @author Norman Nusse
 */
public class CodeKataObjectiveReadable implements CodeKataObjective, CodeKataConstants {

    @Autowired
    private StopWatch watch;

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.WORDSEARCHER_READABLE)
    private WordSearcher wordSearcher;

    @Autowired
    private DataLoader fileLoader;

    private ArrayList<ArrayList<String>> listOfWordsGroupedByLength;
    private final ArrayList<String> matchedWords = new ArrayList<>();
    private int foundWords = 0;

    @Override
    public List<String> start() throws IOException {
        watch.reset();
        watch.start();

        fillDictionaryAndExtractWordsByLength();
        searchForValidWords();

        watch.stop();
        System.out.println("Algorithm needs " + watch.getRuntime(Time.SECONDS) + "s to finish.");
        return matchedWords;
    }

    /**
     * This is a helper method that loads data from a file and put them into a single list. After that,
     * this method extracts words of a fix length and put them to a new list.
     *
     * @throws IOException I/O error has occurred.
     */
    private void fillDictionaryAndExtractWordsByLength() throws IOException {
        List<String> readInWords = fileLoader.readFile(FILE_NAME);
        listOfWordsGroupedByLength =
                wordSearcher.getListsOfWordsGroupedByLength(readInWords, WORD_LENGTH);
    }

    /**
     * This method looking for words of length {@link CodeKataConstants#WORD_LENGTH} which are
     * composed of two concatenated smaller words, that are also a part of the dictionary.
     */
    private void searchForValidWords() {
        System.out.println("Searching words with length=" + WORD_LENGTH + " ... -> Please wait.");
        for (final String word : listOfWordsGroupedByLength.get(WORD_LENGTH - 1)) {
            analyseWordAndFindConcatenatedSmallerWords(word);
        }
        System.out.println("Found " + foundWords + " valid words.");
    }

    /**
     * This method splits a word iteratively into two smaller words. It aborts if the
     * dictionary contains at least one combination of the extracted smaller words.
     *
     * @param word the word that should be analysed.
     */
    private void analyseWordAndFindConcatenatedSmallerWords(final String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            final String leftWordPart = word.substring(0, i + 1);
            final String rightWordPair = word.substring(i + 1, word.length());
            // quit if we find at least one combination.
            if (existsAtLeastOneCombinationOfSmallerWords(word, i, leftWordPart, rightWordPair))
                return;
        }
    }

    /**
     * This method checks if the dictionary contains the given concatenated smaller words.
     * 
     * @param word the compound word.
     * @param charPos a character pointer.
     * @param leftWord the extracted word (left)
     * @param rightWord the extracted word (right)
     * @return <code>true</code> if there is at least one combination of smaller words in the
     *         dictionary.
     */
    private boolean existsAtLeastOneCombinationOfSmallerWords(final String word, final int charPos,
            final String leftWord, final String rightWord) {
        if (wordSearcher.isWordInList(listOfWordsGroupedByLength.get(charPos), leftWord)
                && wordSearcher.isWordInList(
                        listOfWordsGroupedByLength.get((WORD_LENGTH - 1) - (charPos + 1)),
                        rightWord)) {
            matchedWords.add(word + "-> '" + leftWord + ", " + rightWord + "'");
            foundWords++;
            return true;
        }
        return false;
    }

}
