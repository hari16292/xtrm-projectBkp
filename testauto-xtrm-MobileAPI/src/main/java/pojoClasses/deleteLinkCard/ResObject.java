package pojoClasses.deleteLinkCard;

import com.fasterxml.jackson.annotation.JsonProperty;
import pojoClasses.linkCard.ResStatus;

public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;
    @JsonProperty("data")
    private String data;

    public ResStatus getStatus() {
        return status;
    }

    public void setStatus(ResStatus status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
