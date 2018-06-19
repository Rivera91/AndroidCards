package backend;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CardApiService {

    public static final String BASE_URL = "https://deckofcardsapi.com/";

    @GET("api/deck/new/shuffle/")
    Call<CardApiResponse> getNewShuffle();

    @GET("api/deck/{deck_id}/draw/?count=num_cards")
    Call<CardApiResponse> getDraw1(@Path("deck_id")String id, @Query("count")int numCards);
}
