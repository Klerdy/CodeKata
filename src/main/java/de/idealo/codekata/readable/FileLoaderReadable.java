package de.idealo.codekata.readable;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class FileLoaderReadable implements FileLoader{

    private static final Charset ENCODING = Charsets.ISO_8859_1;

    @Override
    public List<String> readFile(String filename) throws IOException {
        String filePath = getClass().getClassLoader().getResource(filename).getPath();
        System.out.println("Reading file: " + filePath);
        return FileUtils.readLines(new File(filePath), ENCODING);
    }
}
