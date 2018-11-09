package utilities;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebElement;

public class NetworkRequestUtils extends RemoteWebElement {

    @Override
    public void click() {
        execute(DriverCommand.CLICK_ELEMENT, ImmutableMap.of("id", id));

    }

    public static NetworkRequestUtils convert(WebElement element) {
        return (NetworkRequestUtils) element;
    }
}
