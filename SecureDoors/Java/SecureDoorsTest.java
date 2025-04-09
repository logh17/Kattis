package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecureDoorsTest {

    // Test case record to hold input and expected output
    record TestCase(String testName, String input, String expectedOutput) {
        @Override
        public String toString() {
            return testName; // Used for test display names
        }
    }

    // Provides test cases for parameterized testing
    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        "Sample Test 1",
                        "8\n" +
                                "entry Abbey\n" +
                                "entry Abbey\n" +
                                "exit Abbey\n" +
                                "entry Tyrone\n" +
                                "exit Mason\n" +
                                "entry Demetra\n" +
                                "exit Latonya\n" +
                                "entry Idella\n",
                        "Abbey entered\n" +
                                "Abbey entered (ANOMALY)\n" +
                                "Abbey exited\n" +
                                "Tyrone entered\n" +
                                "Mason exited (ANOMALY)\n" +
                                "Demetra entered\n" +
                                "Latonya exited (ANOMALY)\n" +
                                "Idella entered\n"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testSecureDoors(TestCase testCase) throws IOException {
        String expected = testCase.expectedOutput.replace("\r\n", "\n").trim();
        String actual = runWithInput(testCase.input).replace("\r\n", "\n").trim();
        assertEquals(expected, actual);
    }

    // Helper method to simulate program execution with given input
    private String runWithInput(String input) throws IOException {
        // Save original system streams
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            // Set up test input/output
            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();

            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            // Execute main method
            SecureDoors.main(new String[]{});

            return testOut.toString();
        } finally {
            // Restore original streams
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}