package L07E02;

public class SuperThreadRunner {
    public static void main(String args[]) {
        Threadzinha t1 = new Threadzinha(10);
        Threadzinha t2 = new Threadzinha(20);
        t1.start();
        t2.start();   
    }
    
}
