package edu.wcu.cs.fivecarddraw_gj;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
/**
 * Win List Activity
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * Displays only the win list fragment
 */
public class Screen6_WinList extends Activity {

    /**
     * Basic life activity cycle method.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen6__win_list);

    }
}
