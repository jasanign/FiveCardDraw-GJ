package edu.wcu.cs.fivecarddraw_gj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * gamefragment class
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * Fragment that creates and handles game
 */
public class gameFragment extends Fragment implements View.OnClickListener{


    /** Text view for player name */
    TextView name;
    /** field for player name */
    static String playerName = "Player";
    /** Constant for deck size*/
    private static int DECK_SIZE = 52;
    /** Field for deck of cards*/
    public static Card[] deck =  new Card[DECK_SIZE];
    /** field for card's index in deck */
    public static int deckindex = 0;
    /** Field for holding a 5 card hand*/
    Hand playerHand = new Hand();
    /** field for holding images associated with cards*/
    ImageView[] deltCardImages = new ImageView[5];
    /**buttons to hold/throw cards in game */
    ArrayList<Button> deltCardChoices = new ArrayList<>(5);
    /**field to check game status, initialization is done once*/
    boolean gameInitialzed= false;
    /**Field to store the total number of games played*/
    int numOfGamesPlayed = 0;
    /**Field to store the total money earned*/
    int money = 0;
    /**Field used to tell if the game is a new game,*/
    boolean newGame = true;

    /**
     *  Default constructor
     */
    public gameFragment() {
        // Required empty public constructor
    }


    /**
     * onCreateView lifecycle method
     * @param inflater layout inflater
     * @param container parent container
     * @param savedInstanceState saved instance of app
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }


    /**
     * onViewCreated lifecycle method
     * this method calls all the helper method to build the game components
     * @param view view from on create
     * @param savedInstanceState saved instance of app
     */
    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        name = (TextView) view.findViewById(R.id.players_name);
        name.setText(playerName + "'s hand");
        prepareImageViews();
        storeButtonPointers();
        hideCardsAndButtons();
        Button deal = (Button) view.findViewById(R.id.deal);
        deal.setOnClickListener(this);
        Button menu = (Button) view.findViewById(R.id.menu);
        menu.setOnClickListener(this);
        Button winList = (Button) view.findViewById(R.id.win_hands_play_button);
        winList.setOnClickListener(this);
    }

    /**
     * helper method initDeck
     * this method creates the deck
     */
    private void createDeck(){
            deckindex = 0;
            int cardNum = 0;
            for (Suit suit :  Suit.values()) {
                for (Face face : Face.values()) {
                    deck[cardNum++] = new Card(face, suit);
                }
            }
            shuffle();
        }

    /**
     * helper method shuffle
     * this method shuffles the cards of the deck
     */
    public void shuffle() {
        Random r = new Random();
        Random r2 = new Random();
        for (int i = 0; i < DECK_SIZE; i++)
        {
            int rnd = r.nextInt(DECK_SIZE);
            int rnd2 = r2.nextInt(DECK_SIZE);
            Card swap = deck[rnd];
            deck[rnd] = deck[rnd2];
            deck[rnd2] = swap;
        }
    }

    /**
     * helper method drawFive
     * this method draws five cards from the deck
     */
    private void drawFive(){
            for(int i = 0; i < 5; i++){
                replace(i);
            }
        }

    /**
     * helper method replace
     * this method replaces the card and its related image at the given index
     * @param handIndex card's index in a hand
     */
        private void replace(int handIndex){
            playerHand.setCard(handIndex, deck[++deckindex]);
            Card temp = deck[deckindex];
            String cardImageName = temp.toString().toLowerCase();
            cardImageName = cardImageName.replace(" ", "_");
            int id = this.getContext().getResources().getIdentifier(cardImageName,"drawable",
                    getActivity().getPackageName());
                deltCardImages[handIndex].setImageDrawable(getResources().getDrawable(id));
        }

    /**
     * helper method storeButtonPointers
     * this method attaches the logical buttons with graphical buttons
     * and add them to the button array for card choice
     */
        private void storeButtonPointers(){
            deltCardChoices.add((Button)getView().findViewById(R.id.delt_card_choice_1));
            deltCardChoices.add((Button)getView().findViewById(R.id.delt_card_choice_2));
            deltCardChoices.add((Button)getView().findViewById(R.id.delt_card_choice_3));
            deltCardChoices.add((Button)getView().findViewById(R.id.delt_card_choice_4));
            deltCardChoices.add((Button)getView().findViewById(R.id.delt_card_choice_5));
            for(int i = 0; i < deltCardChoices.size(); i++){
                deltCardChoices.get(i).setOnClickListener(this);
            }
        }

    /**
     * helper method prepareImageView
     * this method attaches logical imageViews with graphical imageviews
     * and sets the initial back side image on each
     */
        private void prepareImageViews(){
            deltCardImages[0] = (ImageView) getView().findViewById(R.id.delt_card_1);
            deltCardImages[1] = (ImageView) getView().findViewById(R.id.delt_card_2);
            deltCardImages[2] = (ImageView) getView().findViewById(R.id.delt_card_3);
            deltCardImages[3] = (ImageView) getView().findViewById(R.id.delt_card_4);
            deltCardImages[4] = (ImageView) getView().findViewById(R.id.delt_card_5);
            for(int i = 0;i < deltCardImages.length;i++){
                deltCardImages[i].setImageDrawable(getResources().getDrawable(R.drawable.back));
            }

        }

    /**
     * After the cards are thrown, add any money won and increment the number of games played
     */
    private void endOfTurn (){
        TextView moneyLabel = (TextView) getView().findViewById(R.id.bank_total);
        TextView gamesPlayed = (TextView) getView().findViewById(R.id.num_of_hands_played);
        try{
            gamesPlayed.setText(String.valueOf(++numOfGamesPlayed));
            money+=playerHand.getHandType().getWinHandVal();
            moneyLabel.setText(String.valueOf(money));
        }catch(NumberFormatException e){
            System.out.println("Something went wrong endOfTurn");
        }

    }

    /**
     * Hides the cards and buttons
     */
    private void hideCardsAndButtons(){
        for(int i = 0;i < deltCardImages.length;i++){
            deltCardImages[i].setVisibility(View.INVISIBLE);
        }
        for(int i = 0; i < deltCardChoices.size(); i++){
            deltCardChoices.get(i).setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Sets both the cards and buttons to visible.
     */
    private void displayCardsAndButtons(){
        for(int i = 0;i < deltCardImages.length;i++){
            deltCardImages[i].setVisibility(View.VISIBLE);
        }
        for(int i = 0; i < deltCardChoices.size(); i++){
            deltCardChoices.get(i).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Sets all the cards so that they are face down, and makes the buttons invisible.
     */
    private void placeFaceDown(){
        for(int i = 0;i < deltCardImages.length;i++){
            deltCardImages[i].setImageDrawable(getResources().getDrawable(R.drawable.back));
        }
        for(int i = 0; i < deltCardChoices.size(); i++){
            deltCardChoices.get(i).setVisibility(View.INVISIBLE);
            deltCardChoices.get(i).setText(R.string.Deal);
        }
    }

    /**
     * Makes sure all the buttons are visible, and displays the five cards in the player's hand
     */
    private void placeFaceUp(){
        for(int i = 0;i < deltCardImages.length;i++){
            Card temp = playerHand.getCard(i);
            String cardImageName = temp.toString().toLowerCase();
            cardImageName = cardImageName.replace(" ", "_");
            int id = this.getContext().getResources().getIdentifier(cardImageName,"drawable",
                    getActivity().getPackageName());
            deltCardImages[i].setImageDrawable(getResources().getDrawable(id));
        }
        for(int i = 0; i < deltCardChoices.size(); i++){
            deltCardChoices.get(i).setVisibility(View.VISIBLE);
        }
    }


    /**
     * The backbone of the gameFragment. Prompts the user after each game informing them if they
     * have won or not. Displays cards, hide cards, and deals cards.
     * @param v The "Deal" button view
     */
    private void processDeal(Button v) {
        boolean justPressed = false;
        if (!gameInitialzed) {
            displayCardsAndButtons();
            createDeck();
            drawFive();
            gameInitialzed = true;
            justPressed = true;
        }

        if (newGame && !justPressed) {
            if (v.getId() == getView().findViewById(R.id.deal).getId()) {
                for (int i = 0; i < deltCardChoices.size(); i++)
                    if (deltCardChoices.get(i).getText() == getResources().getText(R.string.Throw))
                        replace(i);
                buildDialog();
                newGame = false;

            }
        } else if (!newGame && !justPressed){
                createDeck();
                drawFive();
                placeFaceUp();
                newGame = true;
            }
    }

    /**
     * Builds a dialog that will inform  the user if they won or lost.
     * Sets the cards face down after the user clicks "OK".
     */
    private void buildDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(playerHand.getHandType().ordinal() > WinHand.HIGHCARD.ordinal()) {
            builder.setMessage(R.string.DialogMessageWin);
        }else{
            builder.setMessage(R.string.DialogMessageLose);
        }

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                endOfTurn();
                placeFaceDown();
            }
        });
        builder.show();
    }

    /**
     * onClick method
     * this mrthod makes the button clickable and handles the actions related to clicks
     * @param v button view handler
     */
    @Override
    public void onClick(View v) {
        Button temp = (Button) v;
        if(temp.getId() == getView().findViewById(R.id.deal).getId()){
            processDeal(temp);
        }

        if(deltCardChoices.contains(temp)) {
            if (temp.getText() == getResources().getString(R.string.Deal)) {
                temp.setText(getResources().getText(R.string.Throw));
            } else {
                temp.setText(getResources().getText(R.string.Deal));
            }
        }

        if(v.getId() == getView().findViewById(R.id.menu).getId()){
            getActivity().startActivity(new Intent(getActivity(), Screen2_Menu.class));
        }else if(v.getId() == getView().findViewById(R.id.win_hands_play_button).getId()){
            getActivity().startActivity(new Intent(getActivity(), Screen6_WinList.class));
        }


    }
}
