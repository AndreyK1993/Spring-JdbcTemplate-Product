package app.utils;

import app.conf.AppConfig;
import app.controller.ContactController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter {

    public static void startApp() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ContactController contactController = context.getBean(ContactController.class);
        contactController.getOption();

        context.close();
    }
}
