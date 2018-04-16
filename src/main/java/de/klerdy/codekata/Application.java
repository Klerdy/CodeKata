package de.klerdy.codekata;

import de.klerdy.codekata.config.CodeKataSpringConfiguration;
import de.klerdy.codekata.constants.CodeKataConstants;
import de.klerdy.codekata.tools.DataLoader;
import de.klerdy.codekata.tools.DataWriter;
import de.klerdy.codekata.tools.StopWatch;
import de.klerdy.codekata.tools.Time;
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

public class Application implements CodeKataConstants {

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.OBJECTIVE_READABLE)
    private CodeKataObjective readable;

    @Autowired
    @Qualifier(CodeKataSpringConfiguration.OBJECTIVE_SPEED)
    private CodeKataObjective speed;

    @Autowired
    private DataWriter dataWriter;

    private List<String> foundWords;

    /**
     * Starts the different versions.
     */
    public void start(){
        try {
            System.out.println("Starting Readable CodeKata Version.");
            System.out.println("===================================");

            foundWords = readable.start();
            dataWriter.writeWordsToFile(foundWords, RESULT_READ);

            System.out.println("\nStarting Speed CodeKata Version.");
            System.out.println("===================================");

            foundWords = speed.start();
            dataWriter.writeWordsToFile(foundWords, RESULT_SPEED);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
