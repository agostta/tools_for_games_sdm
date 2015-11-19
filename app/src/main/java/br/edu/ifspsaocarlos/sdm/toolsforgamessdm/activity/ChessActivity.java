package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class ChessActivity extends AppCompatActivity {

    private Button buttonPlayer1;
    private Button buttonPlayer2;
    private CountDownTimer timer;
    private int playerTurn = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recupera os componentes
        buttonPlayer1 = (Button)findViewById(R.id.buttonPlayer1);
        buttonPlayer2 = (Button)findViewById(R.id.buttonPlayer2);
        timer = new ChessTimer();
    }

    /**
     *
     * Inverte os jogadores
     *
     */
    private void change(){

    }

    private class ChessTimer extends CountDownTimer{
        private final static int DURATION = 120000; //2min
        private Button buttonPrint;

        public ChessTimer(){
            super(DURATION, 1000);
        }

        public void setButtonPrint(Button buttonPrint){
            this.buttonPrint = buttonPrint;
        }

        public void onTick(long millisUntilFinished) {
            String min = String.format("%02d", millisUntilFinished/60000);
            int seg = (int)( (millisUntilFinished%60000)/1000);

            buttonPrint.setText(String.format("%s:%02d", min, seg));
        }

        @Override
        public void onFinish() {
            change();
        }
    }
}

