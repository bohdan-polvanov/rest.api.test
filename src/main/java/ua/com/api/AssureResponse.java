package ua.com.api;

import io.restassured.response.Response;

public class AssureResponse {
    private final Response response;

    public AssureResponse(Response response) {
        this.response = response;
    }
}
