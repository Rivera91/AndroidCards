package model;

public class CardDraw {
    private String image;
    private String value;
    private String suit;
    private String code;
    private CardImages images;


    public CardDraw(String image, String value, String suit, String code, CardImages images) {
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.code = code;
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CardImages getImages() {
        return images;
    }

    public void setImages(CardImages images) {
        this.images = images;
    }
}
