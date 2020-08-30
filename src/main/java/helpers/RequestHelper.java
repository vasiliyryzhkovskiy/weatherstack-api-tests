package helpers;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestHelper {

    private static RequestHelper requestHelper;
    private String urlPartCurrent = "current";
    private String urlPartHistorical = "historical";

    public Response getRequest(RequestSpecification requestSpecification, String url) {
        Response response = requestSpecification.get(url);
        Allure.step("response :\n" + response.getBody().prettyPrint());
        return response;
    }

    public static RequestHelper getRequestHelper() {
        if (requestHelper == null) {
            requestHelper = new RequestHelper();
        }
        return requestHelper;
    }


}
