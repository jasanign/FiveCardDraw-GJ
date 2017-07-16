package edu.wcu.cs.fivecarddraw_gj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Splash  Screen Activity
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * Displays a logo for five seconds and then goes to main menu.
 */
public class Screen1_Splash extends Activity {

    /** Constant to define 5 seconds */
    private final int DELAY = 5000;
    /** Handler to apply delay */
    Handler handler;

    /**
     * Method: onCreate
     * This method displays the splash screen when the app is opened
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1__splash);
    }

    /**
     * Method: onStart
     * This method loads Screen2 after 3 seconds of splash screen
     */
    protected void onStart(){
        super.onStart();
        handler = new Handler();
        handler.postDelayed(runner, DELAY);
    }

    /**
     * Thread to make the delay possible before loading next screen
     */
    private final  Runnable runner = new Runnable() {
        @Override
        public void run() {
            nextScreen();
        }
    };

    /**
     * Helper method to load next screen
     */
    private void nextScreen(){

        Intent i = new Intent(this, edu.wcu.cs.fivecarddraw_gj.Screen2_Menu.class);
        this.startActivity(i);
    }
}
