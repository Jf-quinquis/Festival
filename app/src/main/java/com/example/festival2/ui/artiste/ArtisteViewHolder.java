package com.example.festival2.ui.artiste;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;

public class ArtisteViewHolder extends RecyclerView.ViewHolder {
    ImageView imageGroupe;
    TextView textGroupe;
    TextView textPlace;
    TextView textPassage;
    TextView textWeb;
    TextView textTexte;
    TextView textParticipe;
    TextView textFavori;


    public ArtisteViewHolder(@NonNull View itemView) {
        super(itemView);
        imageGroupe = itemView.findViewById(R.id.imageGroupe);
        textGroupe = itemView.findViewById(R.id.textGroupe);
        textPlace = itemView.findViewById(R.id.textPlace);
        textPassage = itemView.findViewById(R.id.textPassage);
        textWeb = itemView.findViewById(R.id.textWeb);
        textTexte = itemView.findViewById(R.id.textTexte);
        textParticipe = itemView.findViewById(R.id.textParticipe);
        textFavori = itemView.findViewById(R.id.textFavori);
    }
}