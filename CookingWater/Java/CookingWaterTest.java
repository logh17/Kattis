package Java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookingWaterTest {

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
                        "2\n1 7\n5 5\n",
                        "gunilla has a point"
                ),
                new TestCase(
                        "Sample Test 2",
                        "3\n4 6\n4 8\n7 8\n",
                        "edward is right"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testCookingWater(TestCase testCase) throws IOException {
        assertEquals(testCase.expectedOutput.trim(), runWithInput(testCase.input).trim());
    }

    // Helper method to simulate program execution with given input
    private String runWithInput(String input) {
        // Set up input stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Set up output capture
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Execute main method
        CookingWater.main(new String[]{});

        // Return captured output (trimmed to remove any extra whitespace)
        return outputStream.toString().trim();
    }
}