package Input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputTest {
    Input input;

    @BeforeEach
    void setUp() {
        input = new Input(new Scanner(System.in));
    }

    @Test
    void stringWithWhitespaceToInt() {
        testStringWithWhitespaceToInt("1", 1);
        testStringWithWhitespaceToInt(" 1", 1);
        testStringWithWhitespaceToInt("1 ", 1);
        testStringWithWhitespaceToInt(" 1 ", 1);

        testStringWithWhitespaceToInt("5", 5);
        testStringWithWhitespaceToInt(" 5", 5);
        testStringWithWhitespaceToInt(" 5 ", 5);
        testStringWithWhitespaceToInt("5 ", 5);

        testStringWithWhitespaceToInt("8987431", 8987431);
        testStringWithWhitespaceToInt(" 8987431", 8987431);
        testStringWithWhitespaceToInt(" 8987431 ", 8987431);
        testStringWithWhitespaceToInt("8987431 ", 8987431);
    }

    void testStringWithWhitespaceToInt(String s, int i) {
        assertEquals(input.stringWithWhitespaceToInt(s), i);
    }
}