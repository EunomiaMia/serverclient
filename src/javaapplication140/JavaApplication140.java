
package javaapplication140;
public class JavaApplication140 {
    public static void main(String[] args) {
        Thread ts = new Thread(new Server());
        Thread tc = new Thread(new Klijent());

        ts.start();
        tc.start();
    }
    
}
