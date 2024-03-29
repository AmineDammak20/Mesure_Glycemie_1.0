package com.example.dammak_mohamed_amine_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvage, tvReponse;
    private EditText etMesureGlycemie;
    private SeekBar sbAge;
    private RadioButton rbIsFasting, rbIsNotFasting;
    private Button btnConsulter;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // bundle estt utilisé pour passer des données entre les composants exp: activités
        super.onCreate(savedInstanceState); // Appel à la méthode onCreate() de la classe parente (AppCompatActivity)
        setContentView(R.layout.activity_main); //  Définition du layout de l'activité en utilisant le fichier activity_main.xml
        init(); // Initialisation des éléments de l'interface utilisateur

        // valeur de seek bar pour l'age

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // declancher lorseque on déplace le curseur du SeekBar
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { // lorsqu'on modifie le seekbar elle est appelée
                Log.i("Information","On progress change : " + progress); // affichage de la valeur dans le log
                tvage.setText("Votre âge : " + progress); // TextView est mis à jour avec la valeur sélectionnée sur le SeekBar
            }
             // Dans notre cas, on n'a besoin que de surcharger la méthode onProgressChanged():
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnConsulter.setOnClickListener(new View.OnClickListener() {  // Définition d'un listener de clic pour le bouton CONSULTER
            @Override
            public void onClick(View v) {
                calculer(); // Appel de la méthode calculer() lorsque le bouton est cliqué
            }
        });

    }

    private void init(){ // initialise les attributs de la classe MainActivity
        tvage = findViewById(R.id.tvAge);
        tvReponse = findViewById(R.id.txtReponse);
        etMesureGlycemie = findViewById(R.id.etMesureGlycemie);
        sbAge = findViewById(R.id.sbAge);
        rbIsFasting = findViewById(R.id.rbIsFasting);
        rbIsNotFasting = findViewById(R.id.rbIsNotFasting);
        btnConsulter = findViewById(R.id.btnConsulter);
    }
    private void calculer() {
        Log.i("Information","Onclick sur le bouton btnConsulter");
        int age;
        float valeur;
        boolean verifAge = false, verifValeur = false;
        if(sbAge.getProgress()!=0)
            verifAge = true;
        else
            Toast.makeText(MainActivity.this,"Veuillez saisir votre age",Toast.LENGTH_SHORT);
        if(etMesureGlycemie.getText().toString().isEmpty())
            Toast.makeText(MainActivity.this,"Veuillez saisir votre valeur mesurée",Toast.LENGTH_LONG);
        else
            verifValeur = true;
        if(verifAge && verifValeur){
            age = sbAge.getProgress();
            valeur = Float.valueOf(etMesureGlycemie.getText().toString());
            if(rbIsFasting.isChecked()){
                if(age>= 13)
                    if(valeur< 5.0)
                        tvReponse.setText("Niveau de glycémie est trop bas");
                    else if(valeur>= 5.0 && valeur <= 7.2)
                        tvReponse.setText("Niveau de glycémie est normal");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                else if(age>= 6 && age <= 12)
                    if(valeur< 5.0)
                        tvReponse.setText("Niveau de glycémie est bas");
                    else if(valeur>= 5.0 && valeur <= 10.0)
                        tvReponse.setText("Niveau de glycémie est normal");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
                else if(age < 6)
                    if(valeur< 5.5)
                        tvReponse.setText("Niveau de glycémie est bas");
                    else if(valeur>= 5.5 && valeur <= 10.0)
                        tvReponse.setText("Niveau de glycémie est normal");
                    else
                        tvReponse.setText("Niveau de glycémie est trop élevé");
            } else {
            if (valeur > 10.5)
                tvReponse.setText("Niveau de glycémie est trop élevé");
            else
                tvReponse.setText("Niveau de glycémie est normale");
        }
        }


    }
}