package com.example.festival2.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.example.festival2.R;
import com.example.festival2.bdd.Bdd;
import com.example.festival2.ui.groupes.Groupes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Json extends AppCompatActivity {

    private String[] ListeArtistes;
    private String[] DataArtistes;
    private String urlSite = "https://daviddurand.info/D228/festival/";
    private String urlListe = "https://daviddurand.info/D228/festival/liste";
    private String urlArtiste = "https://daviddurand.info/D228/festival/info/";
    Bdd bdd = new Bdd(this);
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread background = new Thread(new Runnable() {
            public void run() {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    chargementJson();
                } catch (Throwable t) {
                }
                Context context = getApplicationContext();
                Intent intent = new Intent(context, Groupes.class);
                startActivity(intent);
            }
        });
        background.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    //Chargemenet du fichier Json
    private void chargementJson(){
        try {
            ListeArtistes = jsonListeArtistes();
            bdd.open();
            bdd.deleteTable();
            bdd.close();

            for (int i=0; i < ListeArtistes.length-1; i++){
                try {
                    DataArtistes = jsonDataArtiste(ListeArtistes[i]);
                    bdd.open();
                    bdd.insertArtiste(DataArtistes);
                    bdd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Récupération de la liste des artistes depuis le json
    public String[] jsonListeArtistes() throws IOException, JSONException {

        json_string = String.valueOf(JSONObject(urlListe));
        JSONObject root = new JSONObject(json_string);
        JSONArray array = root.getJSONArray("data");

        String[] data = new String[array.length()];

        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                data[i] = array.optString(i);
            }
        }
        return data;
    }

    // Récupération le détail de l'artiste depuis le json
    public String[] jsonDataArtiste(String artiste) throws IOException, JSONException {

        String urlRecherche = urlArtiste + artiste;
        json_string = String.valueOf(JSONObject(urlRecherche));
        String [] data = new String[8];

        try {
            JSONObject root = new JSONObject(json_string);
            JSONObject sys  = root.getJSONObject("data");

            data[0] = sys.getString("artiste");
            data[1] = sys.getString("texte");
            data[2] = sys.getString("web");
            data[3] = urlSite + sys.getString("image");
            data[4] = sys.getString("jour");
            data[5] = sys.getString("heure");
            data[6] = sys.getString("scene");
            data[7] = sys.getString("time");

            JSONObject nested= root.getJSONObject("nested");
            Log.d("TAG","flag value "+nested.getBoolean("flag"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Récupération du contenu de l'url
    public String JSONObject (String url) throws MalformedURLException {
        URL myURL = new URL(url);
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;

        try
        {
            urlConn = myURL.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();
        }
        catch(Exception ex)
        {
            Log.e("App", "yourDataTask", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}