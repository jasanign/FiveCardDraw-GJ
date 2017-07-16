package edu.wcu.cs.fivecarddraw_gj;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Hand class
 * @author Gajjan Jasani & Samuel Avins
 * @version 10/28/2016
 * This class is helper class of Game class
 * This class creates and manipulates hand in the game
 */
public class Hand {
    /** field for number of cards in a hand*/
    public static int HAND_SIZE = 5;
    /**field for holding all cards of a hand*/
    private Card[] playerHand;
    /**field for describing the winning( best scoring) hand*/
    public WinHand best;


    /**
     * Modified constructor of Hand class
     */
    public Hand(){
        playerHand = new Card[HAND_SIZE];
    }

    /**
     * setCard method
     * This method sets all the cards of a hend object
     * @param index card's index in the hand (not deck)
     * @param newCard new card for the hand
     */
    public void setCard(int index, Card newCard){
        playerHand[index] = newCard;
    }

    /**
     * Returns a card from he plays hand at the given index
     * @param i the index
     * @return The card at the fiven index
     */
    public Card getCard(int i) {
        return playerHand[i];
    }

    /**
     * getHandType method
     * this method compares the current hands with possible winning combination
     * to detact win
     * @return returns the detected wining combinations with a related label
     */
    public WinHand getHandType(){
        if(straightFlush()){
            best = WinHand.SFLUSH;
        }
        else if(fourOfAKind()){
            best = WinHand.FOURKIND;
        }
        else if(fullHouse()){
            best = WinHand.FHOUSE;
        }
        else if(flush()){
            best = WinHand.FLUSH;
        }
        else if(straight()){
            best = WinHand.STRAIGHT;
        }
        else if(threeOfAKind()){
            best = WinHand.THREEKIND;
        }
        else if(twoPair()){
            best = WinHand.TWOPAIR;
        }
        else if(pair()){
            best = WinHand.PAIR;
        }
        else{
            best = WinHand.INVALID;
        }

        return best;
    }

    /**
     * getPair method
     * this method checks the playerHand to see if it has a single or
     * double pairs in it
     * @return highest pair
     */
    public int getPair(){
        int pair = 0;
        int matches = 0;
        int highestPair = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==2){
                pair = currentCard;
            }
            if(pair > highestPair){
                highestPair = pair;
            }
            matches = 0;

        }

        return highestPair;
    }
    /**
     * getThreeKind method
     * this method checks if the playerHand has three cards of same kind in it
     * @return 0 if no three of a kind, highest card's rank if there are three cards of same king
     */
    public int getThreeKind(){
        int toak = 0;
        int matches = 0;
        int highestPair = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==3){
                toak = currentCard;
            }
            if(toak > highestPair){
                highestPair = toak;
            }
            matches = 0;

        }

        return highestPair;
    }

    /**
     * getFourKind method
     * this method checks if the playerHand has for cards of the same kind or not
     * @return 0 if no four of a kind, highest card's rank if there is a four of a kind
     */
    public int getFourKind(){
        int foak = 0;
        int matches = 0;
        int highestPair = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==4){
                foak = currentCard;
            }
            if(foak > highestPair){
                highestPair = foak;
            }
            matches = 0;

        }

        return highestPair;
    }

    /**
     * getHighestCard method
     * this method returns the highest card in the player hand when there is no winning
     * combinations in playerhand
     * @return rank of the highest card
     */
    public Card getHighestCard(){
        Card highest = playerHand[0];
        for(int i = 1; i < playerHand.length; i++){
            if(playerHand[i].rank() >= highest.rank()){
                highest = playerHand[i];
            }
        }
        return highest;
    }

    /**
     * sraightFlush method
     * this method checks if the playerHand is a flush hand, and if it is a flush hand,
     * checks further if it is a straight too
     * @return true if straight and flush, false otherwise
     */
    private boolean straightFlush(){
        if(straight() && flush()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * straight method
     * this method checks if the playerHand is a straight combination
     * @return true if hand is a straight, false otherwise
     */
    private boolean straight(){
        int sequence=1;
        sort();
        for(int i = 0; i < playerHand.length - 1; i++){
            if(playerHand[i].rank() == playerHand[i+1].rank()){
                sequence++;
            }
        }
        if(sequence == playerHand.length){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * flush method
     * this method checks if the playerHand is a flush combination
     * @return true if the hand is a flush, false otherwise
     */
    private boolean flush(){
        int suitCount = 0;
        for(int i = 1; i < playerHand.length; i++){
            if(playerHand[i].getSuit().equals(playerHand[0].getSuit())){
                suitCount++;
            }
            else{
                return false;
            }
        }
        if(suitCount == playerHand.length){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * fourOfAKind method
     * this method checks if the playerHand has four cards of the same kind
     * @return true if there is a combinations of four of a kind, false otherwise
     */
    private boolean fourOfAKind(){
        int matches = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==4){
                return true;
            }
            matches = 0;
        }
        return false;
    }

    /**
     * threeOfAKind method
     * this method checks if the playerHand has three cards of the same kind
     * @return true if there is a combinations of three of a kind, false otherwise
     */
    private boolean threeOfAKind(){
        int matches = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==3){
                return true;
            }
            matches = 0;
        }
        return false;
    }

    /**
     * pair method
     * this method checks if the playerHand has just 2 of the same kind
     * @return true if there is a combinations of 2 of a kind, false otherwise
     */
    private boolean pair(){
        int matches = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==2){
                return true;
            }
            matches = 0;
        }
        return false;
    }

    /**
     * twoPair method
     * this method checks if the playerHand has two pairs of different kinds
     * @return true if there is a combinations of two pairs of different kind, false otherwise
     */
    private boolean twoPair(){
        int matches = 0;
        int numPair = 0;
        for(int currentCard = 0; currentCard < playerHand.length; currentCard++){
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].rank() == playerHand[currentCard].rank()){
                    matches++;
                }
            }
            if(matches==2){
                numPair++;
            }
            matches = 0;
        }
        if(numPair == 2){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * fullHouse method
     * this method checks if the playerHand has a pair and a three of a kind
     * @return true if there is a pair and a three of a kind, false otherwise
     */
    private boolean fullHouse(){
        return (pair() && threeOfAKind());
    }

    /**
     * toString method
     * this method gets each card of the hand and gets a string description of that card
     * and combines descriptions of all cards to create the description of playerHand
     * @return playerHand's description
     */
    public String toString(){
        String toPrint = "";
        for(int i = 0; i < playerHand.length; i++){
            toPrint = toPrint + i + ": " + playerHand[i].toString() + "\n";
        }

        return toPrint;
    }

    /**
     * sort method
     * this method sorts all the cards of the playerHand according to the card ranks
     */
    public void sort(){
        //Arrays.sort(playerHand, (Card u1, Card u2)->u1.compareTo(u2));
        Arrays.sort(playerHand, new Comparator<Card>() {
            @Override
            public int compare(Card u1, Card u2) {
                return u1.compareTo(u2);
            }
        });
    }

}
