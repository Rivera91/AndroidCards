package nyc.c4q.cards;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import backend.CardApiResponse;
import backend.CardApiService;
import controller.CardAdapter;
import model.CardDraw;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "JSON?";
    private CardApiService cardService;
    private Button shuffle;
    private Button draw;
    private EditText editText;
    private TextView remaining;
    private String newShuffledId;
    private int remainingCards;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardDraw> cardImageUrl = new ArrayList<>();
    // private ImageView image1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuffle = (Button) findViewById(R.id.shuffle);
        draw = (Button) findViewById(R.id.draw);
        editText = (EditText) findViewById(R.id.editText);
        recyclerView = (RecyclerView) findViewById(R.id.cardImage);


//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new CardAdapter(cardImageUrl);
//        recyclerView.setAdapter(adapter);


        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2);
        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CardAdapter(cardImageUrl);
        recyclerView.setAdapter(adapter);



        retrofit = new Retrofit.Builder()
                .baseUrl(CardApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        shuffle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                recyclerView.removeAllViewsInLayout();


//                if(cardImageUrl.size() > 0) {
//                    cardImageUrl.removeAll(cardImageUrl);
//                }

                CardApiService cardApiService = retrofit.create(CardApiService.class);
                Call<CardApiResponse> shuffleDeck = cardApiService.getNewShuffle();
                shuffleDeck.enqueue(new Callback<CardApiResponse>() {

                    @Override
                    public void onResponse(Call<CardApiResponse> shuffledDeck, Response<CardApiResponse> response) {
                        newShuffledId = response.body().getDeck_id();
                        remainingCards = response.body().getRemaining();
                        Log.e("shuffled cards", newShuffledId + " " + remainingCards);
                       // remaining.setText(getString(R.string.cards_remaining_, remainingCards + ""));
                    }

                    @Override
                    public void onFailure(Call<CardApiResponse> call, Throwable t) {
                        //Log.e(TAG, "onResponse: " + t.toString());
                    }
                });

            }
        });

        draw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(editText.getText().toString());
                if (editText.getText().toString().isEmpty() && editText.equals(0)) {
                    editText.setError("You must draw at least 1 card");

                } else if (num > remainingCards) {
                    editText.setError("There are only {%s} cards remaining");

                } else {

                    setKeyboardVisibility(false);

                    CardApiService cardApiService = retrofit.create(CardApiService.class);
                    Call<CardApiResponse> call = cardApiService.getDraw1(newShuffledId, num);
                    call.enqueue(new Callback<CardApiResponse>() {


                        @Override
                        public void onResponse(Call<CardApiResponse> call, Response<CardApiResponse> response) {

                            List<CardDraw> cardImageUrl = response.body().getCards();
                            adapter.addCards(cardImageUrl);
                           // adapter.notifyDataSetChanged();
                            editText.getText().clear();



                            Log.e("card", cardImageUrl.toString());
                            Log.e("card name", cardImageUrl.get(0).getSuit());
                            Log.e("Cards Remaining: ", String.valueOf(remainingCards));

                        }

                        @Override
                        public void onFailure(Call<CardApiResponse> call, Throwable t) {
                            Log.e(TAG, "onResponse: " + t.toString());
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });

//        image1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
//
//            }
//        });

    }

    public void setKeyboardVisibility(boolean show) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


}


//        draw.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                int num = Integer.parseInt(editText.getText().toString());
//
//                if (editText.getText().toString().isEmpty()) {
//                    editText.setError("You must draw at least 1 card");
//                    editText.requestFocus();
//                } else {
//                    CardApiService cardApiService = retrofit.create(CardApiService.class);
//                    Call<CardApiResponse> call = cardApiService.getDraw1(newShuffledId, num);
//                    call.enqueue(new Callback<CardApiResponse>()
//                    {
//
//
//                        @Override
//                        public void onResponse(Call<CardApiResponse> call, Response<CardApiResponse> response) {
//
//                            cardImageUrl = response.body().getCards();
//                            adapter.addAll(cardImageUrl);
//                            adapter.notifyDataSetChanged();
//
//
//                            Log.e("card",cardImageUrl.toString());
//                            Log.e("card name", cardImageUrl.get(0).getSuit());
//                        }
//
//                        @Override
//                        public void onFailure(Call<CardApiResponse> call, Throwable t) {
//                            Log.e(TAG, "onResponse: " + t.toString());
//                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    // editText.setText(null);
//
//
//
//                }
//
//
//            }
//        });
