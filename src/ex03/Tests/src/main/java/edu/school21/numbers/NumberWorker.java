package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException();
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        if (number < 0) {
            number = Math.abs(number);
        }

        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
