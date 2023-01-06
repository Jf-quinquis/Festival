package com.example.festival2.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Bdd {
    private final static int VERSION_BDD = 1;
    private SQLiteDatabase bdd;
    private final MaBaseSQLite maBaseSQLite;

    public Bdd(Context context) {
        maBaseSQLite = new MaBaseSQLite(context, "Bdd", null, VERSION_BDD);
    }

    public void open() {
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public ArrayList<ModeleArtiste> getListeScene(String scene) {
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD
        Cursor cursor = bdd.rawQuery("SELECT * FROM informations WHERE scene = ? " +
                "ORDER BY jour desc, heure;", new String[]{scene});
        return cursorArtiste(cursor);
    }

    public ArrayList<ModeleArtiste> getListeFavoris() {
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD
        String query = "SELECT * FROM informations WHERE favori = 1 ORDER BY artiste;";
        Cursor cursor = bdd.rawQuery(query,  null);
        return cursorArtiste(cursor);
    }

    public ArrayList<ModeleArtiste> getListeArtistes() {
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD
        Cursor cursor = bdd.rawQuery("SELECT * FROM informations ORDER BY artiste;",
                null);
        return cursorArtiste(cursor);
    }

    public ArrayList<ModeleArtiste> getListeArtistesFilter(String recherche) {
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD
        Cursor cursor = bdd.rawQuery("SELECT * FROM informations " +
                        "WHERE artiste like \"%" + recherche + "%\" " +
                        "OR  jour like \"%" + recherche + "%\" " +
                        "OR  scene like \"%" + recherche + "%\" " +
                        "ORDER BY artiste;",
                null);
        return cursorArtiste(cursor);
    }

    public ArrayList<ModeleArtiste> getArtiste(String artiste) {
        Cursor cursor = bdd.rawQuery("SELECT * FROM informations WHERE artiste = ? ORDER BY artiste",
                new String[]{artiste});
        return cursorArtiste(cursor);
    }

    public ArrayList<ModeleArtiste> cursorArtiste(Cursor cursor) {
        ArrayList<ModeleArtiste> modeleArtisteInfo = new ArrayList<>();
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();
        do {
            ModeleArtiste modeleArtiste = new ModeleArtiste();
            modeleArtiste.setArtiste(cursor.getString(0));
            modeleArtiste.setTexte(cursor.getString(1));
            modeleArtiste.setWeb(cursor.getString(2));
            modeleArtiste.setImage(cursor.getString(3));
            modeleArtiste.setDate(cursor.getString(4));
            modeleArtiste.setJour(cursor.getString(5));
            modeleArtiste.setHeure(cursor.getString(6));
            modeleArtiste.setDuree(cursor.getInt(7));
            modeleArtiste.setPlace(cursor.getString(8));
            modeleArtiste.setScene(cursor.getString(9));
            modeleArtiste.setFavori(cursor.getInt(10));
            modeleArtiste.setParticipe(cursor.getInt(11));
            modeleArtiste.setEventId(cursor.getInt(12));
            modeleArtisteInfo.add(modeleArtiste);
        } while (cursor.moveToNext());
        return modeleArtisteInfo;
    }

    public void insertArtiste(String[] data) {
        ContentValues values = new ContentValues();
        values.put("artiste", data[0]);
        values.put("texte", data[1]);
        values.put("web", data[2]);
        values.put("image", data[3]);
        values.put("jour", data[4]);
        values.put("heure", data[5]);
        values.put("scene", data[6]);
        values.put("duree", data[7]);
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert("artistes", null, values);
    }

    public void deleteTable() {
        bdd.execSQL("DELETE FROM artistes");
    }

    public void insertFavori(String artiste) {
        ContentValues values = new ContentValues();
        values.put("artiste", artiste);
        values.put("favori", 1);
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert("favoris", null, values);
    }

    public void deleteFavori(String artiste) {
        bdd.execSQL("DELETE FROM favoris WHERE artiste = \"" + artiste + "\";");
    }

    public void insertParticipe(String artiste, long id) {
        ContentValues values = new ContentValues();
        values.put("artiste", artiste);
        values.put("participe", 1);
        values.put("eventId", id);
        //on insère l'objet dans la BDD via le ContentValues
        bdd.insert("participes", null, values);
    }

    public void deleteParticipe(String artiste, long eventId) {
        bdd.execSQL("DELETE FROM participes WHERE  artiste = \"" + artiste + "\" AND eventId = " + eventId + ";");
    }

}