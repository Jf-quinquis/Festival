package com.example.festival2.ui.favoris;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;

public class FavorisViewHolder extends RecyclerView.ViewHolder {
    ImageView imageGroupe;
    TextView textGroupe;
    TextView textPassage;
    TextView textFavori;


    public FavorisViewHolder(@NonNull View itemView) {
        super(itemView);
        imageGroupe = itemView.findViewById(R.id.imageGroupe);
        textGroupe = itemView.findViewById(R.id.textGroupe);
        textPassage = itemView.findViewById(R.id.textPassage);
        textFavori = itemView.findViewById(R.id.textFavori);
    }
}