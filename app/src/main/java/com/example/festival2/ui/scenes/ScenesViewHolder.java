package com.example.festival2.ui.scenes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;

public class ScenesViewHolder extends RecyclerView.ViewHolder {
    TextView textGroupe;
    TextView textPassage;
    View layoutAdapter;


    public ScenesViewHolder(@NonNull View itemView) {
        super(itemView);
        textGroupe = itemView.findViewById(R.id.textGroupe);
        textPassage = itemView.findViewById(R.id.textPassage);
        layoutAdapter = itemView.findViewById(R.id.layoutAdapter);
    }
}