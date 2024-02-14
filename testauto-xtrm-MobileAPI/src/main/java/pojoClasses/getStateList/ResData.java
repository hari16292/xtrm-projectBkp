package pojoClasses.getStateList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("state_name")
    private String state_name;

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
