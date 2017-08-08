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
	   System.out.println(row + " " + col);
	   final int LEFT = (this.xyTo1D(row, col) - 1);
	   final int RIGHT = (this.xyTo1D(row, col) + 1);
	   final int UP = (this.xyTo1D(row, col) - n);
	   final int DOWN = (this.xyTo1D(row, col) + n);
	   final int VALUE = xyTo1D(row, col);
	   System.out.println(VALUE);
	   if(!isOpen(row, col)&&!isFull(row, col)){
		   grid [row][col] = 1;  
		   counter++;
	   }
	    
	   if(LEFT > 0 && !isOpen(row, col) && !uf.connected(VALUE, LEFT)){
		   uf.union(VALUE, LEFT);
	   }
	   if(RIGHT < n  && !isOpen(row, col) && !uf.connected(VALUE, RIGHT)){
		   uf.union(VALUE, RIGHT);
	   }
	   if(UP > 0 && !isOpen(row, col) && !uf.connected(VALUE, UP)){
		   uf.union(VALUE, UP);
	   }
	   if(DOWN < n  && !isOpen(row, col) && !uf.connected(VALUE, DOWN)){
		   uf.union(VALUE, DOWN);
	   }	   

	   int top = 0;
	   
	   while(top < n-1){		   
		   if(uf.connected( top , VALUE))			   
			   grid [row][col] = 2;
		   top++;
	   }
	   

	   
   }
   
   public boolean isOpen(int row, int col) {
	   if(grid [row][col] == OPEN)
		   return true;
	   return false;
   }
   
   public boolean isFull(int row, int col){	  
	   if(grid [row][col] == FULL)
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

