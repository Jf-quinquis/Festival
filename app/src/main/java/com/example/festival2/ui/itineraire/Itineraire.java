package com.example.festival2.ui.itineraire;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.example.festival2.R;
import com.example.festival2.databinding.ActivityItineraireBinding;
import com.example.festival2.ui.aide.Aide;
import com.example.festival2.ui.favoris.Favoris;
import com.example.festival2.ui.groupes.Groupes;
import com.example.festival2.ui.numeros.Numeros;
import com.example.festival2.ui.scenes.Scenes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class Itineraire extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityItineraireBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        binding = ActivityItineraireBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setHasOptionsMenu(boolean hasMenu) {
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 16.0f;
        float iconAlpha = 0.9f;
        float zIndex = 0.9f;


        // Add markers
        int[][] markers = new int[13][3];

        // Coordonnées des markers
        LatLng festival = new LatLng(48.835048, 2.220070);
        LatLng entree = new LatLng(48.839127, 2.221070);
        LatLng amplifiee = new LatLng(48.830002, 2.222169);
        LatLng acoustique = new LatLng(48.835843, 2.218811);
        LatLng croixRouge1 = new LatLng(48.83792, 2.21946);
        LatLng croixRouge2 = new LatLng(48.83317, 2.22165);
        LatLng toilette1 = new LatLng(48.83714, 2.22124);
        LatLng toilette3 = new LatLng(48.83412, 2.21939);
        LatLng toilette4 = new LatLng(48.835212, 2.221525);
        LatLng restauration1 = new LatLng(48.83728, 2.21912);
        LatLng restauration2 = new LatLng(48.83385, 2.22161);
        LatLng camping = new LatLng(48.83129, 2.22206);



        mMap.addMarker(new MarkerOptions().position(festival).title("Rock en Seine").snippet("Festival").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_festival)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(entree).title("Entrée").zIndex(zIndex).
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_parking)).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(amplifiee).title("Scéne amplifiée").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_amplifiee)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(acoustique).title("Scéne acoustique").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_acoustique)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(croixRouge1).title("Croix rouge1").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_croix_rouge)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(croixRouge2).title("Croix rouge2").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_croix_rouge)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(toilette1).title("Toilettes1").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_toilette)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(toilette3).title("Toilettes2").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_toilette)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(toilette4).title("Toilettes3").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_toilette)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(toilette4).title("Toilettes4").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_toilette)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(restauration1).title("Restauration1").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_restaurant)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(restauration2).title("Restauration2").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_restaurant)).
                zIndex(zIndex).alpha(iconAlpha));

        mMap.addMarker(new MarkerOptions().position(camping).title("Zone de camping").
                icon(bitmapDescriptorFromVector(this, R.drawable.ic_camping)).
                zIndex(zIndex).alpha(iconAlpha));

        // Zone du festival
        mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(48.839127, 2.221070),
                        new LatLng(48.838933, 2.221189),
                        new LatLng(48.837434, 2.221488),
                        new LatLng(48.835428, 2.221906),
                        new LatLng(48.831892, 2.221888),
                        new LatLng(48.829792, 2.223088),
                        new LatLng(48.829569, 2.221753),
                        new LatLng(48.831981, 2.220820),
                        new LatLng(48.832570, 2.220744),
                        new LatLng(48.832880, 2.220349),
                        new LatLng(48.834468, 2.219075),
                        new LatLng(48.835696, 2.218483),
                        new LatLng(48.835751, 2.218392),
                        new LatLng(48.838183, 2.219113),
                        new LatLng(48.838343, 2.219454),
                        new LatLng(48.838188, 2.220820),
                        new LatLng(48.839127, 2.221070))
                .zIndex(0.7f)
                .fillColor(R.color.festival)
                .strokeColor(Color.GRAY));

        // Zone de camping
        mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(48.836830, 2.221609),
                        new LatLng(48.835432, 2.221905),
                        new LatLng(48.834078, 2.221935),
                        new LatLng(48.834163, 2.220676),
                        new LatLng(48.835606, 2.220630),
                        new LatLng(48.836830, 2.221609))
                .zIndex(0.8f)
                .fillColor(R.color.camping)
                .strokeColor(Color.GRAY));

        // Positionnement sur la carte
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(festival, zoomLevel));
    }

    // Transforme Vector en Bitmaps
    @SuppressLint("ResourceAsColor")
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    // Menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchBar = menu.findItem(R.id.actionSearch);
        searchBar.setVisible(false);
        return true;
    }

    @Override
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