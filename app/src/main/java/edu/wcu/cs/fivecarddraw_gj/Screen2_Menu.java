package edu.wcu.cs.fivecarddraw_gj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * App: Five Card Draw Poker (Screen 2)
 * This screen provides users opportunity to Enter name, Play the the game,
 * and view hands
 * @Author: Gajjan Jasani, Samuel Avins
 * @version 10/28/2016
 * @version: 1.0
 */
public class Screen2_Menu extends Activity implements View.OnClickListener{

    /** Buttons to handle user input */
    /**Button that goes to the user input screen*/
    Button btn1;
    /**Button that goes to main menu*/
    Button btn2;
    /**Button to go to the screen that only displays the win list fragment*/
    Button btn3;

    /**
     * Method: onCreate
     * This method sets up the screen 2 on the device
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2__menu);
    }

    /**
     * Method: onStart
     * makes all 3 buttons clickable to get the user input
     */
    @Override
    protected void onStart(){
        super.onStart();
        // Connecting the xml screen 2's button elements with code
        btn1 = (Button) findViewById(R.id.scr2_btn1);
        btn2 = (Button) findViewById(R.id.scr2_btn2);
        btn3 = (Button) findViewById(R.id.scr2_btn3);
        //making those buttons to listen to user click (touch)
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    /**
     * Method: onClick
     * This method loads screen associated to the button clicked by the user
     * @param v - the clickable view (button) element that has been clicked/touched
     */
    @Override
    public void onClick(View v) {

        // casting the clicked view element to a button because we
        // know it is a button
        Button b = (Button) v;

        // Intent to navigate to the screen selected by user
        Intent selected_screen = new Intent(this, Screen7_PlayerName.class);

        if(b.getId() == R.id.scr2_btn1){
            selected_screen = new Intent(this,Screen7_PlayerName.class);
        }else if(b.getId() == R.id.scr2_btn2){
            selected_screen = new Intent(this,Screen3_Game.class);
        }else if(b.getId() == R.id.scr2_btn3){
            selected_screen = new Intent(this,Screen6_WinList.class);
        }
        this.startActivity(selected_screen); //starting the user selected activity

    }
}
