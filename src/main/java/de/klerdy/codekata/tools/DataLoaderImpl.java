package de.klerdy.codekata.tools;

import de.klerdy.codekata.constants.CodeKataConstants;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoaderImpl implements DataLoader, CodeKataConstants {

    private static final Charset ENCODING = Charsets.ISO_8859_1;

    private List<String> readInWords;

    @Override
    public List<String> readFile(String filename) throws IOException {
        String filePath = getClass().getClassLoader().getResource(filename).getPath();
        System.out.println("Reading file: " + filePath);
        readInWords = FileUtils.readLines(new File(filePath), ENCODING);
        removeWordsGreaterThanMaxWordLength();
        return readInWords;
    }

    /**
     * This method filters the word list and removes all words of length greater than
     * {@link CodeKataConstants#WORD_LENGTH}.
     *
     * @return a list of words with a length of 1 to {@link CodeKataConstants#WORD_LENGTH}
     */
    private void removeWordsGreaterThanMaxWordLength() {
        readInWords = readInWords.stream().filter(word -> word.length() <= WORD_LENGTH)
                .collect(Collectors.toList());
    }
}
