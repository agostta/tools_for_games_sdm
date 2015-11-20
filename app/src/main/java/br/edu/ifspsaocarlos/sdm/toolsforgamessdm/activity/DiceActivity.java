package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.ImageView;
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

        //Mensagem informativa para o usuário
        Toast.makeText(this, "Toque na tela para rolar os dados.", Toast.LENGTH_SHORT).show();
    }

    //Eventos de Touch na tela irá rolar os dados
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Evento de Touch Down
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            playSound();
            int number = rollDices();
            ImageView viewById = (ImageView) findViewById(R.id.dice_view);

            //Escolhe uma face do dado de acordo com o range de 1 a 6
            switch (number) {
                case 1:
                    viewById.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    viewById.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    viewById.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    viewById.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    viewById.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    viewById.setImageResource(R.drawable.dice6);
                    break;
                default:
                    Toast.makeText(this, "Algo errado! Toque novamente :(", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        if(event.getAction()==MotionEvent.ACTION_UP){
            Toast.makeText(this, "Toque na tela para rolar os dados.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    //Sortear um numero de 1 a 6
    public int rollDices() {
        Random rn = new Random();
        int maximum = 6;
        int minimum = 1;
        int n = maximum - minimum + 1;
        int i = rn.nextInt(n);
        int randomNum = minimum + i;
        return randomNum;
    }

    //Tocar som dos dados Rolando
    private void playSound() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll_dices);
        mp.start();
    }



}
