import javax.swing.JFrame;


public class Main {
    public static void main(String args[]){

        Design design = new Design();
        design.setVisible(true);
        design.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        design.setBounds(100,100,0,0);
        design.setSize(800,600);
    }
}
