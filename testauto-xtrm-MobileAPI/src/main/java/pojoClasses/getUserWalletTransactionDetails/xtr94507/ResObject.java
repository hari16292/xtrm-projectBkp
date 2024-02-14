package pojoClasses.getUserWalletTransactionDetails.xtr94507;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;
    @JsonProperty("data")
    private ResData data;
    @JsonProperty("keys")
    private List<String> keys;

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

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
