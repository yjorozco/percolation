package percolation;

public class Percolation {
	
   private int[][] grid = null;
   private QuickUnionUF uf = null;
   private int counter = 0;
   private int n = 0;
   public static final int OPEN = 1;
   public static final int FULL = 2;
   public static final int BLOCK = 0;
   
   public Percolation(int n) {
	   this.n = n;

	   grid = new int[n+1][n+1];
	   uf = new QuickUnionUF((n+1) * (n+1));
	   for(int y=0; y < n;  y++){
		   for(int x=0; x < n; x++){
			   grid [y][x] = BLOCK;   
		   }
	   }
	   
   }
   
   public void open(int row, int col) {		   

	   final int LEFT = (this.xyTo1D(row, col) - 1);
	   final int RIGHT = (this.xyTo1D(row, col) + 1);
	   final int UP = (this.xyTo1D(row-1, col));
	   final int DOWN = (this.xyTo1D(row+1, col));
	   final int VALUE = xyTo1D(row, col);

	   if(grid [row-1][col-1] == 0 && !isFull(row, col)){
		   grid [row-1][col-1] = OPEN;  
		   counter++;
	   }
	    
	   if(LEFT > 0 && isOpen(row, col) && !uf.connected(LEFT, VALUE)){
		   uf.union(LEFT, VALUE);
	   }
	   if(RIGHT < n  && isOpen(row, col) && !uf.connected(RIGHT, VALUE)){
		   uf.union(RIGHT, VALUE);
	   }
	   if(UP > 0 && isOpen(row, col) && !uf.connected(UP, VALUE)){
		   uf.union( UP, VALUE);
	   }
	   if(DOWN < n  && isOpen(row, col) && !uf.connected(DOWN, VALUE)){
		   uf.union(DOWN, VALUE);
	   }	   

	   int top = 0;
	   
	   while(top < n-1){		   
		   if(isOpen(row, col) && uf.connected( top , VALUE))			   
			   grid [row-1][col-1] = 2;
		   top++;
		   
	   }
	   

	   
   }
   
   public boolean isOpen(int row, int col) {
	   if(grid [row-1][col-1] == OPEN)
		   return true;
	   return false;
   }
   
   public boolean isFull(int row, int col){	  
	   if(grid [row-1][col-1] == FULL)
		   return true;
	   return false;	   
   }
   
   public int numberOfOpenSites() {
	   return counter;
   }
   
   public boolean percolates() {
	  
	   return (counter/(n*n) > 0.5);
   }  
   private int xyTo1D(int row, int col){
	   return (col + row * n);
   }
   
   public static void main(String[] args) {
	   
   }  // test client (optional)
}
