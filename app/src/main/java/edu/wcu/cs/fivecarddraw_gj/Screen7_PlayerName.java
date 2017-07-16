package edu.wcu.cs.fivecarddraw_gj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Player name activity
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * Activity for allowing the user to input a name to be displayed on the game fragment.
 */
public class Screen7_PlayerName extends Activity implements View.OnClickListener {

    /** Button to go back to Screen  */
    Button btnOK;
    /** EditText to get the userIput */
    EditText name;
    /** String to extract name entered by user in edittext field */
    String userName;

    /**
     * Basic life cycle method, called when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7__player_name);

        btnOK = (Button) findViewById(R.id.scr7_btn1);
        btnOK.setOnClickListener(this);
    }

    /**
     * Pulls the name from a edit text to be used by the game fragment to display the users name
     * @param v The view that has been clicked.
     */
    @Override
    public void onClick(View v) {

        // casting the clicked view element to a button
        Button b = (Button) v;
        name = (EditText) findViewById(R.id.scr7_edTxt1);
        userName = name.getText().toString();

        // Intent to navigate to the screen6
        //Intent selected_screen = new Intent(this, Screen6.class);
        if(b.getId() == R.id.scr7_btn1){
            String name = userName.trim();
            if(name.length() > 0 && name.matches("^[ A-Za-z]+$")){
                //if(name.length() > 0){
                Intent screen2 = new Intent(this, Screen2_Menu.class);
                gameFragment.playerName = name;
               // screen2.putExtra("playerName", name);
                this.startActivity(screen2);

            } else {
                Toast.makeText(this.getApplicationContext(), "Player name can only contain" +
                        " letters and spaces", Toast.LENGTH_LONG).show();
            }

        }

    }
}
