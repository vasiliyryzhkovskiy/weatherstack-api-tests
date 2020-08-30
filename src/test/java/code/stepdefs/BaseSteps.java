package code.stepdefs;

import cucumber.api.java.ru.Тогда;
import helpers.Assertions;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

import static helpers.RequestHelper.getRequestHelper;
import static helpers.TestHelper.getConfigVars;
import static io.restassured.RestAssured.given;

public class BaseSteps {


    @Тогда("^Запрос погоды по городу \"([^\"]*)\". Проверка соответствия \"([^\"]*)\" и \"([^\"]*)\"$")
    public void sucsessWheaterForCity(String expectedCityName, String expectedCountry, String expectedTimezoneId) throws IOException, ParseException {
        String url = getConfigVars().host() + getRequestHelper().getUrlPartCurrent();
        Response response = getRequestHelper().getRequest((given()
                .queryParam("access_key", getConfigVars().accessKey())
                .queryParam("query", expectedCityName)), url);

        Assertions.assertWithAllure(response.getStatusCode(), HttpStatus.SC_OK);

        ResponseBody responseBody = response.getBody();
        JSONObject jsonObject = responseBody.as(JSONObject.class);
        HashMap<String, String> location = (HashMap<String, String>) jsonObject.get("location");

        String actualCountry = location.get("country");
        String actualCityName = location.get("name");
        String actualTimezoneId = location.get("timezone_id");

        Assertions.assertWithAllure(expectedCountry, actualCountry);
        Assertions.assertWithAllure(expectedCityName, actualCityName);
        Assertions.assertWithAllure(expectedTimezoneId, actualTimezoneId);
    }

}
