package de.klerdy.codekata;

import de.klerdy.codekata.config.CodeKataSpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CodeKataSpringConfiguration.class);
        Application app = context.getBean(Application.class);
        app.start();
    }
}
