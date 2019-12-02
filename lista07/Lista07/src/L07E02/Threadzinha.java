package L07E02;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Threadzinha extends Thread{
    
    int n;
    public Threadzinha(int n) {
        this.n = n;
    }
    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            System.out.println(getName() + " - " + i);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Threadzinha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
