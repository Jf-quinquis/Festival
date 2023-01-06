package com.example.festival2.ui.scenes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;
import com.example.festival2.bdd.ModeleArtiste;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.ui.aide.Aide;
import com.example.festival2.ui.favoris.Favoris;
import com.example.festival2.ui.groupes.Groupes;
import com.example.festival2.ui.itineraire.Itineraire;
import com.example.festival2.ui.numeros.Numeros;

import java.util.ArrayList;

public class Scenes extends AppCompatActivity {

    private Bdd bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes);
        getSupportActionBar().setTitle("Scenes");

        bdd = new Bdd(this);
        bdd.open();
        ArrayList<ModeleArtiste> listeScenesAmplifiee = bdd.getListeScene("amplifiée");
        ArrayList<ModeleArtiste> listeScenesAcoustique = bdd.getListeScene("acoustique");
        bdd.close();

        if (listeScenesAmplifiee != null){
            RecyclerView recyclerView = findViewById(R.id.recycleViewScene1);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ScenesViewAdapter(getApplicationContext(), listeScenesAmplifiee));

        }

        if (listeScenesAmplifiee != null){
            RecyclerView recyclerView = findViewById(R.id.recycleViewScene2);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ScenesViewAdapter(getApplicationContext(), listeScenesAcoustique));

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