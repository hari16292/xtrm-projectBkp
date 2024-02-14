package pojoClasses.getUserPersonal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;

    public ResStatus getStatus() {
        return status;
    }

    public void setStatus(ResStatus status) {
        this.status = status;
    }

    public ResData getData() {
        return data;
    }

    public void setData(ResData data) {
        this.data = data;
    }

    @JsonProperty("data")
    private ResData data;

}
