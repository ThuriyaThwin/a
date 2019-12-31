package test;

import engine.algo.FlexibleBacktrackingSolver;
import engine.csp.Assignment;
import engine.csp.CSP;
import engine.csp.CspListener;
import engine.csp.Variable;
import engine.csp.inference.AC3Strategy;
import engine.csp.inference.ForwardCheckingStrategy;
import model.nqueen.NQueensCSP;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Scanner;

public class ConsoleApp {

       public static void main(String args[])throws Exception{
        Options options = new OptionsBuilder()
                .include(ConsoleApp.class.getSimpleName()).forks(1).warmupForks(1).measurementIterations(1).build();

        new Runner(options).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public static void test()
    {
        Scanner sc = new Scanner(System.in);

        NQueensCSP csp=new NQueensCSP(4);
        // Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), 20, TimeUnit.MINUTES);// Program Timer
        //Runtime runtime = Runtime.getRuntime();//for memory
        double start = System.currentTimeMillis();
        FlexibleBacktrackingSolver bts=new FlexibleBacktrackingSolver();
        bts.set(new AC3Strategy()).set(new ForwardCheckingStrategy());
        bts.addCspListener(new CspListener() {
            public void stateChanged(CSP csp, Assignment assignment, Variable variable) {
                System.out.println("Assignment Evolved :"+assignment);
            }
        });

        bts.solve(csp);
        double end = System.currentTimeMillis();
        //System.out.println("\nThe solution is     = " + sol.get());
        System.out.println("\nThe number of solutions is   = "+bts.getNumberOfSolution());
        System.out.println("\nAlgorithm Evaluation process");
        System.out.println("................................\n");
        System.out.println("Time to solve in second       = " + (end - start) * 0.001 + " s");
        System.out.println("Number of backtracking occurs = " + bts.getNumberOfBacktrack() + " times");
        System.out.println("Number of nodes visited       = " + bts.getNumberOfNodesVisited() + " nodes");
        System.out.println("Number of nodes assigned      = " + bts.getNumberOfNodesAssigned() + " nodes");
        //   System.out.println("Used Memory                   = " + ((runtime.totalMemory() - runtime.freeMemory())) / (double) 1000000 + " mb");
    }
}
