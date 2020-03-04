import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class PercolationStats{
	double[] fraction;
	double numOfMean;
	double numOfStddev;
	int T;
	public PercolationStats(int n,int trials){
		validate(n,trials);
		fraction = new double[trials];
		T = trials;
		int row,col;
		
		for(int i=0;i<trials;++i){
			Percolation p = new Percolation(n);
			while(!(p.percolates())){
				row = StdRandom.uniform(n) + 1; //generates a random number within range n+1
				col = StdRandom.uniform(n) + 1;
				p.open(row,col);
			}
			fraction[i] = (p.numberOfOpenSites()*1.0)/(n*n); /*fraction of open sites 
			in computational experiment i*/
		}
		
	}
	private void validate(int n, int trials) {
		if (n <= 0 || trials<= 0) {
			throw new java.lang.IllegalArgumentException("IllegalArgument");
		}
	}
	public double mean(){
		numOfMean = StdStats.mean(fraction);
		return numOfMean;
	}
	public double stddev(){
		numOfStddev = StdStats.stddev(fraction);
		return numOfStddev;
	}
	public double confidenceLo(){
		return numOfMean - (1.96*numOfStddev)/Math.sqrt(T);
	}

	public double confidenceHi(){
		return numOfMean + (1.96*numOfStddev)/Math.sqrt(T);
	}
 
	public static void main(String[] args) {
		int n = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats perStats = new PercolationStats(n, T);
		StdOut.printf("mean                    = %.8f\n", perStats.mean());
		StdOut.printf("stddev                  = %.8f\n", perStats.stddev());
		StdOut.printf("95%% confidence interval = [%.16f, %.16f]", perStats.confidenceLo(),perStats.confidenceHi());
	}
}