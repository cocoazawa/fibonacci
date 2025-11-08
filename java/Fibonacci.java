// Fibonacci.java
// Copyright (C) 2025 cocoazawa

class Fibonacci {
    private static void Help() {
        System.out.println("");
        System.out.println("fibonacci");
        System.out.println("    a CLI benchmarking tool using the fibonacci sequence");
        System.out.println("");
        System.out.println("Example Usage:");
        System.out.println("    Help Page");
        System.out.println("        fibonacci -H");
        System.out.println("        fibonacci --help");
        System.out.println("    Running Fibonacci passes");
        System.out.println("        fibonacci -P 10");
        System.out.println("        fibonacci -P 47");
        System.out.println("    Running Fibonacci while logging the values");
        System.out.println("        fibonacci -P 10 -L");
        System.out.println("        fibonacci -P 47 -L");
        System.out.println("");
        System.out.println("Benchmarking Prints:");
        System.out.println("    Finished [command with number of passes]");
        System.out.println("    Elapsed Time [elapsed time for run in milliseconds]");
        System.out.println("    Language [language in full name]");
    }

    private static int[] Run(int passes, boolean logging) {
        int[] newFibonacciArray = new int[passes];
        int furtherPrevious = 0;
        int previous = 1;

        for (int pass = 0; pass < passes; pass++) {
            // use switch for slightly faster iteration
            switch (pass) {
                case 0 -> {
                    if (logging) {System.out.println(0);}
                    furtherPrevious = 0;
                    newFibonacciArray[pass] = 0;
                }
                case 1 -> {
                    if (logging) {System.out.println(1);}
                    previous = 1;
                    newFibonacciArray[pass] = 1;
                }
                default -> {
                    newFibonacciArray[pass] = furtherPrevious + previous;
                    furtherPrevious = previous;
                    previous = newFibonacciArray[pass];
                    if (logging) {System.out.println(newFibonacciArray[pass]);}
                }
            }
        }

        return newFibonacciArray;
    }

    public static void main(String[] args) {
        System.out.println("Running Fibonacci (Java) based on given arguments...");

        if (args.length <= 0) {
            System.out.println("No arguments given. Loading Help page...");
            Help();
            System.exit(0);
        }
        
        int runUntil = 0;
        boolean log = false;

        try {
            for (int argsLookupIndex = 0; argsLookupIndex < args.length; argsLookupIndex++) {
                if (args[argsLookupIndex].equals("-H") || args[argsLookupIndex].equals("--help") || args[argsLookupIndex].equals("-h") || args[argsLookupIndex].equals("-help")) {
                    Help();
                    System.exit(0);
                }

                if (args[argsLookupIndex].equals("-P")) {
                    String runUntilString = args[argsLookupIndex + 1];
                    runUntil = Integer.parseInt(runUntilString);
                    continue;
                }

                if (args[argsLookupIndex].equals("-L")) {
                    log = true;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Unexpected argument type. Exiting...");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Argument expected but non-existent. Exiting...");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("An error occurred that wasn't expected. Exiting...");
            System.exit(1);
        }

        if (runUntil > 47) {
            System.out.println("An error will occur after the 46th Fibonacci Sequence number due to the limitations of the `int` type in Java.");
            System.out.println("Java Language Specifications");
            System.out.println("    https://docs.oracle.com/javase/specs/jls/se7/html/jls-4.html#:~:text=For%20int%2C%20from%20-2147483648%20to%202147483647%2C%20inclusive");
            System.out.println("");
            System.out.println("Exiting...");
            System.exit(1);
        }

        long startTime = System.nanoTime();
        Run(runUntil, log);
        long elapsedTimeNanoseconds = System.nanoTime() - startTime;
        int elapsedTime = (int) Math.round(elapsedTimeNanoseconds * 0.000001);
        System.out.println("Finished     - Fibonacci " + runUntil + " passes");
        System.out.println("Time Elapsed - " + elapsedTime + " milliseconds");
        System.out.println("Time Elapsed - " + elapsedTimeNanoseconds + " nanoseconds");
        System.out.println("Language     - Java");
        System.exit(0);
    }
}

