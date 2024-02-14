package pojoClasses.createWallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResFailedStatus {
    @JsonProperty("error")
    private String error;
    @JsonProperty("code")
    private String code;
    @JsonProperty("type")
    private String type;
    @JsonProperty("message")
    private List<String> message;

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message) {
        this.message = message;
    }
}
