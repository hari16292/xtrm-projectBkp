package pojoClasses.getPaymentMethods;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;
    @JsonProperty("data")
    private List<String> data;

    public ResStatus getStatus() {
        return status;
    }

    public void setStatus(ResStatus status) {
        this.status = status;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
