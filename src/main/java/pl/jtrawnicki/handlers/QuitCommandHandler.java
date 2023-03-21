package pl.jtrawnicki.handlers;

import pl.jtrawnicki.QuitApplicationException;
import pl.jtrawnicki.input.UserInputCommand;

import java.util.logging.Logger;

public class QuitCommandHandler extends BaseCommandHandler {

    Logger LOG = Logger.getLogger(QuitCommandHandler.class.getName());

    public static final String COMMAND_NAME = "quit";

    @Override
    public void handle(UserInputCommand command) {
        LOG.info("Quit...");
        throw new QuitApplicationException();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
