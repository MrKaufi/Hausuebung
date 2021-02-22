/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eaustria;

/**
 *
 * @author bmayr
 */
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Class wrapping methods for implementing reciprocal array sum in parallel.
 */
public final class ReciprocalArraySum {

    public static void main(String[] args) {
        double[] input = new double[50000];
        for (int i = 0; i < 50000; i++) {
            input[i] = i+1;
        }
        System.out.println(parManyTaskArraySum(input, 2));
        System.out.println(seqArraySum(input));
    }

    /**
     * Default constructor.
     */
    private double ReciprocalArraySum() {
        return 0;
//        ForkJoinPool fjp = new ForkJoinPool();
//        return fjp.execute(new ReciprocalArraySumTask(input));
    }

    /**
     * Sequentially compute the sum of the reciprocal values for a given array.
     *
     * @param input Input array
     * @return The sum of the reciprocals of the array input
     */
    protected static double seqArraySum(final double[] input) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += 1 / input[i];
        }
        return sum;
        // ToDo: Compute sum of reciprocals of array elements

    }

    /**
     * This class stub can be filled in to implement the body of each task
     * created to perform reciprocal array sum in parallel.
     */
    private static class ReciprocalArraySumTask extends RecursiveTask<Double> {

        /**
         * Starting index for traversal done by this task.
         */
        private final int startIndexInclusive;
        /**
         * Ending index for traversal done by this task.
         */
        private final int endIndexExclusive;
        /**
         * Input array to reciprocal sum.
         */
        private final double[] input;
        /**
         * Intermediate value produced by this task.
         */
        private double value;

        private static int SEQUENTIAL_THRESHOLD = 50000;

        /**
         * Constructor.
         *
         * @param setStartIndexInclusive Set the starting index to begin
         * parallel traversal at.
         * @param setEndIndexExclusive Set ending index for parallel traversal.
         * @param setInput Input values
         */
        ReciprocalArraySumTask(final int setStartIndexInclusive, final int setEndIndexExclusive, final double[] setInput) {
            this.startIndexInclusive = setStartIndexInclusive;
            this.endIndexExclusive = setEndIndexExclusive;
            this.input = setInput;
        }

        /**
         * Getter for the value produced by this task.
         *
         * @return Value produced by this task
         */
        public double getValue() {
            return value;
        }

        @Override
        protected Double compute() {
            if (input.length <= SEQUENTIAL_THRESHOLD) {

                value = seqArraySum(input);
                return value;
            } else {
                System.out.println("Creating 2 Tasks:");
                int mid = (startIndexInclusive + endIndexExclusive) / 2;
                ReciprocalArraySumTask rAST1 = new ReciprocalArraySumTask(startIndexInclusive, mid +1 , input);
                ReciprocalArraySumTask rAST2 = new ReciprocalArraySumTask(mid+1 , endIndexExclusive, input);
                
                invokeAll(rAST1, rAST2);
                
                return rAST1.join() + rAST2.join();
            }

            // TODO: Implement Thread forking on Threshold value. (If size of
            // array smaller than threshold: compute sequentially else, fork 
            // 2 new threads
        }
    }

    /**
     * TODO: Extend the work you did to implement parArraySum to use a set
     * number of tasks to compute the reciprocal array sum.
     *
     * @param input Input array
     * @param numTasks The number of tasks to create
     * @return The sum of the reciprocals of the array input
     */
    protected static double parManyTaskArraySum(final double[] input, final int numTasks) {
        double sum = 0;
        ForkJoinPool pool = new ForkJoinPool();
        ArrayList<ReciprocalArraySumTask> tasks = new ArrayList<>();
        for (int i = 0; i < numTasks; i++) {
            tasks.add(new ReciprocalArraySumTask(0, input.length, input));
            pool.invoke(tasks.get(i));
            sum = tasks.get(i).getValue();
        }

        return sum;
        // ToDo: Start Calculation with help of ForkJoinPool

    }

}
