package com.example.egzaminkosc

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Kosc(
    var czyDostepny: Boolean,
    var nazwa: ImageView,
    var liczbaOczek: Int = 0
    ){

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rzutPrzycisk = findViewById<Button>(R.id.rzut_button)
        val kosc1 = findViewById<ImageView>(R.id.kosc1)
        val kosc2 = findViewById<ImageView>(R.id.kosc2)
        val kosc3 = findViewById<ImageView>(R.id.kosc3)
        val kosc4 = findViewById<ImageView>(R.id.kosc4)
        val kosc5 = findViewById<ImageView>(R.id.kosc5)
        val pokazSume = findViewById<TextView>(R.id.pokazSume)

        val listaObrazkow = arrayOf(
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six)

        val listaObiektowKosc = arrayOf(
            Kosc(true, kosc1, ),
            Kosc(true, kosc2, ),
            Kosc(true, kosc3, ),
            Kosc(true, kosc4, ),
            Kosc(true, kosc5, ),
        )

        //val listaKosci = arrayOf(kosc1, kosc2, kosc3, kosc4, kosc5)

        rzutPrzycisk.setOnClickListener {

            var suma = 0

            for (i in (0..4)){

                if(!listaObiektowKosc[i].czyDostepny) {
                    suma += listaObiektowKosc[i].liczbaOczek
                }else{
                    val losowaLiczba = (1..6).random()
                    listaObiektowKosc[i].liczbaOczek = losowaLiczba
                    suma += losowaLiczba
                    listaObiektowKosc[i].nazwa.setImageResource(listaObrazkow[losowaLiczba-1]) //indeksy tablicy -1
                }

            }
            pokazSume.text = suma.toString()
        }

        fun ukryjKosc(index: Int){
            if(listaObiektowKosc[index - 1].czyDostepny){
                listaObiektowKosc[index - 1].czyDostepny = false
                listaObiektowKosc[index - 1].nazwa.alpha = 0.5f
            }else{
                listaObiektowKosc[index - 1].czyDostepny = true
                listaObiektowKosc[index - 1].nazwa.alpha = 1f
            }
        }

        kosc1.setOnClickListener {ukryjKosc(1)}
        kosc2.setOnClickListener {ukryjKosc(2)}
        kosc3.setOnClickListener {ukryjKosc(3)}
        kosc4.setOnClickListener {ukryjKosc(4)}
        kosc5.setOnClickListener {ukryjKosc(5)}

    }
}