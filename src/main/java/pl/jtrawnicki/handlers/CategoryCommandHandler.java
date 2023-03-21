package pl.jtrawnicki.handlers;

import pl.jtrawnicki.dao.CategoryDao;
import pl.jtrawnicki.input.UserInputCommand;
import pl.jtrawnicki.model.Category;

import java.util.List;
import java.util.logging.Logger;

public class CategoryCommandHandler extends BaseCommandHandler {

    Logger LOG = Logger.getLogger(CategoryCommandHandler.class.getName());

    private CategoryDao categoryDao;

    public CategoryCommandHandler() {
        this.categoryDao = new CategoryDao();
    }

    private static final String COMMAND_NAME = "category";
    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void handle(UserInputCommand command) {

        if (command.getAction() != null) {

            switch (command.getAction()) {

                case LIST:

                    LOG.info("List of categories...");

                    if (!command.getParams().isEmpty()) {
                        throw new IllegalArgumentException("command list doesn't support any params");
                    }

                    List<Category> categories = categoryDao.findAll();
                    categories.forEach(System.out::println);
                    break;

                case ADD:
                    LOG.info("add new category...");

                    if (command.getParams().size() != 1) {
                        throw new IllegalArgumentException("command add category support only one param");
                    }
                    String categoryName = command.getParams().get(0);
                    categoryDao.add(new Category(categoryName));
                    break;

                case REMOVE:
                    LOG.info("remove category...");

                    if (command.getParams().size() != 1) {
                        throw new IllegalArgumentException("command remove support only one param");
                    }

                    categoryName = command.getParams().get(0);
                    categoryDao.remove(categoryName);
                    break;

                case UPDATE:
                    LOG.info("update category...");

                    if (command.getParams().size() != 2) {
                        throw new IllegalArgumentException("command update support two params( currentName, newName)");
                    }

                    String currentName = command.getParams().get(0);
                    String newName = command.getParams().get(1);
                    categoryDao.update(currentName, newName);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown action. Check help for more information");
            }

        } else {
            throw new NullPointerException("action cannot be empty");
        }
    }
}
