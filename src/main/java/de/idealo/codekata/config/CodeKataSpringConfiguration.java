package de.idealo.codekata.config;

import de.idealo.codekata.Application;
import de.idealo.codekata.readable.FileLoader;
import de.idealo.codekata.readable.FileLoaderReadable;
import de.idealo.codekata.readable.WordSearcher;
import de.idealo.codekata.readable.WordSearcherReadable;
import de.idealo.codekata.speed.WordSearcherSpeed;
import de.idealo.codekata.tools.StopWatch;
import de.idealo.codekata.tools.StopWatchImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeKataSpringConfiguration {

    public static final String FILELOADER_READABLE = "fileLoaderReadable";
    public static final String WORDSEARCHER_READABLE = "wordSearcherReadable";
    public static final String WORDSEARCHER_SPEED = "speedWordSearcher";

    @Bean(name = FILELOADER_READABLE)
    public FileLoader readableFileLoader() {
        return new FileLoaderReadable();
    }

    @Bean(name = WORDSEARCHER_READABLE)
    public WordSearcher readableWordSearcher() {
        return new WordSearcherReadable();
    }

    @Bean(name = WORDSEARCHER_SPEED)
    public de.idealo.codekata.speed.WordSearcher speedWordSearcher() {
        return new WordSearcherSpeed();
    }

    @Bean
    public StopWatch stopWatch(){
        return new StopWatchImpl();
    }

    @Bean
    public Application application(){
        return new Application();
    }
}
