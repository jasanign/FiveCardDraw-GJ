package edu.wcu.cs.fivecarddraw_gj;


import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Win List Fragment
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * A fragment that displays all the possible win hands and their values.
 */
public class WinListFragment extends ListFragment {

    /**Stores the name of the hand*/
    private String handName = "";
    /**An array of all the possible win hands*/
    String[] handNames = {"Royal Flush", "Straight Flush", "4 of a kind",
            "Full house", "Flush", "Straight", "3 of a kind",
            "2 Pair", "Pair"};
    /**An array of all the hand values*/
    String[] handValues = {"1000", "250", "100", "50", "30", "25", "20", "10", "5"};
    /**A field to store an adapter for the custom list*/
    ArrayAdapter<String> myAdapter;
    /**field to store the list that we will be displaying*/
    ListView lv;


    /**
     * Basic life cycle method of a fragment, inflates the layout to be displayed
     * @param inflater layout inflater
     * @param container parent container
     * @param savedInstanceState saved instance of app
     * @return
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_win_list, container, false);
    }//end onCreateView

    /**
     * Basic fragment life cycle method
     * @param view The view that has been created
     * @param savedInstanceState saved instance of app
     */
    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {

        ListView lv = (ListView) view.findViewById(android.R.id.list);
        getListView().setAdapter(new WinListAdapter(getActivity(),handNames,handValues));
        //lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //getListView().setOnItemClickListener(this);
    }

    /**
     * Displays the win hand example for the user
     * @param l
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {

        super.onListItemClick(l, view, position, id);
        Intent screen6A = new Intent(this.getActivity(), Screen6a_Win.class);
        screen6A.putExtra("handValue", handValues[position]);
        screen6A.putExtra("handName", handNames[position]);
        startActivity(screen6A);
    }


    /**
     * Custom adapter for the list. Displays both the hand names and its corresponding value.
     */
    public class WinListAdapter extends ArrayAdapter<String> {

        /**Stores the context of the adapter*/
        Context context;
        /**Stores the names of the hands*/
        String[] hand_names;
        /**Stores the values of the hands*/
        String[] hand_values;

        /**
         * Constructor
         * @param context Context of the fragment
         * @param hand_names An array of the hand names
         * @param hand_values an array of the hand values
         */
        public WinListAdapter(Context context, String[] hand_names, String[] hand_values)
        {
            super(context, R.layout.list_item, hand_names);
            this.context = context;
            this.hand_names = hand_names;
            this.hand_values = hand_values;
        }

        /**
         *
         * @param position The position in the array
         * @param convertView The view that you are altering
         * @param parent The parent holding the views
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_item, parent, false);

            TextView handName = (TextView) rowView.findViewById(R.id.textView2);
            TextView handValues = (TextView) rowView.findViewById(R.id.textView1);

            handName.setText(hand_names[position]);
            handValues.setText(hand_values[position]);


            return rowView;
        }
    }
}
