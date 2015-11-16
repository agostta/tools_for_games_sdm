package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Random;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class DiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().hasExtra("face")){
            int face = (int) getIntent().getSerializableExtra("face");
            System.out.print(face);
        }
        Toast.makeText(this, "Toque na tela para rolar os dados.", Toast.LENGTH_SHORT).show();
    }

    //Eventos de Touch na tela irá rolar os dados
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        playSound();

        Intent dice = new Intent(getApplicationContext(), DiceActivity.class);
        dice.putExtra("face",rollDices());
        startActivityForResult(dice, 1);
        return true;
    }

    //Sortear um numero
    public int rollDices(){
        Random rn = new Random();
        int maximum = 6;
        int minimum = 1;
        int n = maximum - minimum + 1;
        int i = rn.nextInt() % n;
        int randomNum = minimum + i;
        return randomNum;
    }

    //Tocar som dos dados Rolando
    private void playSound(){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll_dices);
        mp.start();
    }

}
