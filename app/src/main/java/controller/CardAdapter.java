package controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.CardDraw;
import nyc.c4q.cards.R;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    List<CardDraw> newCard;


    public CardAdapter(List<CardDraw> newCard) {
        this.newCard = newCard;
    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        //context = parent.getContext();
        return new CardViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardDraw cards = newCard.get(position);
        holder.bindImage(cards);

    }

    @Override
    public int getItemCount() {
        return newCard.size();
    }


//    public void addAll(List<CardDraw> newCard) {
//        this.newCard = newCard;
////        notifyDataSetChanged();
//    }



    public void addCards(List<CardDraw> cards) {
        newCard.addAll(cards);
    }




    class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView image1;


        public CardViewHolder(View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.image1);

        }

        void bindImage(CardDraw newCard) {
            Picasso.with(itemView.getContext()).load(newCard.getImage()).into(image1);
        }
    }
}
