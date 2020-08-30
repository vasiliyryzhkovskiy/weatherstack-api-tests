package code.stepdefs;

import code.ERROR_CODE;
import cucumber.api.java.ru.Тогда;
import helpers.Assertions;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import java.util.HashMap;

import static helpers.RequestHelper.getRequestHelper;
import static helpers.TestHelper.getConfigVars;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

public class ErrorSteps {

    String url;
    Response response;
    ResponseBody responseBody;
    JSONObject jsonObject;
    HashMap<String, String> error;
    String actualCode;
    String actualMessage;

    @Тогда("^Проверка получения ошибки с кодом \"([^\"]*)\" и сообщением \"([^\"]*)\"$")
    public void checkErrorMessageByCode(String expectedCode, String expectedMessage) {

        ERROR_CODE code = ERROR_CODE.get(expectedCode);

        switch (code) {
            case ERROR_CODE_101:
                checkErrorCode101(expectedCode, expectedMessage);
                break;
            case ERROR_CODE_105:
                checkErrorCode105(expectedCode, expectedMessage);
                break;
            case ERROR_CODE_603:
                checkErrorCode603(expectedCode, expectedMessage);
                break;
            default:
                fail("Нет реализации для кода " + expectedCode);
        }

    }

    private void checkErrorCode603(String expectedCode, String expectedMessage) {
        url = getConfigVars().host() + getRequestHelper().getUrlPartHistorical();
        response = getRequestHelper().getRequest((given()
                .queryParam("access_key", getConfigVars().accessKey())
                .queryParam("query", "Москва")
                .queryParam("historical_date", "1986-09-07")), url);

        Assertions.assertWithAllure(response.getStatusCode(), HttpStatus.SC_OK);

        responseBody = response.getBody();
        jsonObject = responseBody.as(JSONObject.class);
        error = (HashMap<String, String>) jsonObject.get("error");

        actualCode = String.valueOf(error.get("code"));
        actualMessage = error.get("info");

        Assertions.assertWithAllure(expectedCode, actualCode);
        Assertions.assertWithAllure(expectedMessage, actualMessage);
    }

    private void checkErrorCode105(String expectedCode, String expectedMessage) {
        url = getConfigVars().host() + getRequestHelper().getUrlPartCurrent();
        response = getRequestHelper().getRequest((given()
                .queryParam("access_key", getConfigVars().accessKey())
                .queryParam("query", "Москва")
                .queryParam("language", "ru")), url);

        Assertions.assertWithAllure(response.getStatusCode(), HttpStatus.SC_OK);

        responseBody = response.getBody();
        jsonObject = responseBody.as(JSONObject.class);
        error = (HashMap<String, String>) jsonObject.get("error");

        actualCode = String.valueOf(error.get("code"));
        actualMessage = error.get("info");

        Assertions.assertWithAllure(expectedCode, actualCode);
        Assertions.assertWithAllure(expectedMessage, actualMessage);
    }

    private void checkErrorCode101(String expectedCode, String expectedMessage) {
        url = getConfigVars().host() + getRequestHelper().getUrlPartHistorical();
        response = getRequestHelper().getRequest((given()
                .queryParam("access_key", getConfigVars().accessKeyInavalid())
                .queryParam("query", "Москва")), url);

        Assertions.assertWithAllure(response.getStatusCode(), HttpStatus.SC_OK);

        responseBody = response.getBody();
        jsonObject = responseBody.as(JSONObject.class);
        error = (HashMap<String, String>) jsonObject.get("error");

        actualCode = String.valueOf(error.get("code"));
        actualMessage = error.get("info");

        Assertions.assertWithAllure(expectedCode, actualCode);
        Assertions.assertWithAllure(expectedMessage, actualMessage);
    }
}