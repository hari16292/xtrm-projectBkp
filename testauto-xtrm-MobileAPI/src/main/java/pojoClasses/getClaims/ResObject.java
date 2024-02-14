package pojoClasses.getClaims;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ResObject {
    @JsonProperty("Status")
    private ResStatus status;
    @JsonProperty("data")
    private List<ResData> data;
    @JsonProperty("current_page")
    private String current_page;
    @JsonProperty("items_per_page")
    private String items_per_page;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getItems_per_page() {
        return items_per_page;
    }

    public void setItems_per_page(String items_per_page) {
        this.items_per_page = items_per_page;
    }

    public String getRecords_total() {
        return records_total;
    }

    public void setRecords_total(String records_total) {
        this.records_total = records_total;
    }

    @JsonProperty("records_total")
    private String records_total;

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
