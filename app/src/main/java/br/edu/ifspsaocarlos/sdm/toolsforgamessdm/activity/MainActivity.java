package br.edu.ifspsaocarlos.sdm.toolsforgamessdm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.ifspsaocarlos.sdm.toolsforgamessdm.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Quando cria o menu de opções
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Capitura o click no item Chess no menu
        if (id == R.id.action_hourglass){
            Intent hourglass = new Intent(getApplicationContext(), HourglassActivity.class);
            startActivityForResult(hourglass, 1);
        }
        else if (id == R.id.action_chess){
            Intent chess = new Intent(getApplicationContext(), ChessActivity.class);
            startActivityForResult(chess, 1);
        }
        else if (id == R.id.action_dice){

            Intent dice = new Intent(getApplicationContext(), DiceActivity.class);
            startActivityForResult(dice, 1);

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
