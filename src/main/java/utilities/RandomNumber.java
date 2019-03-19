package utilities;


import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * Created by kristian.g.maglasang on 3/10/2017.
 */
public class RandomNumber {

    public static int generateRandomNumber(List<WebElement> elementArray) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(elementArray.size());
    }

    public static int generateRandomNumber(String[] elementArray) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(elementArray.length);
    }
}
