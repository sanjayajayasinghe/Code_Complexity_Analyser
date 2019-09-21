package test.resources;

public class FibonacciMain {
    public static int fibonacci(long number) {

        switch ((int) number){
            case 1:
                System.out.println("aa");
            case 2:
                System.out.println("aa");
        }

        int x = 0;
        while (x < 1) {
            for(int i = 0;i < 0;i++){


            }
            if (number == 0 || number == 1) {
                //return number;
                if (number == 0 || number == 14) {

                }
                if (number == 0 || number == 14) {

                }

            } else {
                //recursion step
                return fibonacci(number - 1) + fibonacci(number - 2);
            }
        }

        //return number;
        return x;
    }

    public static void main(String[] args) {
        for (int count = 0; count <= 10; count++) {
            System.out.println("Fibonacci of " + count + " is " + fibonacci(count));
        }
    }
}
