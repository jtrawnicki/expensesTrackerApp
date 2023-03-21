package pl.jtrawnicki.handlers;

import pl.jtrawnicki.dao.CategoryDao;
import pl.jtrawnicki.dao.ExpenseDao;
import pl.jtrawnicki.input.UserInputCommand;
import pl.jtrawnicki.model.Category;
import pl.jtrawnicki.model.Expense;

import java.util.List;
import java.util.logging.Logger;


public class ExpenseCommandHandler extends BaseCommandHandler {

    Logger LOG = Logger.getLogger(ExpenseCommandHandler.class.getName());

    private ExpenseDao expenseDao;

    private CategoryDao categoryDao;


    public ExpenseCommandHandler() {
        expenseDao = new ExpenseDao();
        categoryDao = new CategoryDao();
    }

    private static final String COMMAND_HANDLER = "expense";

    @Override
    protected String getCommandName() {
        return COMMAND_HANDLER;
    }

    @Override
    public void handle(UserInputCommand command) {

        if (command.getAction() != null) {

            switch(command.getAction()) {

                case LIST:
                        LOG.info("list of expenses...");

                        if(!command.getParams().isEmpty()) {
                            throw new IllegalArgumentException("This command doesn't support any params");
                        }

                        List<Expense> expenses = expenseDao.findAllExpenses();
                        expenses.forEach(System.out::println);
                        break;

                case ADD:
                    LOG.info("add new expense...");

                    if (command.getParams().size() != 3) {
                        throw new IllegalArgumentException("This command support only three params(expenseName, category, expensePrice)");
                    }

                    String categoryName = command.getParams().get(0);
                    String expenseName = command.getParams().get(1);
                    String expensePrice = command.getParams().get(2);
                    Category category = categoryDao.findOne(categoryName)
                            .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryName));

                    expenseDao.add(new Expense(expenseName, category, expensePrice));
                    break;

                case REMOVE:
                    LOG.info("Remove expense...");

                    if (command.getParams().size() != 1) {
                        throw new IllegalArgumentException("This command support only one param(expenseName)");
                    }

                    expenseName = command.getParams().get(0);
                    expenseDao.remove(expenseName);
                    break;

                case UPDATE:

                    LOG.info("Update expense...");

                    if(command.getParams().size() != 2) {
                        throw new IllegalArgumentException("This command support only two params(currentName, newName)");
                    }

                    String currentName = command.getParams().get(0);
                    String newName = command.getParams().get(1);
                    expenseDao.update(currentName, newName);
                    break;
            }

        } else {
            throw new NullPointerException("action cannot be empty");
        }
    }

}
