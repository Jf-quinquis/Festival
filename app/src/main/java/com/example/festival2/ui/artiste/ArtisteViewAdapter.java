package com.example.festival2.ui.artiste;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.festival2.R;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.bdd.ModeleArtiste;
import com.example.festival2.utils.Calendrier;

import java.util.ArrayList;

public class ArtisteViewAdapter extends RecyclerView.Adapter<ArtisteViewHolder>
        implements View.OnClickListener {

    Context context;
    String url;
    ArrayList<ModeleArtiste> artiste;
    Bdd bdd;

    public ArtisteViewAdapter(Context context, ArrayList<ModeleArtiste> artiste) {
        this.context = context;
        this.artiste = artiste;
    }

    @NonNull
    @Override
    public ArtisteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_artiste_ligne, parent, false);
        return new ArtisteViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ArtisteViewHolder holder, @SuppressLint("RecyclerView") int position) {

        url = artiste.get(position).getImage();
        Glide.with(context).load(url).into(holder.imageGroupe);
        holder.textGroupe.setText(artiste.get(position).getArtiste());
        holder.textPlace.setText(artiste.get(position).getPlace());

        String passage = artiste.get(position).getScene() + " " +
                artiste.get(position).getJour() + " à " +
                artiste.get(position).getHeure();

        holder.textPassage.setText(passage);

        holder.textWeb.setText(artiste.get(position).getWeb());
        holder.textTexte.setText(artiste.get(position).getTexte());
        holder.textTexte.setMovementMethod(new ScrollingMovementMethod());

        // modification du texte participation
        if(artiste.get(position).getParticipe() == 1){
            holder.textParticipe.setText(R.string.pouce_up);
            holder.textFavori.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        }else{
            holder.textFavori.setText(R.string.pouce_down);
            holder.textFavori.setTextColor(ContextCompat.getColor(context, R.color.khaki));
        }

        // Gestion de la participation
        holder.textParticipe.setOnClickListener(v -> {
            if (artiste.get(position).getParticipe() == 1) {
                Intent intent = new Intent(context, Calendrier.class);
                intent.putExtra("artiste", artiste.get(position).getArtiste());
                intent.putExtra("eventId", artiste.get(position).getEventId());
                intent.putExtra("page", "artiste");
                startActivity(context, intent, null);
            } else {
                Intent intent = new Intent(context, Calendrier.class);
                intent.putExtra("artiste", artiste.get(position).getArtiste());
                intent.putExtra("descritption", artiste.get(position).getArtiste());
                intent.putExtra("place", artiste.get(position).getPlace());
                intent.putExtra("scene", artiste.get(position).getScene());
                intent.putExtra("date", artiste.get(position).getDate());
                intent.putExtra("heure", artiste.get(position).getHeure());
                intent.putExtra("duree", artiste.get(position).getDuree());
                intent.putExtra("jour", artiste.get(position).getJour());
                intent.putExtra("page", "artiste");
                startActivity(context, intent, null);
            }
        });

        // modification de l'image favori
        if(artiste.get(position).getFavori() == 1){
            holder.textFavori.setText(R.string.coeur);
            holder.textFavori.setTextColor(ContextCompat.getColor(context, R.color.red));
        }else{
            holder.textFavori.setText(R.string.coeur_brise);
            holder.textFavori.setTextColor(ContextCompat.getColor(context, R.color.lightcoral));
        }

        // Gestion du favori
        holder.textFavori.setOnClickListener(v -> {
            bdd = new Bdd(context);
            bdd.open();
            if (artiste.get(position).getFavori() == 1) {
                bdd.deleteFavori(artiste.get(position).getArtiste());
                Toast.makeText(context, "Le groupe " + artiste.get(position).getArtiste()  + " a été retiré de vos favoris", Toast.LENGTH_SHORT).show();
                ArtisteViewAdapter.this.setData(bdd.getArtiste(artiste.get(position).getArtiste()));
            } else {
                bdd.insertFavori(artiste.get(position).getArtiste());
                Toast.makeText(context, "Le groupe " + artiste.get(position).getArtiste()  + " a été ajouter à vos favoris", Toast.LENGTH_SHORT).show();}
                ArtisteViewAdapter.this.setData(bdd.getArtiste(artiste.get(position).getArtiste()));
            bdd.close();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<ModeleArtiste> artiste) {
        this.artiste = artiste;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return artiste.size();
    }

    @Override
    public void onClick(View view) {    }

}