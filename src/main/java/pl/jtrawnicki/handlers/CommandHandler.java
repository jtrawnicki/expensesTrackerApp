package pl.jtrawnicki.handlers;

import pl.jtrawnicki.input.UserInputCommand;

public interface CommandHandler {
   void handle(UserInputCommand command);

   boolean support(String name);

}
