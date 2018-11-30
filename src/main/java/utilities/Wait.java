package utilities;


/**
 * Created by kristian.g.maglasang on 8/2/2017.
 */
public class Wait {

    /**
     * This method will wait x seconds before the next lines of code can run
     *
     * DO NOT ABUSE THIS METHOD. Do not use it for waiting on an element
     * to appear, to be clickable, etc. You should still use WebDriver's explicitwait.
     * @param timeInSeconds The amount of time you want to wait
     */
    public static void waitFor(int timeInSeconds) {
        long time1, time2;
        time1 = System.currentTimeMillis();

        do {
            time2 = System.currentTimeMillis();
        } while ((time2 - time1) < timeInSeconds * 1000);
    }
}
