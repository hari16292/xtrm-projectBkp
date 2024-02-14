package pojoClasses.getGiftCards;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResData {
    @JsonProperty("brandName")
    private String brandName;
    @JsonProperty("imageUrls")
    private List<ResImageURLs> imageUrls;
    @JsonProperty("description")
    private String description;
    @JsonProperty("disclaimer")
    private String disclaimer;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("terms")
    private String terms;
    @JsonProperty("items")
    private List<ResItems> items;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<ResImageURLs> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<ResImageURLs> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ResItems> getItems() {
        return items;
    }

    public void setItems(List<ResItems> items) {
        this.items = items;
    }
}
