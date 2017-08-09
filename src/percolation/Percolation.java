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
	   
	   if(row -1 == 0){
		   uf.union(VALUE, 0);
	       grid [row-1][col-1] = FULL;
	   }
	   if(row + 1 == n){
		   uf.union(VALUE, n*n);
		   grid [row-1][col-1] = FULL;
	   }
	    
	   if(LEFT > 0 &&  (col - 1) > 0 && grid [row - 1][col - 2] >0){
		   uf.union(VALUE, LEFT);

	   }
	   if(RIGHT < (n * n) && col <  n && grid [row - 1][col] >0){
		   uf.union(VALUE, RIGHT);

	   }
	   if(UP > 0 && (row - 1) > 0 && grid [row - 2][col - 1] >0){
		   uf.union(VALUE, UP);

	   }

	   if(DOWN < (n * n) && (row + 1) < n && grid [row + 2][col - 1] >0){
		   uf.union(VALUE, DOWN);

	   }   
	   

	   for(int y=0; y < n;  y++){
		   for(int x=0; x < n; x++){
			   if(xyTo1D(y, x +1) >= 0 && y - 1 > 0 && uf.connected(xyTo1D(y + 1, x + 1), xyTo1D(y, x +1)) && grid [y - 1][x] == FULL &&  grid [y][x] == OPEN)
				   grid [y][x] = FULL;
			   if(xyTo1D(y + 2, x) < n * n && y + 1  < n && uf.connected(xyTo1D(y + 1, x + 1), xyTo1D(y + 2, x)) && grid [y + 1][x] == FULL &&  grid [y][x] == OPEN )
				   grid [y][x] = FULL;
			   if(xyTo1D(y + 1, x + 2) < n * n && x + 1 < n && uf.connected(xyTo1D(y + 1, x + 1), xyTo1D(y + 1, x + 2)) && grid [y][x + 1] == FULL &&  grid [y][x] == OPEN)
				   grid [y][x] = FULL;
			   if(xyTo1D(y + 1, x) >= 0 && x - 1 > 0 && uf.connected(xyTo1D(y + 1 , x + 1), xyTo1D(y + 1, x)) && grid [y][x - 1] == FULL &&  grid [y][x] == OPEN)
				   grid [y][x] = FULL;
		   }
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
	   return ((col-1) + (row-1) * n);
   }
   
   public static void main(String[] args) {
	   
   }  // test client (optional)
}
