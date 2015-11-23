package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

import static android.widget.Toast.LENGTH_LONG;

public class ChessActivity extends AppCompatActivity {

    public static final String DEFAULT_DURATION_INFO = "Tempo padrão de 60 min.";
    public static final String TIME_FINISH = "Acabou o tempo :(";
    public static final int DEFAULT_DURATION = 60;
    public static final String WHITE_PLAYER_SHOULD_START = "Jogador Branco deve começar.";

    private Button buttonPlayer1;
    private Button buttonPlayer2;
    private ChessTimer white;
    private ChessTimer black;
    private int playerTurn = -1;
    private int chessTime;
    private Button button;
    private boolean firstTurnWhite = false;
    private boolean firstTurnBlack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recupera os componentes
        buttonPlayer1 = (Button) findViewById(R.id.buttonPlayer1);
        buttonPlayer2 = (Button) findViewById(R.id.buttonPlayer2);

        //Verifica se existe tempo selecionado, senão utiliza o tempo padrão
        if (getIntent().hasExtra("chessTime")) {
            chessTime = (int) getIntent().getSerializableExtra("chessTime") * 60000;
        } else {
            Toast.makeText(this, DEFAULT_DURATION_INFO, Toast.LENGTH_SHORT).show();
            chessTime = DEFAULT_DURATION * 60000;
        }
        buttonPlayer1.setText(formatTime(chessTime));
        buttonPlayer2.setText(formatTime(chessTime));
        white = new ChessTimer(chessTime);
        black =new ChessTimer(chessTime);
    }

    //Formata o tempo para string
    private String formatTime(int chessTime) {
        String min = String.format("%02d", chessTime / 60000);
        int seg = (chessTime % 60000) / 1000;
        return String.format("%s:%02d", min, seg);
    }

    //Jogador clica para parar o tempo
    public void onPlayerClick(View view) {
        if (playerTurn == -1) {
            playerTurn = view.equals(buttonPlayer1) ? 1 : 2;
            //Alerta informando que quem deve começar é o jogador branco
            if (playerTurn != 2) {
                playerTurn = -1;
                Toast.makeText(this, WHITE_PLAYER_SHOULD_START, Toast.LENGTH_SHORT).show();
            } else {
                white.start((Button) view);
                firstTurnWhite = true;
            }
        } else {
            //jogador cuja não é a vez clicou no botão
            if ((view.equals(buttonPlayer2) && playerTurn == 1) || (view.equals(buttonPlayer1) && playerTurn == 2)) {
                return;
            }
            change();

            //Marca a primeira vez que o botao foi abe
            if (firstTurnWhite && !firstTurnBlack) {
                firstTurnBlack = true;
            }
        }
    }

    /**
     * Inverte os jogadores
     */
    private void change() {
        playerTurn = playerTurn == 2 ? 1 : 2;
        String[] split;
        int milliUntilFinished;

        //Verifica qual o jogador para recuperar o tempo restante do botão
        if (playerTurn == 1) {
            split = buttonPlayer1.getText().toString().split(":");
        } else {
            split = buttonPlayer2.getText().toString().split(":");
        }

        //se já foi a primeira vez que o botão foi apertado. calcula o tempo restante
        if (firstTurnWhite && firstTurnBlack) {
            milliUntilFinished = Integer.valueOf(split[0]) * 60000 + Integer.valueOf(split[1]) * 1000;
        } else {//se não, usa o tempo default
            milliUntilFinished = chessTime;
        }

        //Se for o jogador preto quem clicou
        if (playerTurn == 1) {
            white.cancel();
            black = new ChessTimer(milliUntilFinished);
            black.start(playerTurn == 1 ? buttonPlayer1 : buttonPlayer2);
        } else {
            black.cancel();
            white = new ChessTimer(milliUntilFinished);
            white.start(playerTurn == 1 ? buttonPlayer1 : buttonPlayer2);
        }
    }

    //Mostra a mensagem de tempo acabado
    private void showMessage() {
        Toast.makeText(this, TIME_FINISH, LENGTH_LONG).show();
    }

    private class ChessTimer extends CountDownTimer {
        private Button buttonPrint;

        public ChessTimer(final int chessTime) {
            super(chessTime, 1000);
        }

        public void start(Button buttonPrint) {
            this.buttonPrint = buttonPrint;
            super.start();
        }

        public void onTick(long millisUntilFinished) {
            buttonPrint.setText(formatTime((int) millisUntilFinished));
        }

        @Override
        public void onFinish() {
            showMessage();

        }
    }

}