package de.idealo.codekata;

import de.idealo.codekata.config.CodeKataSpringConfiguration;
import de.idealo.codekata.readable.FileLoader;
import de.idealo.codekata.readable.WordSearcher;
import de.idealo.codekata.tools.StopWatch;
import de.idealo.codekata.tools.Time;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: Die Versionen des Katas auch per DI lösen
public class Application {

    private static final String FILE_NAME = "wordlist.txt";
    private static final String WRITE_TO_FILE = "matchedWords.txt";
    private static final int WORD_LENGTH = 6;

    @Autowired
    private StopWatch watch;

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.WORDSEARCHER_READABLE)
    private WordSearcher wordSearcherRead;

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.WORDSEARCHER_SPEED)
    private de.idealo.codekata.speed.WordSearcher wordSearcherSpeed;

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.FILELOADER_READABLE)
    private FileLoader readableFileLoader;


    public void start(String[] args){
        try {
            List<String> foundWords;

            System.out.println("Starting Readable CodeKata Version.");
            System.out.println("===================================");

            foundWords = startReadableVersion();

            System.out.println("\nStarting Speed CodeKata Version.");
            System.out.println("===================================");

            startFastVersion();

            writeMatchedWordsToFile(foundWords);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> startReadableVersion() throws IOException {
        watch.reset();
        watch.start();
        // === START MEASUREMENT ===
        final ArrayList<String> matchedWords = new ArrayList<>();
        final List<String> allWordsReadable = readableFileLoader.readFile(FILE_NAME);
        final ArrayList<ArrayList<String>> wordsGroupedByLength =
                wordSearcherRead.getCollectionWithWordsGroupedByLength(allWordsReadable, WORD_LENGTH);
        int counterFoundWordsWithCombination = 0;

        System.out.println("Search for words... (" + "length=" + WORD_LENGTH + ") -> Please wait.");
        for (String word: wordsGroupedByLength.get(WORD_LENGTH-1)) {
            counterFoundWordsWithCombination += getCombinations(matchedWords, wordsGroupedByLength, word);
        }
        System.out.println("Found " + counterFoundWordsWithCombination + " valid words.");
        // === END MEASUREMENT ===
        watch.stop();
        System.out.println("Algorithm needs " + watch.getRuntime(Time.SECONDS) + "s to finish.");
        return matchedWords;
    }


    private ArrayList<String> startFastVersion() throws IOException {
        watch.reset();
        watch.start();
        // === START MEASUREMENT ===
        final ArrayList<String> matchedWords;
        final List<String> allWordsAsList = readableFileLoader.readFile(FILE_NAME);
        final Map<String, Integer> allWordsAsMap = allWordsAsList
                .stream().collect(Collectors.toMap(key -> key, number -> 0));
        final List<String> listOfWordsWithLength = wordSearcherSpeed.getCollectionWithSpecificWordLength(allWordsAsList, WORD_LENGTH);

        System.out.println("Search for words... (" + "length=" + WORD_LENGTH + ") -> Please wait.");
        matchedWords = (ArrayList<String>) wordSearcherSpeed.findWordsOfSpecificLengthAndCombination(listOfWordsWithLength, allWordsAsMap);
        System.out.println("Found " + matchedWords.size() + " valid words.");

        // === END MEASUREMENT ===
        watch.stop();
        System.out.println("Algorithm needs " + watch.getRuntime(Time.SECONDS) + "s to finish.");
        return matchedWords;
    }

    private void writeMatchedWordsToFile(Collection<String> matchedWords) throws IOException {
        File saveFile = new File(WRITE_TO_FILE);
        StringBuilder words = new StringBuilder();
        matchedWords.forEach(word -> words.append(word + "\n"));
        System.out.println("\nWrite matching words to file: " + saveFile.getAbsolutePath());
        FileUtils.writeStringToFile(saveFile, words.toString());
    }

    /**
     * Helper method to get all words with the specific {@link #WORD_LENGTH}. The word will be added to a list, if
     * we find at least one combination of the word in the given data as independent word.
     */
    private int getCombinations(Collection<String> matchedWords, ArrayList<ArrayList<String>> wordsGroupedByLength, String word) {
        for (int i = 0; i < WORD_LENGTH-1; i++) {
            String leftWordPart = word.substring(0, i+1);
            String rightWordPair = word.substring(i+1, WORD_LENGTH);
            if(wordsGroupedByLength.get(i).contains(leftWordPart)
                    && wordsGroupedByLength.get(WORD_LENGTH-1 - (i+1)).contains(rightWordPair)) {
                matchedWords.add(word + "-> '" + leftWordPart + ", " + rightWordPair + "'");
                return 1;
            }
        }
        return 0;
    }

}
