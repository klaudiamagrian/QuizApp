package com.example.quizapp;

import android.graphics.Typeface;
import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    List<Player> list;
    String currentName;
    int currentScore;

    public RankingAdapter(List<Player> list, String currentName, int currentScore) {
        this.list = list;
        this.currentName = currentName;
        this.currentScore = currentScore;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(View view) {
            super(view);
            txt = view.findViewById(android.R.id.text1);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player p = list.get(position);

        // jeśli name jest null, wyświetl "Wynik:"
        String playerName = (p.name == null || p.name.trim().isEmpty())
                ? "Wynik:"
                : p.name;

        String text = (position + 1) + ". " + playerName + " - " + p.score;
        holder.txt.setText(text);

        if (playerName.equals(currentName) && p.score == currentScore) {
            holder.txt.setTypeface(null, Typeface.BOLD);
        } else {
            holder.txt.setTypeface(null, Typeface.NORMAL);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}