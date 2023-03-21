package pl.jtrawnicki.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jtrawnicki.model.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao {

    Logger LOG = Logger.getLogger(CategoryDao.class.getName());

    private ObjectMapper objectMapper;


    public CategoryDao() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Category> findAll() {

        return getCategories();

    }

    private List<Category> getCategories() {
        try {

            return objectMapper.readValue(Files.readString(Paths.get("./categories.txt")), new TypeReference<>() {
            });

        } catch (IOException e) {
            LOG.log(Level.WARNING, "error on getCategories", e);
            return new ArrayList<>();
        }
    }

    public void add(Category category) {


            List<Category> categories = getCategories();
            categories.add(category);

        saveCategories(categories);
    }

    private void saveCategories(List<Category> categories) {
        try {

            Files.writeString(Paths.get("./categories.txt"), objectMapper.writeValueAsString(categories));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "error on saveCategories", e);
        }
    }

    public Optional<Category> findOne(String categoryName) {
        return getCategories().stream()
                .filter(c -> c.getName().equals(categoryName))
                .findAny();
    }

    public void remove(String categoryName) {

        List<Category> categories = getCategories();

        categories.removeIf(category -> category.getName().equals(categoryName));

        saveCategories(categories);

    }

    public void update(String currentName, String newName) {
        List<Category> categories = getCategories();

        Optional<String> categoryNewName = Optional.empty();

        for (Category category : categories) {
            if (category.getName().equals(currentName)) {
                categoryNewName = Optional.of(newName);
                category.setName(categoryNewName.get());
            }
        }

        if (categoryNewName.isEmpty()) {
            throw new NullPointerException("category not found");
        }

        saveCategories(categories);
    }
}