package pojoClasses.getGiftCards;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResImageURLs {
    @JsonProperty("Img80W")
    private String img80W;
    @JsonProperty("Img130w")
    private String img130w;
    @JsonProperty("Img200W")
    private String img200W;
    @JsonProperty("Img300W")
    private String img300W;

    public String getImg80W() {
        return img80W;
    }

    public void setImg80W(String img80W) {
        this.img80W = img80W;
    }

    public String getImg130w() {
        return img130w;
    }

    public void setImg130w(String img130w) {
        this.img130w = img130w;
    }

    public String getImg200W() {
        return img200W;
    }

    public void setImg200W(String img200W) {
        this.img200W = img200W;
    }

    public String getImg300W() {
        return img300W;
    }

    public void setImg300W(String img300W) {
        this.img300W = img300W;
    }
}
