import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Scanner;

class InteractivePercolationVisualizer {

    public static void main(String[] args) {
        int order;    //order of the matrix      
        System.out.print("Enter the order of matrix: ");
        Scanner scan = new Scanner(System.in);
        order = scan.nextInt();
        scan.close();

        // repeatedly open site specified my mouse click and draw resulting system
        StdDraw.enableDoubleBuffering();
        Percolation perc = new Percolation(order);
        PercolationVisualizer.draw(perc, order);
        StdDraw.show();

        while (true) {
            // detected mouse click
            if (StdDraw.isMousePressed()) {

                // screen coordinates
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                // convert to row i, column j
                int i = (int) (order - Math.floor(y));
                int j = (int) (1 + Math.floor(x));

                // open site (i, j) provided it's in bounds
                if (i >= 1 && i <= order && j >= 1 && j <= order) {
                    if (!perc.isOpen(i, j)) { 
                        StdOut.println(i + " " + j);
                    }
                    perc.open(i, j);
                }
                // draw n-by-n percolation system
                PercolationVisualizer.draw(perc, order);
                StdDraw.show();
            }
            StdDraw.pause(20);
        }
    }
}