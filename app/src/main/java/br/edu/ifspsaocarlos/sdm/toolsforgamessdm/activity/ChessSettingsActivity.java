package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class ChessSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Inicia acontagem apos a escolhado tempo
    public void onClickStart(View view){
        Intent chess = new Intent(getApplicationContext(), ChessActivity.class);
        chess.putExtra("chessTime", R.id.time_durtion);
        startActivityForResult(chess, 1);
    }

}
