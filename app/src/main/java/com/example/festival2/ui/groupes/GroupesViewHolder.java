package com.example.festival2.ui.groupes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.festival2.R;
import com.example.festival2.bdd.ModeleArtiste;

public class GroupesViewHolder extends RecyclerView.ViewHolder {

    ImageView imageGroupe;
    TextView textGroupe;
    TextView textPlace;
    TextView textPassage;
    TextView textFavori;


    public GroupesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageGroupe = itemView.findViewById(R.id.imageGroupe);
        textGroupe = itemView.findViewById(R.id.textGroupe);
        textPlace = itemView.findViewById(R.id.textPlace);
        textPassage = itemView.findViewById(R.id.textPassage);
        textFavori = itemView.findViewById(R.id.textFavori);
    }
}