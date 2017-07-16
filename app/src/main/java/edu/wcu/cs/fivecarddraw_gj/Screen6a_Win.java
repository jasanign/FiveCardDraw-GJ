package edu.wcu.cs.fivecarddraw_gj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
/** Win Screen
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * Displays an example of the winning hand
 */
public class Screen6a_Win extends Activity {

    /**A field to store the name of the hand*/
    String handName = "";
    /**a field to store the value of the hand*/
    String handValue = "";
    /**Text views to display the name of the hand, and a description of it*/
    TextView title, description;
    /**All of the image views for the players hand*/
    ImageView card1, card2, card3, card4, card5;


    /**
     * Basic life cycle method for android, takes an intent and displays an example of the expected
     * winning hand
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen6a__win);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            // extracting the string from the extras
            handName = extras.getString("handName");
            handValue = extras.getString("handValue");
        }

        if(handValue.equals("1000")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("ace_of_spades", "king_of_spades", "queen_of_spades",
                    "jack_of_spades","ten_of_spades");
            setDescription("All cards are the same suit, run in sequence A, K, Q K, 10");
        } else if(handValue.equals("250")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("ten_of_hearts", "nine_of_hearts", "eight_of_hearts",
                    "seven_of_hearts","six_of_hearts");
            setDescription("All cards are the same suit and run in sequence");
        } else if(handValue.equals("100")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("queen_of_hearts", "queen_of_clubs", "queen_of_spades",
                    "queen_of_diamonds","duece_of_hearts");
            setDescription("Four cards  of the same rank ");
        } else if(handValue.equals("50")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("ten_of_hearts", "ten_of_spades", "three_of_hearts",
                    "three_of_spades","three_of_diamonds");
            setDescription("A pair and a three of a kind");
        } else if(handValue.equals("30")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("ace_of_clubs", "eight_of_clubs", "four_of_clubs",
                    "jack_of_clubs","five_of_clubs");
            setDescription("All cards are the same suit");
        } else if(handValue.equals("25")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("four_of_clubs", "five_of_spades", "six_of_hearts",
                    "seven_of_spades","eight_of_diamonds");
            setDescription("All cards run in sequence");
        } else if(handValue.equals("20")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("three_of_spades", "three_of_hearts", "three_of_clubs",
                    "duece_of_hearts","six_of_spades");
            setDescription("Three cards of the same rank");
        } else if(handValue.equals("10")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("king_of_clubs", "king_of_diamonds", "duece_of_hearts",
                    "duece_of_clubs","seven_of_spades");
            setDescription("Two pairs of cards");
        } else if(handValue.equals("5")){
            setTitle(handName+" - $"+handValue);
            setCards();
            setImages("ace_of_clubs", "ace_of_spades", "queen_of_hearts",
                    "ten_of_spades","nine_of_clubs");
            setDescription("Two of the same cards with a rank of 10 or more");
        }

    }

    /**
     * Sets the title (the name of the winning hand)
     * @param text The name of the winning hand
     */
    private void setTitle(String text){
        title = (TextView) findViewById(R.id.scr6a_title);
        title.setText(text);
    }

    /**
     * Displays a description of the winning hand
     * @param text The descrition of the winning hand
     */
    private void setDescription(String text){
        description = (TextView) findViewById(R.id.scr6a_description);
        description.setText(text);
    }

    /**
     * Initiallizes all of the image views for each of the cards.
     */
    private void setCards(){
        card1 = (ImageView) findViewById(R.id.win_card1);
        card2 = (ImageView) findViewById(R.id.win_card2);
        card3 = (ImageView) findViewById(R.id.win_card3);
        card4 = (ImageView) findViewById(R.id.win_card4);
        card5 = (ImageView) findViewById(R.id.win_card5);
    }

    /**
     * Sets the images for each card
     * @param card1Name The first card in the players hand
     * @param card2Name The second card in the players hand
     * @param card3Name The third card in the players hand
     * @param card4Name The fourth card in the players hand
     * @param card5Name The fifth card in the players hand
     */
    private void setImages(String card1Name, String card2Name,
                           String card3Name, String card4Name, String card5Name){
        card1.setImageResource(getResources()
                .getIdentifier(card1Name, "drawable", "edu.wcu.cs.fivecarddraw_gj"));
        card2.setImageResource(getResources()
                .getIdentifier(card2Name, "drawable", "edu.wcu.cs.fivecarddraw_gj"));
        card3.setImageResource(getResources()
                .getIdentifier(card3Name, "drawable", "edu.wcu.cs.fivecarddraw_gj"));
        card4.setImageResource(getResources()
                .getIdentifier(card4Name, "drawable", "edu.wcu.cs.fivecarddraw_gj"));
        card5.setImageResource(getResources()
                .getIdentifier(card5Name, "drawable", "edu.wcu.cs.fivecarddraw_gj"));
    }


}
