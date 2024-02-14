package pojoClasses.getLinkedBankAccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResFailed {

    @JsonProperty("error")
    private String error;
    @JsonProperty("error_message")
    private String error_message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

}
