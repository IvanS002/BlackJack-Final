package BlackJack;
import java.util.Map;
import java.util.HashMap;
public class Card {
    private String name;
    private Integer value;

    public void setName(String name){
        this.name = name;    
    }

    public String getName(){
        return this.name;
    }

    

    public void setValue(Integer value){
        this.value = value;
    }
    public Integer getValue(Integer sum, Integer numberOfCards){
        Map<String, Integer> values  = new HashMap<String, Integer>();
        String[] namesList = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        Integer[] valuesList = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; 
        for (int i = 0; i < 13; i++){
            values.put(namesList[i], valuesList[i]);
        }
        if (sum == 10 && numberOfCards>2 && this.name == "Ace"){
            this.value = 1;
        }
        else {
            this.value = values.get(this.name);
        }
        return this.value;
    }
    public Card(String name){
        this.name = name;
    }
}
