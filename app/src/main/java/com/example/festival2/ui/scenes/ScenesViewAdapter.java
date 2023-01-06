package com.example.festival2.ui.scenes;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;
import com.example.festival2.bdd.ModeleArtiste;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.utils.Calendrier;

import java.util.ArrayList;

public class ScenesViewAdapter extends RecyclerView.Adapter<ScenesViewHolder>
        implements View.OnClickListener{

    View layout;
    Context context;
    ArrayList<ModeleArtiste> listeScenes;
    String scene;
    Bdd bdd;

    public ScenesViewAdapter(Context context, ArrayList<ModeleArtiste> listeScenes) {
        this.context = context;
        this.listeScenes = listeScenes;
        this.scene = scene;
    }

    @NonNull
    @Override
    public ScenesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_scenes_ligne, parent, false);
        return new ScenesViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ScenesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textGroupe.setText(listeScenes.get(position).getArtiste());
        holder.textPassage.setText(listeScenes.get(position).getJour() + " - " +
                listeScenes.get(position).getHeure());

        // modification de la couleur pour les groupes auxquels l'utilisateur veut voir
        if(listeScenes.get(position).getParticipe() == 1){
            holder.layoutAdapter.setBackground(ContextCompat.getDrawable(context, R.drawable.customborder_red));
            holder.layoutAdapter.setElevation(5);
        }else{
            holder.layoutAdapter.setBackground(ContextCompat.getDrawable(context, R.drawable.customborder_yellow));
            holder.layoutAdapter.setElevation(0);
        }

        // Modification de la participation
        holder.itemView.setOnClickListener(v -> {
            if (listeScenes.get(position).getParticipe() == 1) {
                Intent intent = new Intent(context, Calendrier.class);
                intent.putExtra("artiste", listeScenes.get(position).getArtiste());
                intent.putExtra("eventId", listeScenes.get(position).getEventId());
                intent.putExtra("page", "scenes");
                startActivity(context, intent, null);
            } else {
                Intent intent = new Intent(context, Calendrier.class);
                intent.putExtra("artiste", listeScenes.get(position).getArtiste());
                intent.putExtra("descritption", listeScenes.get(position).getArtiste());
                intent.putExtra("place", listeScenes.get(position).getPlace());
                intent.putExtra("scene", listeScenes.get(position).getScene());
                intent.putExtra("date", listeScenes.get(position).getDate());
                intent.putExtra("heure", listeScenes.get(position).getHeure());
                intent.putExtra("duree", listeScenes.get(position).getDuree());
                intent.putExtra("jour", listeScenes.get(position).getJour());
                intent.putExtra("page", "scenes");
                startActivity(context, intent, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listeScenes.size();
    }

    @Override
    public void onClick(View view) {

    }
}