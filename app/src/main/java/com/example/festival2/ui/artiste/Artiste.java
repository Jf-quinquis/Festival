package com.example.festival2.ui.artiste;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.bdd.ModeleArtiste;
import com.example.festival2.ui.aide.Aide;
import com.example.festival2.ui.favoris.Favoris;
import com.example.festival2.ui.groupes.Groupes;
import com.example.festival2.ui.itineraire.Itineraire;
import com.example.festival2.ui.numeros.Numeros;
import com.example.festival2.ui.scenes.Scenes;
import com.example.festival2.utils.OnSwipeTouchListener;

import java.util.ArrayList;

public class Artiste extends AppCompatActivity {

    private Bdd bdd;
    String artisteName;
   ConstraintLayout layout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artiste);

        layout = findViewById(R.id.constraintlayout);
        getSupportActionBar().setTitle("Artiste");

        // A l'écoute du Swipe pour retourner sur la list des groupes
        layout.setOnTouchListener(new OnSwipeTouchListener(Artiste.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent intent = new Intent(Artiste.this, Groupes.class);
                startActivity(intent);
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                super.onSwipeLeft();
                Intent intent = new Intent(Artiste.this, Groupes.class);
                startActivity(intent);
            }
        });

        // Récupération du nom de l'artiste de la page Groupes
        artisteName = getIntent().getStringExtra("artiste");


        bdd = new Bdd(this);
        bdd.open();
        ArrayList<ModeleArtiste> artiste = bdd.getArtiste(artisteName);
        bdd.close();

        if (artiste != null){
            RecyclerView recyclerView = findViewById(R.id.recycleViewArtiste);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ArtisteViewAdapter(getApplicationContext(), artiste));
        }
    }

    // Menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchBar = menu.findItem(R.id.actionSearch);
        searchBar.setVisible(false);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_groupes:
                startActivity(new Intent(this, Groupes.class));
                return true;
            case R.id.menu_favoris:
                startActivity(new Intent(this, Favoris.class));
                return true;
            case R.id.menu_scenes:
                startActivity(new Intent(this, Scenes.class));
                return true;
            case R.id.menu_itineraire:
                startActivity(new Intent(this, Itineraire.class));
                return true;
            case R.id.menu_numeros:
                startActivity(new Intent(this, Numeros.class));
                return true;
            case R.id.menu_aide:
                startActivity(new Intent(this, Aide.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}