package hw2;

import java.util.Random;

public class PercolationStats {
    private double[] x;
    private final int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        } else {
            x = new double[N];
            this.T = T;
            Random rand = new Random();
            for(int i=0; i<T; i++) {
                Percolation percSystem = pf.make(N);
                while(!percSystem.percolates()) {
                    int row, col;
                    do {
                        row = rand.nextInt(N);
                        col = rand.nextInt(N);
                    } while(percSystem.isOpen(row, col));
                    percSystem.open(row, col);
                }
                x[i] = percSystem.numberOfOpenSites() * 1.0 / (N * N);
            }
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        double sum = 0.0;
        for(double val : x) {
            sum += val;
        }
        return sum / T;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double sum = 0.0;
        double mean = this.mean();
        for(double val : x) {
            sum += Math.pow(val - mean, 2.0);
        }
        return Math.sqrt(sum / (T - 1));
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow()  {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(T);
    }
}
