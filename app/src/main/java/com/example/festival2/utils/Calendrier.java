package com.example.festival2.utils;

import static android.content.ContentUris.withAppendedId;
import static java.lang.Long.parseLong;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.festival2.R;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.ui.artiste.Artiste;
import com.example.festival2.ui.scenes.Scenes;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Observer;
import java.util.TimeZone;

public class Calendrier extends AppCompatActivity {

    Bdd bdd = new Bdd(this);
    Observer context;
    long eventId;
    long dureeLong;
    Date date;
    String[] horaire = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);

        eventId = (long) getIntent().getIntExtra("eventId",0);

        if(eventId != 0){
            try {
                // Suppression de levent dans le calendrier
                deleteEventToCalender(eventId);
                Log.i("Bdd", "Suppression de l'event n° " + eventId + " dans le calendrier, " +
                        "retour vers la page " + getIntent().getStringExtra("page") + ".");

                // Suppression de levent dans la base
                deleteEventTOBdd(getIntent().getStringExtra("artiste"), eventId);
                Log.i("Bdd", "Suppression de l'event n° " + eventId + " dans la base de données, " +
                        "retour vers la page " + getIntent().getStringExtra("page") + ".");

                // Infobulle pour l'utilisateur
                Toast.makeText(this, "Le concert " + getIntent().getStringExtra("artiste") +
                        " a été retiré dans votre calendrier", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }else{
            try {

                // Ajout de levent dans le calendrier
                addEventToCalender(getIntent().getStringExtra("artiste"),
                        getIntent().getStringExtra("description"),
                        getIntent().getStringExtra("scene") + " - " + getIntent().getStringExtra("place"),
                        getIntent().getStringExtra("date"),
                        getIntent().getStringExtra("heure"),
                        dureeLong);
                Log.i("Bdd", "Ajout de l'event n° " + eventId + " dans la base de données, " +
                        "retour vers la page " + getIntent().getStringExtra("page") + ".");

                // Ajout de levent dans la base
                addEventTOBdd(getIntent().getStringExtra("artiste"),eventId);
                Log.i("Bdd", "Ajout de l'event n° " + eventId + " dans la base de données, " +
                        "retour vers la page " + getIntent().getStringExtra("page") + ".");

                // Infobulle pour l'utilisateur
                Toast.makeText(this, "Le concert " + getIntent().getStringExtra("artiste") +
                        " a été ajouté dans votre calendrier", Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Retour sur la page de l'artiste
        if(Objects.equals(getIntent().getStringExtra("page"), "artiste")){
            Intent intent = new Intent(this, Artiste.class);
            intent.putExtra("artiste", getIntent().getStringExtra("artiste"));
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, Scenes.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Méthode pour une insertion d'event avec ouverture du calendrier et validation du User
    public void addEventVisible(String heure, long startDate, long duree, String artiste) {
        try {
            horaire = heure.split("h");
        } catch (Exception e) {
            e.printStackTrace();
        }

        startDate = startDate + Integer.valueOf(horaire[0]) * 60 * 60 * 1000 + Integer.valueOf(horaire[1]) * 60 * 1000;
        long endDate = startDate + duree * 60 * 1000;

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.TITLE, artiste);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // Méthode d'insertion silencieuse dans le calendrier
    public long addEventToCalender(String artiste, String description, String place, String startDateString, String heure, Long duree) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pp1 = new ParsePosition(0);
        date = sdf.parse(startDateString, pp1);
        long startDate = date.getTime();

        dureeLong = (long) getIntent().getIntExtra("duree",10);

        try {
            horaire = heure.split("h");
        } catch (Exception e) {
            e.printStackTrace();
        }

        startDate = startDate + Integer.valueOf(horaire[0]) * 60 * 60 * 1000 + Integer.valueOf(horaire[1]) * 60 * 1000;
        long endDate = startDate + duree * 60 * 1000;

        TimeZone timeZone = TimeZone.getDefault();

        // insert event to calendar
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID,2);
        values.put(CalendarContract.Events.DTSTART, startDate);
        values.put(CalendarContract.Events.DTEND, endDate);
        values.put(CalendarContract.Events.STATUS, 1); // entries tentative (0), confirmed (1) or canceled (2):
        values.put(CalendarContract.Events.TITLE, artiste);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.EVENT_LOCATION, place);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
        values.put(CalendarContract.Events.HAS_ALARM, 1);
        Uri eventUri;
        eventUri = Uri.parse("content://com.android.calendar/events");
        Uri uri = getContentResolver().insert(eventUri, values);
        eventId = parseLong(uri.getLastPathSegment());
        Log.i("Calendrier", uri.toString());

        //add reminder for event
        Uri REMINDERS_URI;

        ContentValues reminders = new ContentValues();
        reminders.put(CalendarContract.Reminders.EVENT_ID, eventId);
        reminders.put(CalendarContract.Reminders.METHOD, 1); // Alert Methods: Default(0), Alert(1), Email(2), SMS(3)
        reminders.put(CalendarContract.Reminders.MINUTES, 15);
        REMINDERS_URI = Uri.parse("content://com.android.calendar/reminders");
        Uri remindersUri = getContentResolver().insert(REMINDERS_URI, reminders);
        Log.i("Calendrier", remindersUri.toString());

        return eventId;
    }

    // Méthode d'insertion dans la base de données
    public void addEventTOBdd(String artiste, long eventId){
        bdd.open();
        bdd.insertParticipe(artiste, eventId);
        bdd.close();
        Log.i("Bdd", "Insert dans la table participes " +
                artiste + " -> " + eventId);
    }

    // Méthode de suppression de la participation dans le calendrier
    public void deleteEventToCalender(long eventId) {
        ContentResolver cr = getContentResolver();
        Uri deleteUri = null;
        deleteUri = withAppendedId(CalendarContract.Events.CONTENT_URI, eventId);
        cr.delete(deleteUri, null, null);
    }

    // Méthode de suppression de la participation dans la base de données
    public void deleteEventTOBdd(String artiste, long eventId){
        bdd.open();
        bdd.deleteParticipe(artiste, eventId);
        bdd.close();
        Log.i("Bdd", "Delete dans la table participes " +
                artiste + " -> " + eventId);
    }
}