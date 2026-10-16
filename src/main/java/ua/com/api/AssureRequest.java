package ua.com.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AssureRequest {
    private String url;
    private String path;
    private String body;
    private String proxyHost;
    private Integer proxyPort;
    private String description;
    private Boolean sslValidation = true;
    private final RequestSpecBuilder requestSpecBuilder;
    private final Map<String, String> headers = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(AssureRequest.class);

    static {
        System.setProperty("slf4j.internalVerbosity", "WARN");
    }

    private AssureRequest() {
        this.requestSpecBuilder = new RequestSpecBuilder();
    }

    public static AssureRequest initApiTest() {
        return new AssureRequest();
    }

    public AssureRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public AssureRequest disableSslValidation() {
        this.sslValidation = false;
        return this;
    }

    public AssureRequest setProxy(String host, int port) {
        this.proxyHost = host;
        this.proxyPort = port;
        return this;
    }

    public AssureRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public AssureRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public AssureRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public AssureRequest setBody(JSONObject body) {
        this.body = body.toString();
        return this;
    }

    public AssureRequest addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public AssureResponse sendRequest(Method method) {
        log.info("= = = = = = = = = = = = Init API Request = = = = = = = = = = = =");
        log.info("Test Case Description: {}", description);

        if (proxyHost != null && proxyPort != null) {
            requestSpecBuilder.setProxy(proxyHost, proxyPort);
            log.info("Config: Proxy set to '{}:{}'", proxyHost, proxyPort);
        }

        if (!sslValidation) {
            requestSpecBuilder.setRelaxedHTTPSValidation();
            log.info("Config: SSL validation disabled (Relaxed HTTPS Validation).");
        }

        if (url != null) {
            requestSpecBuilder.setBaseUri(url);
            log.info("Config: Base URI set to '{}'", url);
        } else {
            log.error("Attempted to send a request without a Base URI.");
            throw new IllegalStateException("URL is null. Base URI is mandatory.");
        }

        if (path != null) {
            requestSpecBuilder.setBasePath(path);
            log.info("Config: Base Path set to '{}'", path);
        } else {
            log.error("Attempted to send a request without a Base Path.");
            throw new IllegalStateException("Path is null. API Path is mandatory.");
        }

        if (body != null) {
            requestSpecBuilder.setBody(body);
            log.info("Config: Request body set to:\n{}", body);
        }

        if (!headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestSpecBuilder.addHeader(entry.getKey(), entry.getValue());
                log.info("Config: Header added -> '{}': '{}'", entry.getKey(), entry.getValue());
            }
        }

        log.info("Executing {} request.", method);

        try {
            Response response = RestAssured.given().spec(requestSpecBuilder.build()).when().request(method);
            return new AssureResponse(response);
        } catch (Exception e) {
            String errorMessage = String.format("Critical error! Failed to send %s request", method);
            log.error(errorMessage);
            log.error("Reason: {}", e.getMessage());
            throw new RuntimeException(errorMessage, e);
        }
    }
}