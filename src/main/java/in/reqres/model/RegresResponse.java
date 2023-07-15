package in.reqres.model;

public class RegresResponse<T> {

    private final T body;
    private final int statusCode;

    public RegresResponse(T body, int statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public T getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
