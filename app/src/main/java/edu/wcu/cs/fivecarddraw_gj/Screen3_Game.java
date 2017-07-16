package edu.wcu.cs.fivecarddraw_gj;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
/**
 * game activity.
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * Depending on the size, only displays the game fragment. On larger screens, displays both the
 * win list and game fragment and hides the win list button.
 */
public class Screen3_Game extends FragmentActivity {


    /**
     * Basic life cycle method, on large and extra large screens removes the main menu button.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3__game);


        View view = findViewById(R.id.play_fragment);

        if (((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) || (Configuration.SCREENLAYOUT_SIZE_MASK ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE)){
            Button b = (Button)view.findViewById(R.id.win_hands_play_button);
            b.setVisibility(View.INVISIBLE);
        }

    }
}
