package de.klerdy.codekata.tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * The implementation of the DataWriter.
 *
 * @author Norman Nusse
 */
public class DataWriterImpl implements DataWriter{
    @Override
    public void writeWordsToFile(Collection<String> words, String fileName) throws IOException {
        File saveFile = new File(fileName);
        StringBuilder content = new StringBuilder();
        words.forEach(word -> content.append(word + "\n"));
        System.out.println("\n-> Write matching words to file: " + saveFile.getAbsolutePath());
        FileUtils.writeStringToFile(saveFile, words.toString());
    }
}
