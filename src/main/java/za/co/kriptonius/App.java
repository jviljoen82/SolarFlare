package za.co.kriptonius;

/**
 * Main entry class for SolarFlare
 *
 */
public class App
{

    public static void main(String[] args)
    {
        Thread t1 = new Thread(new SolarFlare());
        t1.start();
    }
}
