package BlackJack;

import java.util.ArrayList;

public class Dealer extends Player{

    public Dealer() {
        super("Dealer");
        
        
    }
    public void initialDeal(Deck deck, Game game){
        ArrayList<Card> localCards = new ArrayList<Card>();
        for (int i = 0; i < game.getPlayers().size(); i++){
            localCards = game.getPlayers().get(i).getCards();
            for(int j = 0; j < 2; j++){
                localCards.add(deck.cards.get(0));
                deck.cards.remove(0);
            }
            game.getPlayers().get(i).setCards(localCards);
        }

        

    }


    public void dealSingleCard(Deck deck, Player player){
        ArrayList<Card> localCards = player.getCards();

        localCards.add(deck.cards.get(0));
        deck.cards.remove(0);
        player.setCards(localCards);



        
        
        



    }

    @Override
    public String formCardsInitial(){
        String result = "Dealer: X " + this.getCards().get(1).getName();
        return result;
        
    }

    



    
}