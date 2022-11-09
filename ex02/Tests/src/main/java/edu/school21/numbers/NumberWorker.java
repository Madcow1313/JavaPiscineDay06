package edu.school21.numbers;

public class NumberWorker {
    public NumberWorker(){}

    public boolean isPrime(int number){
        if (number < 2){
            throw new IllegalNumberException("Invalid number");
        }
        if (number != 2 && number != 3) {
            if (number % 2 == 0) {
                return false;
            } else {
                for (long i = 3; i * i <= number; i += 2) {
                    if (number % i == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int digitsSum(int number){
        int sum = 0;
        int remainder;
        while (number > 0){
            remainder = number % 10;
            sum += remainder;
            number = number / 10;
        }
        return sum;
    }

    class IllegalNumberException extends IllegalArgumentException{
        public IllegalNumberException(String message){
            super(message);
        }
    }


}
