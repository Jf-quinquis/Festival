package com.example.festival2.ui.numeros;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.festival2.R;
import com.example.festival2.ui.aide.Aide;
import com.example.festival2.ui.favoris.Favoris;
import com.example.festival2.ui.groupes.Groupes;
import com.example.festival2.ui.itineraire.Itineraire;
import com.example.festival2.ui.scenes.Scenes;

public class Numeros extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);

        getSupportActionBar().setTitle("Numéros utiles");

    }

    public void appelPompier (View view) {
       Intent callIntent = new Intent(Intent.ACTION_CALL);
       callIntent.setData(Uri.parse("tel:123456789"));
       startActivity(callIntent);
    }

    public void appelSAMU(View view) {
       Intent callIntent = new Intent(Intent.ACTION_CALL);
       callIntent.setData(Uri.parse("tel:15"));
       startActivity(callIntent);
    }

    public void appelPolice(View view) {
       Intent callIntent = new Intent(Intent.ACTION_CALL);
       callIntent.setData(Uri.parse("tel:17"));
       startActivity(callIntent);
    }

    public void appelUrgence(View view) {
       Intent callIntent = new Intent(Intent.ACTION_CALL);
       callIntent.setData(Uri.parse("tel:12"));
       startActivity(callIntent);
    }

    public void appelOrganisation(View view) {
       Intent callIntent = new Intent(Intent.ACTION_CALL);
       callIntent.setData(Uri.parse("tel:0633010203"));
       startActivity(callIntent);
    }

    public void appelCroixRouge(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0795212233"));
        startActivity(callIntent);
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