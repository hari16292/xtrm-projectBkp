package pojoClasses.getGiftCards;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResItems {

    @JsonProperty("utid")
    private String utid;
    @JsonProperty("rewardName")
    private String rewardName;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("status")
    private String status;
    @JsonProperty("valueType")
    private String valueType;
    @JsonProperty("rewardType")
    private String rewardType;
    @JsonProperty("maxValue")
    private String maxValue;
    @JsonProperty("minValue")
    private String minValue;
    @JsonProperty("faceValue")
    private String faceValue;
    @JsonProperty("countries")
    private List<String> countries;

    public String getUtid() {
        return utid;
    }

    public void setUtid(String utid) {
        this.utid = utid;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
}
