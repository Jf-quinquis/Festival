package com.example.festival2.ui.groupes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.festival2.R;
import com.example.festival2.bdd.ModeleArtiste;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.ui.artiste.Artiste;

import java.util.ArrayList;
import java.util.List;

public class GroupesViewAdapter extends RecyclerView.Adapter<GroupesViewHolder>
        implements View.OnClickListener {

    Context context;
    String url;
    ArrayList<ModeleArtiste> listeArtistes;
    Bdd bdd;

    public GroupesViewAdapter(Context context, ArrayList<ModeleArtiste> listeArtistes) {
        this.context = context;
        this.listeArtistes = listeArtistes;

    }

    @NonNull
    @Override
    public GroupesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_groupes_ligne, parent, false);
        return new GroupesViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(GroupesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        url = listeArtistes.get(position).getImage();
        Glide.with(context).load(url).into(holder.imageGroupe);
        holder.textGroupe.setText(listeArtistes.get(position).getArtiste());
        holder.textPlace.setText(listeArtistes.get(position).getPlace());

        System.out.println("Artiste : " + listeArtistes.get(position).getPlace());

        String passage = listeArtistes.get(position).getScene() + " " +
                listeArtistes.get(position).getJour() + " à " +
                listeArtistes.get(position).getHeure();

        holder.textPassage.setText(passage);

        // modification de l'image favori
        if(listeArtistes.get(position).getFavori() == 1){
            holder.textFavori.setText(R.string.coeur);
            holder.textFavori.setTextColor(ContextCompat.getColor(context, R.color.red));
        }else{
            holder.textFavori.setText(R.string.coeur_brise);
            holder.textFavori.setTextColor(ContextCompat.getColor(context, R.color.lightcoral));
        }

        // Modification du favori
        holder.textFavori.setOnClickListener(v -> {
            bdd = new Bdd(context);
            bdd.open();
            if (listeArtistes.get(position).getFavori() == 1) {
                bdd.deleteFavori(listeArtistes.get(position).getArtiste());
                Toast.makeText(context, "Le groupe " + listeArtistes.get(position).getArtiste()  + " a été retiré des favoris", Toast.LENGTH_SHORT).show();
                GroupesViewAdapter.this.setData(bdd.getListeArtistes());
            } else {
                bdd.insertFavori(listeArtistes.get(position).getArtiste());
                Toast.makeText(context, "Le groupe " + listeArtistes.get(position).getArtiste()  + " a été mis dans les favoris", Toast.LENGTH_SHORT).show();}
                GroupesViewAdapter.this.setData(bdd.getListeArtistes());
            bdd.close();

        });

        // Affichage du groupe
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Artiste.class);
                intent.putExtra("artiste", listeArtistes.get(position).getArtiste());
                view.getContext().startActivity(intent);
            }
        });
    }



    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<ModeleArtiste> listeArtistes) {
        this.listeArtistes = listeArtistes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listeArtistes.size();
    }

    @Override
    public void onClick(View view) {    }

    public void filter(String queryText) {
        GroupesViewAdapter.this.setData(bdd.getListeArtistes());
    }
}

