package de.klerdy.codekata.config;

import de.klerdy.codekata.CodeKataObjective;
import de.klerdy.codekata.readable.CodeKataObjectiveReadable;
import de.klerdy.codekata.readable.WordSearcherReadable;
import de.klerdy.codekata.Application;
import de.klerdy.codekata.speed.CodeKataObjectiveSpeed;
import de.klerdy.codekata.tools.DataLoader;
import de.klerdy.codekata.tools.DataLoaderImpl;
import de.klerdy.codekata.readable.WordSearcher;
import de.klerdy.codekata.speed.WordSearcherSpeed;
import de.klerdy.codekata.tools.DataWriter;
import de.klerdy.codekata.tools.DataWriterImpl;
import de.klerdy.codekata.tools.StopWatch;
import de.klerdy.codekata.tools.StopWatchImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * The Spring-Configuration.
 */
@Configuration
public class CodeKataSpringConfiguration {

    /**
     * Bean-names.
     */
    public static final String WORDSEARCHER_READABLE = "wordSearcherReadable";
    public static final String WORDSEARCHER_SPEED = "speedWordSearcher";
    public static final String OBJECTIVE_READABLE = "readable";
    public static final String OBJECTIVE_SPEED = "speed";


    @Bean(name = WORDSEARCHER_READABLE)
    public WordSearcher readableWordSearcher() {
        return new WordSearcherReadable();
    }

    @Bean(name = WORDSEARCHER_SPEED)
    public de.klerdy.codekata.speed.WordSearcher speedWordSearcher() {
        return new WordSearcherSpeed();
    }

    @Bean(name = OBJECTIVE_READABLE)
    @DependsOn(value = {WORDSEARCHER_READABLE })
    public CodeKataObjective readable() {
        return new CodeKataObjectiveReadable();
    }

    @Bean(name = OBJECTIVE_SPEED)
    @DependsOn(value = {WORDSEARCHER_SPEED })
    public CodeKataObjective speed() {
        return new CodeKataObjectiveSpeed();
    }

    @Bean
    public DataLoader dataLoader() {
        return new DataLoaderImpl();
    }

    @Bean
    public DataWriter dataWriter() {
        return new DataWriterImpl();
    }

    @Bean
    public StopWatch stopWatch() {
        return new StopWatchImpl();
    }

    @Bean
    public Application application() {
        return new Application();
    }
}
