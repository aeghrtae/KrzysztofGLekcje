package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*
    dla pana specjalnie wyjasnienie nazwenictwa zmiennych - MADUZALICZBE (mdl), MAMALALICZBE (mml) ,MAZNAKCPECJALNY (mzsp).
    i btw cos bylo tam w zadaniu ze ma sie na biezaco aktualizowac a nie widzialem jak tamci
    to zrobili to wygugałem i mam aktualizowanie na biezaco ( tamte 3 na dole cuski to sa jakies
    funkcje ktore musze wywolac zeby TextWatcher dzialal, jak probowalem wywalic beforeTextChanged i afterTextChanged to mi blad wywalalo
    wiec musi byc chociazby puste, z obserwacji tyle!!  - smiszne bo samo jak sie watchera zrobi to sie dodaji toto.

    tak wiem ze sp skaluje sie wzgledem ustawien uzytkownika i dp wzgledem ekranu
    */

    EditText inputImie, inputNazwisko, inputGmail, inputHaslo;
    Button btnRej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputImie = findViewById(R.id.imie);
        inputNazwisko = findViewById(R.id.nazwisko);
        inputGmail = findViewById(R.id.email);
        inputHaslo = findViewById(R.id.haslo);
        btnRej = findViewById(R.id.btnRejestruj);

        inputHaslo.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String haslo = s.toString();

                if (haslo.length() < 8) {
                    inputHaslo.setError("Haslo musi mieć conajmniej 8 znakow");
                } else {
                    boolean mdl = false;
                    boolean mml = false;
                    boolean mzsp = false;

                    for (int i = 0; i < haslo.length(); i++) {
                        char lit = haslo.charAt(i);
                        if (Character.isUpperCase(lit)) {
                            mdl = true;
                        } else if (Character.isLowerCase(lit)) {
                            mml = true;
                        } else if (!Character.isLetterOrDigit(lit)) {
                            mzsp = true;
                        };
                    }


                    if (!mdl) {
                        inputHaslo.setError("Dodaj duza litere ciapo");
                    } else if (!mml) {
                        inputHaslo.setError("MALE TEZ MUSZA BYC");
                    } else if (!mzsp) {
                        inputHaslo.setError("znak specjalny nubie");
                    } else {
                        inputHaslo.setError(null);
                    }
                }
            }

            public void afterTextChanged(Editable s) {}
        });



        btnRej.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String imie = inputImie.getText().toString().trim();
                String nazwisko = inputNazwisko.getText().toString().trim();
                String email = inputGmail.getText().toString().trim();
                String haslo = inputHaslo.getText().toString().trim();

                if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Uzupelnij wszystkie pola", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!email.contains("@") || !email.contains(".")) {
                    Toast.makeText(MainActivity.this, "Podaj poprawwny adres email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (haslo.length() < 8 || inputHaslo.getError() != null) {
                    Toast.makeText(MainActivity.this, "Popraw błędy whasle", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "Dane sa poprawne.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}