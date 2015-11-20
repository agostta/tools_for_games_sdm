package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class ChessActivity extends AppCompatActivity {

    public static final String DEFAULT_DURATION_INFO = "Tempo padrão de 2 min.";

    private Button buttonPlayer1;
    private Button buttonPlayer2;
    private ChessTimer timer;
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

        //Verifica se existe tempo selecionado, senão utiliza o tempo padrão
        if (getIntent().hasExtra("chessTime")){
            int chessTime = (int) getIntent().getSerializableExtra("chessTime");
            chessTime = chessTime*1000;
            timer = new ChessTimer(chessTime);
        }else {
            Toast.makeText(this, DEFAULT_DURATION_INFO, Toast.LENGTH_SHORT).show();
            timer = new ChessTimer();
        }
    }

    
    public void onPlayerCLick(View view){
        if(playerTurn == -1){
            clearButtons();
            playerTurn = view.equals(buttonPlayer1) ? 1 : 2;
            timer.start((Button)view);
        }
        else{

            //jogador cuja não é a vez clicou no botão
            if( (view.equals(buttonPlayer2) && playerTurn == 1) || (view.equals(buttonPlayer1) && playerTurn == 2)){
                return;
            }

           change();
        }
    }

    /**
     *
     * Limpa o texto dos botões
     */
    private void clearButtons(){
        buttonPlayer1.setText("");
        buttonPlayer2.setText("");
    }

    /**
     *
     * Inverte os jogadores
     *
     */
    private void change(){
        playerTurn = playerTurn == 1 ? 2 : 1;
        timer.cancel();
        clearButtons();

        timer.start(playerTurn == 1 ? buttonPlayer1 : buttonPlayer2);
    }

    private class ChessTimer extends CountDownTimer{
        private final static int DEFAULT_DURATION = 120000; //2min
        private Button buttonPrint;

        public ChessTimer() {
            super(DEFAULT_DURATION, 1000);
        }

        public ChessTimer(final int chessTime) {
            super(chessTime, 1000);
        }

        public void start(Button buttonPrint){
            this.buttonPrint = buttonPrint;
            super.start();
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

