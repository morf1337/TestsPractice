package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    private NumberWorker nw;

    @BeforeEach
    private void initializer() {
        nw = new NumberWorker();
    }

    @ParameterizedTest(name = "{index} - {0} is a Prime number")
    @ValueSource(ints = {5, 7, 11, 13, 17, 19, 23, 31, 41, 47})
    public void isPrimeForPrimes(int number) {
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest(name = "{index} - {0} is NOT a Prime number")
    @ValueSource(ints = {4, 8, 12, 16, 20, 25, 30, 40, 45, 50})
    public void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest(name = "{index} - {0} throws exception for incorrect numbers")
    @ValueSource(ints = {-5, -10, -15, 1, -2, 0, -1, 0, -3, 1})
    public void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> {
            nw.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitsSumTestCases(int input, int output) {
        Assertions.assertEquals(nw.digitsSum(input), output);
    }
}
