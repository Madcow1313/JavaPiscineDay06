package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    NumberWorker numberWorker;

    @BeforeEach
    void initEach(){
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,5,7,Integer.MAX_VALUE})
    public void isPrimeForPrimes(int number){
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {8,4,9,27})
    public void isPrimeForNotPrimes(int number){
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -11000})
    public void isPrimeForIncorrectNumbers(int number){
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    public void checkDigitSum(int inputNumber, int sum){
        Assertions.assertEquals(sum, numberWorker.digitsSum(inputNumber));

    }
}
