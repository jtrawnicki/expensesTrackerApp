package pl.jtrawnicki.input;

import java.util.Scanner;

public class UserInputManager {

    private Scanner scanner;

    public UserInputManager() {
        this.scanner = new Scanner(System.in);
    }


    public UserInputCommand nextCommand() {
        return new UserInputCommand(scanner.nextLine());
    }
}
