package BlackJack;
import java.util.*;;

public class Deck {
    ArrayList<Card> cards;




    public Deck(){
        String[] cardNames = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        this.cards = new ArrayList<Card>();
        for (String n : cardNames){
            for (int i = 0; i < 4; i++){
                this.cards.add(new Card(n));
            }
        }

    }


    public void setCards(ArrayList<Card> cards){
        this.cards = cards;
    }


    public ArrayList<Card> getCards(){
        return this.cards;
    }




    public void shuffleDeck(){
        Collections.shuffle(this.cards);
        
    }


    

    public void printDeck(){
        for (Card e : cards){
            System.out.print(e.getName());
        }
    }
}
