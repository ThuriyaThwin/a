package test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHHelloWorld {

    @Benchmark
    public void helloWorld() {
        // a dummy method to check the overhead
    }

    /*
     * It is better to run the benchmark from command-line instead of IDE.
     *
     * To run, in command-line: $ mvn clean install exec:exec
     */

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMHHelloWorld.class.getSimpleName()).forks(1).warmupForks(1).measurementIterations(1).build();

        new Runner(options).run();
    }
}