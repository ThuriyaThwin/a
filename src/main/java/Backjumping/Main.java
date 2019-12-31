package Backjumping;


import Backjumping.csp.Problem;
import Backjumping.prosser.CBJ;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), 20, TimeUnit.MINUTES);// Program Timer
        double start = System.currentTimeMillis();

        CBJ a = new CBJ(new Problem(4));
        a.bcssp();

        double end = System.currentTimeMillis();
        System.out.println("Time to solve in second       = " + (end - start) * 0.001 + " s");
        a.printV(new PrintStream("a.txt"));

    }
}

