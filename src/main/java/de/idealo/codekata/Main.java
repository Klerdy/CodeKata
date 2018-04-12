package de.idealo.codekata;

import de.idealo.codekata.config.CodeKataSpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CodeKataSpringConfiguration.class);
        Application app = context.getBean(Application.class);
        app.start(args);
    }
}
