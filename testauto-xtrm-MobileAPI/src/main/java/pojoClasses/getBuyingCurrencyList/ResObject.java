package pojoClasses.getBuyingCurrencyList;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;
    @JsonProperty("data")
    private List<ResData> data;

    public ResStatus getStatus() {
        return status;
    }

    public void setStatus(ResStatus status) {
        this.status = status;
    }

    public List<ResData> getData() {
        return data;
    }

    public void setData(List<ResData> data) {
        this.data = data;
    }
}
