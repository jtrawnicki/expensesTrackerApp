package pl.jtrawnicki.input;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCommandTest {

    @Test
    void shouldBuildCorrectUserCommand() {
        //given
        String input = "command action param1 param2";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals("action", userInputCommand.getAction());
        assertLinesMatch(List.of("param1", "param2"), userInputCommand.getParams());

    }

    @Test
    void shouldBuildCorrectUserCommandWithoutParams() {
        //given
        String input = "command action";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals("action", userInputCommand.getAction());
        assertLinesMatch(List.of(), userInputCommand.getParams());

    }

    @Test
    void shouldBuildCorrectUserCommandWithThreeParams() {
        //given
        String input = "command action param1 param2 param3";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals("action", userInputCommand.getAction());
        assertLinesMatch(List.of("param1", "param2", "param3"), userInputCommand.getParams());

    }
}