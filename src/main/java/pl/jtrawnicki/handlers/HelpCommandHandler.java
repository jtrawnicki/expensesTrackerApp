package pl.jtrawnicki.handlers;

import pl.jtrawnicki.input.UserInputCommand;

import java.util.logging.Logger;

public class HelpCommandHandler extends BaseCommandHandler {

    Logger LOG = Logger.getLogger(HelpCommandHandler.class.getName());

    public static final String COMMAND_NAME = "help";

    @Override
    public void handle(UserInputCommand command) {
        LOG.info("----- HELP -----");
        System.out.println("Available commands: help, quit, category, expense, remove, update.");
        System.out.println("Command pattern: <command> <action> <param1> <param2>");
        System.out.println("Example: category add CategoryName");
    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
