public class Main {
    public static void main(String args[]){

        Thread texteditor = new Thread(new Runnable() {
            @Override
            public void run() {
                Design design = new Design();
                design.setSize(800,610);
            }
        });
        texteditor.start();




    }
}
