package com.example.festival2.bdd;

public class ModeleArtiste {

    private String artiste;
    private String texte;
    private String web;
    private String image;
    private String date;
    private String jour;
    private String heure;
    private String place;
    private String scene;
    private int duree;
    private int favori;
    private int participe;
    private int eventId;

    public ModeleArtiste(String artiste, String texte, String web, String image,
                         String date, String jour, String heure, int duree, String place, String scene, int favori, int participe, int eventId) {
        this.artiste = artiste;
        this.texte = texte;
        this.web = web;
        this.image = image;
        this.date = date;
        this.jour = jour;
        this.heure = heure;
        this.duree = duree;
        this.place = place;
        this.scene = scene;
        this.favori = favori;
        this.participe = participe;
        this.eventId = eventId;
    }

    public ModeleArtiste() { }

    public String getArtiste() { return artiste; }
    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) { this.image = image;}

    public String getTexte() {
        return texte;
    }
    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getWeb() {
        return web;
    }
    public void setWeb(String site) {
        this.web = site;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) { this.date = date;}

    public String getJour() { return jour; }
    public void setJour(String jour) { this.jour = jour;}

    public String getHeure() {
        return heure;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getScene() { return scene; }
    public void setScene(String scene) { this.scene = scene; }

    public int getFavori() { return favori; }
    public void setFavori(int favori) {
        this.favori = favori;
    }

    public int getParticipe() { return participe; }
    public void setParticipe(int participe) {
        this.participe = participe;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) {this.eventId = eventId; }
}