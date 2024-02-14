package pojoClasses.updateWallet;

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

}
