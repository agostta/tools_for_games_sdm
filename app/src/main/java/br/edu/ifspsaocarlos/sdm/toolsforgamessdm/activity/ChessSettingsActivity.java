package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class ChessSettingsActivity extends AppCompatActivity {

    public static final String MESSAGE_INFO = "Informe o tempo em segundos, depois toque em Continuar.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, MESSAGE_INFO, Toast.LENGTH_LONG).show();
    }

    //Inicia acontagem apos a escolhado tempo
    public void onClickStart(View view){
        Intent chess = new Intent(getApplicationContext(), ChessActivity.class);
        EditText time = (EditText) findViewById(R.id.time_durtion);

        //Caso não seja informado nenhum valor. Serausado o DEFAULT
        if(!time.getText().toString().isEmpty()) {
            chess.putExtra("chessTime", Integer.valueOf(time.getText().toString()));
        }
        startActivityForResult(chess, 1);
    }

}
