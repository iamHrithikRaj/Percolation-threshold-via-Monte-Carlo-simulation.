import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class Percolation{
	int[][] id;
	boolean[][] status;
	WeightedQuickUnionUF uf;
	int virtual_top;
	int virtual_bottom;
	final int MAX;
	int openSites;

	public Percolation(int n){
		if (n <= 0) throw new java.lang.IllegalArgumentException("IllegalArgument");
		MAX = n;
		openSites = 0;
		uf = new WeightedQuickUnionUF(MAX*MAX + 2); //initialises a 1D array of args length
		id = new int[MAX+1][MAX+1]; /* MAX+1 because array 
		index starts from 0 therefore to incerease the limit*/
		status = new boolean[MAX+1][MAX+1];
		int k =0;
		for(int i = 1; i <= MAX; i++){ 
			for(int j = 1; j <= MAX; j++){ /*By convention, the row and column 
				 are integers between 1 and n, where (1, 1) is the upper-left site*/
				id[i][j] = k;
				k++;
				status[i][j] = false; // false = site blocked ; true = site open
			}
		}
		virtual_top = MAX*MAX; 
		virtual_bottom = MAX*MAX + 1;
	}
	public void open(int row, int col){
		checkRange(row, col);
		if(isOpen(row,col)); //already open
		else{
			status[row][col] = true;
			++openSites;
			if(row != 1 && isOpen(row-1,col))
				connect(id[row-1][col],id[row][col]);	

			if(row == 1)
				connect(id[row][col],virtual_top);

			if(row != MAX && isOpen(row+1,col))
				connect(id[row+1][col],id[row][col]);
			
			if(row == MAX)
				connect(id[row][col],virtual_bottom);
			
			if(col != 1 && isOpen(row,col-1))
				connect(id[row][col-1],id[row][col]);
			
			if(col != MAX && isOpen(row,col+1))
				uf.union(id[row][col+1],id[row][col]);
		}
	}
	public boolean isOpen(int row,int col){
		checkRange(row, col);
		return status[row][col];
	}
	private void connect(int id_1,int id_2){
		/*Return true if args have common parent*/
		if(!(uf.find(id_1) == uf.find(id_2))) 
			uf.union(id_1,id_2);
	}
	public boolean isFull(int row,int col){
		/*is the site is connected to virtual_top?
		(directly or indirectly)*/
		checkRange(row, col);
		int site_id = id[row][col];
		return (uf.find(site_id) == uf.find(virtual_top));
	}
	public int numberOfOpenSites(){
		return openSites;
	}
	public boolean percolates(){  // does the system percolate?
		return uf.find(virtual_bottom) == uf.find(virtual_top);
	}
	private void checkRange(int i, int j){
		if (i <= 0 || j <= 0 || i > MAX || j > MAX)
			throw new IndexOutOfBoundsException();
	}
}