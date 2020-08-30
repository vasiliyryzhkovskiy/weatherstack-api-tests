package helpers;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class Assertions {

    public static void assertWithAllure(Object expected, Object actual) {
        Allure.step(String.format("Ожидаемый результат %s - фактический результат %s", expected, actual));
        log.info(String.format("Ожидаемый результат %s - фактический результат %s", expected, actual));
        Assert.assertEquals(actual, expected);
    }
}