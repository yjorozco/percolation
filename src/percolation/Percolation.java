package percolation;

public class Percolation {
	
	private int[][] grid = null;
	private QuickUnionUF uf = null;
	private int counter = 0;
	private int n = 0;
   public Percolation(int n) {
	   this.n = n;
	   uf = new QuickUnionUF(n);
	   for(int i=0; i < n;  i++){
		   for(int y=0; y < n; y++){
			   grid [i][y] = 0;   
		   }
	   }
	   
   }               // create n-by-n grid, with all sites blocked
   public void open(int row, int col) {
	   if(!isOpen(row, col)){
		   grid [row][col] = 1;  
		   uf.union(col, row);
		   counter++;
	   }
   }    // open site (row, col) if it is not open already
   public boolean isOpen(int row, int col) {	   
	   return uf.connected(row, col);
   }  // is site (row, col) open?
   public boolean isFull(int row, int col){
	   return true;
	   
   }  // is site (row, col) full?
   public int numberOfOpenSites() {
	   return counter;
   }       // number of open sites
   public boolean percolates() {
	   
	   return (counter >= n);
   }             // does the system percolate?

   public static void main(String[] args) {
	   
   }  // test client (optional)
}

