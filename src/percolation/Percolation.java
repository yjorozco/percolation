package percolation;

public class Percolation {
	
   private int[][] grid = null;
   private QuickUnionUF uf = null;
   private int counter = 0;
   private int n = 0;
   public static final int OPEN = 1;
   public static final int BLOCK = 0;
   
   public Percolation(int n) {
	   this.n = n;

	   grid = new int[n][n];
	   uf = new QuickUnionUF(n * n + 2);
	   for(int y=0; y < n;  y++){
		   for(int x=0; x < n; x++){
			   grid [y][x] = BLOCK;   
		   }
	   }
	   
   }
   
   public void open(int row, int col) {		   

	   final int LEFT = this.xyTo1D(row, col - 1);
	   final int RIGHT = this.xyTo1D(row, col + 1);
	   final int UP = this.xyTo1D(row - 1, col);
	   final int DOWN = this.xyTo1D(row + 1, col);
	   final int VALUE = xyTo1D(row, col);

	   if(grid [row-1][col-1] == BLOCK){
		   grid [row-1][col-1] = OPEN;  
		   counter++;
	   }
	   
	   if(row - 1 == 0){
		   uf.union(VALUE, 0);
	   }	   
	   
	   if(LEFT > 0 &&  (col - 1) > 0 && grid [row - 1][col - 2] > BLOCK){
		   uf.union(VALUE, LEFT);
		   
	   }
	   if(RIGHT < (n * n) && col <  n && grid [row - 1][col] > BLOCK){
		   uf.union(VALUE, RIGHT);
		   
	   }
	   if(UP > 0 && (row - 1) > 0 && grid [row - 2][col - 1] > BLOCK){
		   uf.union(VALUE, UP);

	   }

	   if(DOWN < (n * n) && row < n && grid [row][col - 1] > BLOCK){
		   uf.union(VALUE, DOWN);

	   }   

   }
   
   public boolean isOpen(int row, int col) {
	   if(grid [row-1][col-1] == OPEN)
		   return true;
	   return false;
   }   

   public boolean isFull(int row, int col){		   
      if(grid [row-1][col-1] == OPEN && uf.connected(0, xyTo1D(row, col))){
		   if(row - 1== (n - 1)){
			   uf.union(xyTo1D(row, col), (n * n - 1));
		   } 
		   return true;	
      }
	   return false;	   
   }
   
   public int numberOfOpenSites() {
	   return counter;
   }
   
   public boolean percolates() {	  
	   if(uf.connected(0, (n * n - 1))){
		   return true;
	   }
	   return false;
   }  
   private int xyTo1D(int row, int col){
	   return ((col - 1) + (row - 1) * n);
   }
   
  
   public static void main(String[] args) {
	   
   }  // test client (optional)
}
