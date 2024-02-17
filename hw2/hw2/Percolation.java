package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.HashSet;

public class Percolation {
    private WeightedQuickUnionUF SITES_CONNECTED_TO_TOP; // To take care of backwash for isFull
    private WeightedQuickUnionUF CONNECTIONS; // To check for percolation
    private int N;
    private HashSet<Integer> OPEN_SITES = new HashSet<>();
    private int NO_OF_OPEN_SITES;
    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOTTOM;
    private int xyTo1D(int i, int j) {
        return N * i + j;
    }

    private void isValidIndex(int i, int j) {
        if( (i < 0) || (i >= N) && (j < 0) && (j >= N) ){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private ArrayList<Integer> getOpenNeighbors(int site) {
        ArrayList<Integer> openNeighbors = new ArrayList<>();
        int[] dirs = {1, -1, N, -N};
        for(int dir: dirs) {
            int neighbor = site + dir;
            if(neighbor >= 0 && neighbor < N * N && OPEN_SITES.contains(neighbor)) {
                openNeighbors.add(neighbor);
            }
        }
        return openNeighbors;
    }
    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if(N <= 0) {
            throw new java.lang.IllegalArgumentException("grid size must be a positive integer");
        }
        SITES_CONNECTED_TO_TOP = new WeightedQuickUnionUF(N * N + 1);
        CONNECTIONS = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        VIRTUAL_TOP = N * N;
        VIRTUAL_BOTTOM = N * N + 1;
        NO_OF_OPEN_SITES = 0;

        for(int i = 0; i < N; i++) {
            CONNECTIONS.union(i, VIRTUAL_TOP);
            SITES_CONNECTED_TO_TOP.union(i, VIRTUAL_TOP);
        }

        for(int i = N * N - 1; i >= N * N - N; i--) {
            CONNECTIONS.union(i, VIRTUAL_BOTTOM);
        }
    }
    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        isValidIndex(row, col);

        if(!isOpen(row, col)) {
            int site = xyTo1D(row, col);
            OPEN_SITES.add(site);
            NO_OF_OPEN_SITES += 1;
            ArrayList<Integer> openNeighbors = getOpenNeighbors(site);
            for(int openNeighbor : openNeighbors) {
                if(!CONNECTIONS.connected(openNeighbor, site)) {
                    CONNECTIONS.union(site, openNeighbor);
                }
                if(!SITES_CONNECTED_TO_TOP.connected(openNeighbor, site)) {
                    SITES_CONNECTED_TO_TOP.union(site, openNeighbor);
                }
            }
        }
    }
    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        isValidIndex(row, col);

        int site = xyTo1D(row, col);
        return OPEN_SITES.contains(site);
    }
    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        isValidIndex(row, col);

        int site = xyTo1D(row, col);
        return isOpen(row, col) && SITES_CONNECTED_TO_TOP.connected(site, VIRTUAL_TOP);
    }
    public int numberOfOpenSites() {
        // number of open sites
        return NO_OF_OPEN_SITES;
    }
    public boolean percolates() {
        // does the system percolate?
        return CONNECTIONS.connected(VIRTUAL_TOP, VIRTUAL_BOTTOM);
    }
    public static void main(String[] args) {
        // use for unit testing (not required)
    }
}
