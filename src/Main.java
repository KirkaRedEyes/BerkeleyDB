import com.sleepycat.je.DatabaseException;

public class Main {
    public static void main(String[] args) {
        Store c = new Store();
        try {
            c.run();
        } catch (DatabaseException dbe) {
            System.err.println("StorePut: " + dbe.toString());
            dbe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            e.printStackTrace();
        }
        System.out.println("All done.");
    }
}
