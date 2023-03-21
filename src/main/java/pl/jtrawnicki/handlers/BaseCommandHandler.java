package pl.jtrawnicki.handlers;

import pl.jtrawnicki.handlers.CommandHandler;

abstract class BaseCommandHandler implements CommandHandler {

    @Override
    public boolean support(String name) {
        return getCommandName().equals(name);
    }

    protected abstract String getCommandName();

}
