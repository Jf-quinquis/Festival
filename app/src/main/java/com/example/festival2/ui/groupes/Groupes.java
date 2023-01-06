package com.example.festival2.ui.groupes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival2.R;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.bdd.ModeleArtiste;
import com.example.festival2.ui.aide.Aide;
import com.example.festival2.ui.favoris.Favoris;
import com.example.festival2.ui.itineraire.Itineraire;
import com.example.festival2.ui.numeros.Numeros;
import com.example.festival2.ui.scenes.Scenes;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Groupes extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Bdd bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupes);

        getSupportActionBar().setTitle("Groupes / Artistes");

        bdd = new Bdd(this);
        bdd.open();
        ArrayList<ModeleArtiste> listeModeleArtistes = bdd.getListeArtistes();
        bdd.close();

        if (listeModeleArtistes != null){
            RecyclerView recyclerView = findViewById(R.id.recycleViewGroupes);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new GroupesViewAdapter(getApplicationContext(), listeModeleArtistes));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.actionSearch);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

   @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(this, "Seul les lettres sont autorisées dans la recherche.", Toast.LENGTH_LONG).show();
        if(Pattern.compile("[A-Z|a-z]").matcher(newText).find()){
            bdd.open();
            ArrayList<ModeleArtiste> newlisteArtistes = bdd.getListeArtistesFilter(newText);
            bdd.close();
            RecyclerView recyclerView = findViewById(R.id.recycleViewGroupes);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new GroupesViewAdapter(getApplicationContext(), newlisteArtistes));
        }
        return false;
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