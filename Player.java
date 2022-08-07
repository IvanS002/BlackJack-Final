package BlackJack;

import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private String name;
    private Integer sum;
    private ArrayList<Card> cards;
    public Player(String name){
        this.name = name;
        this.sum = 0;
        this.cards = new ArrayList<Card>();

    }

    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return this.name;
    }


    public void setSum(){
        Integer localSum = 0;
        for (Card card : this.cards){
            localSum += card.getValue(localSum, this.cards.size());
        }
        this.sum = localSum;
    }

    public Integer getSum(){
        return this.sum;
    }

    public void setCards(ArrayList<Card> c){
        this.cards = c;
    }

    public ArrayList<Card> getCards(){
        return this.cards;
    }


    public String formCardsInitial(){
        String result = this.getName() + ": " + this.getCards().get(0).getName() + " ";
        result += this.getCards().get(1).getName();
        return result;
    }

    public String formCardsMiddle() throws IOException{
        String result = "";
        for (int i = 0; i < this.getCards().size(); i++){
            result += this.getCards().get(i).getName();
            if (i!=this.getCards().size()-1){
                result += " ";
            }
        }
        return result;
    }
}
