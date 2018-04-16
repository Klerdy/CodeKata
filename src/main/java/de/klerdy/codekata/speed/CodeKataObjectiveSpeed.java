package de.klerdy.codekata.speed;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import de.klerdy.codekata.CodeKataObjective;
import de.klerdy.codekata.config.CodeKataSpringConfiguration;
import de.klerdy.codekata.constants.CodeKataConstants;
import de.klerdy.codekata.tools.DataLoader;
import de.klerdy.codekata.tools.StopWatch;
import de.klerdy.codekata.tools.Time;

/**
 * The implementation of the sub-objective: speed.
 *
 * @author Norman Nusse
 */
public class CodeKataObjectiveSpeed implements CodeKataObjective, CodeKataConstants {

    @Autowired
    private StopWatch watch;

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.WORDSEARCHER_SPEED)
    private de.klerdy.codekata.speed.WordSearcher wordSearcher;

    @Autowired
    private DataLoader dataLoader;

    private List<String> matchedWords = null;
    private List<String> readInWords = null;
    private List<String> wordsWithMaxLength = null;
    private Map<String, Short> mapWithWords;

    @Override
    public List<String> start() throws IOException {
        watch.reset();
        watch.start();

        fillDictionaryAndGenerateHashMap();
        createListWithWordsOfFixedLength();
        searchingForMatchedWords();

        watch.stop();
        System.out.println("Algorithm needs " + watch.getRuntime(Time.SECONDS) + "s to finish.");
        return matchedWords;
    }

    /**
     * This method analyses all words with the length of {@link #WORD_LENGTH} and compares them and
     * the associated concatenated smaller words with the keys of the Hash-Map.
     */
    private void searchingForMatchedWords() {
        System.out.println("Searching words with length=" + WORD_LENGTH + " ... -> Please wait.");
        matchedWords = wordSearcher.findWordsOfSpecificLengthAndConcatenatedSmallerWords(
                wordsWithMaxLength, mapWithWords);
        System.out.println("Found " + matchedWords.size() + " valid words.");
    }

    /**
     * This method filters the {@link #readInWords}-list and saves the result into a new varible
     * ({@link #wordsWithMaxLength}).
     */
    private void createListWithWordsOfFixedLength() {
        wordsWithMaxLength =
                wordSearcher.getCollectionWithSpecificWordLength(readInWords, WORD_LENGTH);
    }

    /**
     * This is a helper method that loads data from a file and put them into a list. After that, it
     * will generate a Hash-Map that holds each entry of the list.
     *
     * @throws IOException I/O error has occurred.
     */
    private void fillDictionaryAndGenerateHashMap() throws IOException {
        readInWords = dataLoader.readFile(FILE_NAME);
        mapWithWords = readInWords.stream()
                .collect(Collectors.toMap(key -> key, number -> Short.valueOf("0")));
    }

}
