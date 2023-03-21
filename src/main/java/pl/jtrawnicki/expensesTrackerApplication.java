package pl.jtrawnicki;

import pl.jtrawnicki.handlers.*;
import pl.jtrawnicki.input.UserInputCommand;
import pl.jtrawnicki.input.UserInputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class expensesTrackerApplication {
    public static void main(String[] args) {

        Logger LOG = Logger.getLogger(expensesTrackerApplication.class.getName());

        LOG.info("Start App...");
        boolean aplicationLoop = true;


        UserInputManager userInputManager = new UserInputManager();

        List<CommandHandler> handlers = new ArrayList<>();

        handlers.add(new HelpCommandHandler());
        handlers.add(new QuitCommandHandler());
        handlers.add(new CategoryCommandHandler());
        handlers.add(new ExpenseCommandHandler());



        while (aplicationLoop) {

            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand();
                LOG.info(userInputCommand.toString());

                Optional<CommandHandler> currentHandler = Optional.empty();
                for (CommandHandler handler : handlers) {
                    if (handler.support(userInputCommand.getCommand())) {
                        currentHandler = Optional.of(handler);
                    }
                }

                currentHandler
                        .orElseThrow(() -> new IllegalArgumentException("Unknown command..."))
                        .handle(userInputCommand);


            } catch (QuitApplicationException e) {
                aplicationLoop = false;

            } catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "Error: " + e.getMessage());

            } catch (Exception e) {
                LOG.log(Level.SEVERE,  e.getMessage());
            }

        }


    }
}
