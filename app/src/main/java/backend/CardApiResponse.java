package backend;

import java.util.List;

import model.CardDraw;

public class CardApiResponse {

    private boolean success;
    private boolean shuffled;
    private List<CardDraw> cards;
    private String deck_id;
    private int remaining;


    public CardApiResponse(boolean success, boolean shuffled,  List<CardDraw> cards, String deck_id, int remaining) {
        this.success = success;
        this.shuffled = shuffled;
        this.deck_id = deck_id;
        this.remaining = remaining;
        this.cards = cards;

    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getShuffled(){
        return shuffled;
    }

    public void setShuffled(boolean shuffled){
        this.shuffled = shuffled;
    }

    public List<CardDraw> getCards() {

        return cards;
    }

    public void setCards(List<CardDraw> cards) {

        this.cards = cards;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

}
