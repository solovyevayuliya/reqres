package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import in.reqres.model.RegresResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.net.http.HttpRequest.newBuilder;

public class ReqresApi {
    private final HttpClient client = HttpClient.newBuilder().build();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String rootUrl = "https://reqres.in";

    public <T> RegresResponse<T> getListUsers(Map<String, String> params, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder builder = new URIBuilder(rootUrl).setPath("/api/users/");
        for (var param : params.entrySet()) {
            builder.addParameter(param.getKey(), param.getValue());
        }
        var request = newBuilder(builder.build()).GET().build();
        return send(request, type);
    }

    public <T> RegresResponse<T> getUser(String userId, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        List<String> pathSegs = List.of("api", "users", userId);
        URIBuilder builder = new URIBuilder(rootUrl).setPathSegments(pathSegs);
        HttpRequest request = newBuilder(builder.build()).GET().build();
        return send(request, type);
    }

    public <T> RegresResponse<T> getListUsersUnknown(Map<String, String> params, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder builder = new URIBuilder(rootUrl).setPath("/api/unknown/");
        for (var param : params.entrySet()) {
            builder.addParameter(param.getKey(), param.getValue());
        }
        HttpRequest request = newBuilder(builder.build()).GET().build();
        return send(request, type);
    }

    public <T> RegresResponse<T> getUserUnknown(String userId, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        List<String> pathSegs = List.of("api", "unknown", userId);
        URIBuilder builder = new URIBuilder(rootUrl).setPathSegments(pathSegs);
        HttpRequest request = newBuilder(builder.build()).GET().build();
        return send(request, type);
    }

    public <T> RegresResponse<T> postCreateUser(Map<String, String> formData, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder builder = new URIBuilder(rootUrl).setPath("/api/users");
        var body = mapper.writeValueAsString(formData);
        HttpRequest request = newBuilder(builder.build())
                .header("Content-Type", "application/json")
                .POST(ofString(body))
                .build();
        return send(request, type);
    }

    public <T> RegresResponse<T> putUser(String userId, Map<String, String> formData, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        List<String> pathSegs = List.of("api", "users", userId);
        URIBuilder builder = new URIBuilder(rootUrl).setPathSegments(pathSegs);
        var body = mapper.writeValueAsString(formData);
        HttpRequest request = newBuilder(builder.build())
                .header("Content-Type", "application/json")
                .PUT(ofString(body))
                .build();
        return send(request, type);
    }

    public <T> RegresResponse<T> patchUser(String userId, Map<String, String> formData, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        List<String> pathSegs = List.of("api", "users", userId);
        URIBuilder builder = new URIBuilder(rootUrl).setPathSegments(pathSegs);
        var body = mapper.writeValueAsString(formData);
        HttpRequest request = newBuilder(builder.build())
                .header("Content-Type", "application/json")
                .method("PATCH", ofString(body))
                .build();
        return send(request, type);
    }

    public <T> RegresResponse<T> deleteUserUnknown(String userId) throws URISyntaxException, IOException, InterruptedException {
        List<String> pathSegs = List.of("api", "unknown", userId);
        URIBuilder builder = new URIBuilder(rootUrl).setPathSegments(pathSegs);
        HttpRequest request = newBuilder(builder.build())
                .DELETE()
                .build();
        return send(request);
    }

    public <T> RegresResponse<T> postLoginUser(Map<String, String> formData, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder builder = new URIBuilder(rootUrl).setPath("/api/login");
        var body = mapper.writeValueAsString(formData);
        HttpRequest request = newBuilder(builder.build())
                .header("Content-Type", "application/json")
                .POST(ofString(body))
                .build();
        return send(request, type);
    }

    public <T> RegresResponse<T> postRegisterUser(Map<String, String> formData, Class<T> type) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder builder = new URIBuilder(rootUrl).setPath("/api/register");
        var body = mapper.writeValueAsString(formData);
        HttpRequest request = newBuilder(builder.build())
                .header("Content-Type", "application/json")
                .POST(ofString(body))
                .build();
        return send(request, type);
    }

    private <T> RegresResponse<T> send(HttpRequest request) throws IOException, InterruptedException {
        return send(request, null);
    }

    private <T> RegresResponse<T> send(HttpRequest request, Class<T> type) throws IOException, InterruptedException {
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        T body = null;
        if (type != null && response.body() != null && !response.body().equals("{}")) {
            body = mapper.readValue(response.body(), type);
        }
        return new RegresResponse<>(body, response.statusCode());
    }
}
