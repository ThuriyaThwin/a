package Backjumping.prosser;


import Backjumping.csp.Definitions;
import Backjumping.csp.Problem;
import Backjumping.util.stack.IntStack;

import java.io.PrintStream;

//import IntStack;

/*****************************************************************************************
 *
 * this file is a generalized solver it contains the method bcssp and the variables 
 * that all solvers should contain. In addition it contains definition of abstract 
 * methods label/unlabel that are implemented by explicit classes. 
 * It also contains some csp help methods for printing solution and checking it
 *
 *****************************************************************************************/
public abstract class Bcssp {

    public int assignments = 0;  // how many assignments were made?
    protected boolean consistant; // consistant variable from algorithem
    protected Problem problem;    // the problem to solve
    protected int v[];            // the assigment/solution vector
    protected int n;  // these can be taken from problem but put here to simplify code
    protected int d;

    // Init variables common to all implementations
    public Bcssp(Problem problem) {
        this.problem = problem;
        n = problem.getN();
        d = problem.getD();
        assignments = 0;
    }

    public static int max_list(IntStack list) {
        int result = -1;
        for (int l = 0; l < list.size(); l++) {
            int k = list.peek(l);
            if (k > result)
                result = k;
        }

        return result;
    }

    // should be implemeted by the Algorithm classes
    public abstract int label(int i);

    public abstract int unlabel(int i);

    // run the algorithm main loop
    public Definitions.StatOptions bcssp() {
        Definitions.StatOptions status = Definitions.StatOptions.UNKNOWN;
        consistant = true;
        int i = 0;


        while (status == Definitions.StatOptions.UNKNOWN) {
            if (consistant)
                i = label(i);
            else
                i = unlabel(i);

            if (i >= problem.getN())
                status = Definitions.StatOptions.SOLUTION;
            else if (i == -1)
                status = Definitions.StatOptions.IMPOSSIBLE;
        }

		/*  This is the printing of result as suggested by assignment it is
		 *  commented out since I didn't find any use to it.

		if (status == StatOptions.SOLUTION)
			printV();
		else
			System.out.print("No Solution !!! ");

		System.out.println(",CCs=" + problem.constraint_checks + ",Assigments=" + assignments);
		*/
        System.out.println("Number of nodes visited = " + (assignments + 1));
        return status;
    }

    // that returns the max element of an IntStack
    //(since IntStack didn�t contain such a method and.
    // due to the nature of problem we know that only 0 and
    // positive number are used with this function so we can return -1 if it was empty

    // print the results to a PrintStream (you can use System.out
    public void printV(PrintStream output) {
        output.print("Assignment=");
        for (int i = 0; i < problem.getN(); i++)
            output.print("<" + (i + 1) + "," + (v[i] + 1) + ">,");
    }

}


