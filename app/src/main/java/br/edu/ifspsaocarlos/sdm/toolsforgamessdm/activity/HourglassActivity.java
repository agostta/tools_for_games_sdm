package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class HourglassActivity extends AppCompatActivity {

    private Button botaoStart;
    private CountDownTimer timer;
    private EditText txtDuration;
    private TextView lblContagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourglass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
  */
        botaoStart = (Button)findViewById(R.id.buttonStart);
        txtDuration = (EditText)findViewById(R.id.txtDuracao);
        lblContagem = (TextView)findViewById(R.id.lblContagem);
    }

    public void onClickStart(View view){

        Integer duracao = 0;
        try {
            duracao = Integer.valueOf(txtDuration.getText().toString());
        }
        catch (NumberFormatException e){
            lblContagem.setText("Informe uma quantidade de segundos válida.");
            return;
        }

        if(timer != null)
            timer.cancel();

        timer = new CountDownTimer(duracao*1000, 1000) {

            public void onTick(long millisUntilFinished) {

                String min = String.format("%02d", millisUntilFinished/60000);

                int seg = (int)( (millisUntilFinished%60000)/1000);

                lblContagem.setText("Segundos restante: " +min+":"+String.format("%02d",seg));
            }
            public void onFinish() {
                lblContagem.setText("Acabou!");
            }
        };
        timer.start();
    }

}
