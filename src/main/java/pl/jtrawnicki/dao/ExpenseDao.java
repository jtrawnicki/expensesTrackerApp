package pl.jtrawnicki.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jtrawnicki.model.Category;
import pl.jtrawnicki.model.Expense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpenseDao {

    Logger LOG = Logger.getLogger(ExpenseDao.class.getName());

    private ObjectMapper objectMapper;

    public ExpenseDao() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Expense> findAllExpenses() {

        return getExpenses();

    }

    private List<Expense> getExpenses() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./expenses.txt")), new TypeReference<>() {
            });

        } catch (IOException e) {
            LOG.log(Level.WARNING, "error on getExpenses", e);
            return new ArrayList<>();
        }
    }

    public void add(Expense expense) {

        List<Expense> expenses = getExpenses();
        expenses.add(expense);

        saveExpense(expenses);

    }

    private void saveExpense(List<Expense> expenses) {
        try {
            Files.writeString(Paths.get("./expenses.txt"), objectMapper.writeValueAsString(expenses));
        } catch (IOException e) {
            LOG.log(Level.WARNING, "error on saveExpense", e);
        }
    }

    public void remove(String expenseName) {

        List<Expense> expenses = getExpenses();

        expenses.removeIf(expense -> expense.getName().equals(expenseName));


        saveExpense(expenses);
    }

    public void update(String currentValue, String newValue) {


        List<Expense> expenses = getExpenses();

        Optional<String> expenseNewValue = Optional.empty();

        for (Expense expense : expenses) {
            if (expense.getName().equals(currentValue)) {
                expenseNewValue = Optional.of(newValue);
                expense.setName(expenseNewValue.get());
            }
        }

        if (expenseNewValue.isEmpty()) {
            throw new NullPointerException("expense not found");
        }

        saveExpense(expenses);
    }
}

