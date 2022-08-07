package BlackJack;
import  java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.lang.Thread;



public class Game {
    private Integer numberOfPlayers;
    private ArrayList<Player> players;




    public Game(){
        this.numberOfPlayers = 0;
        this.players = new ArrayList<Player>();
    }



    public void setNumberOfPlayers(Integer n){
        this.numberOfPlayers = n;
    }

    public Integer getNumberOfPlayers(){
        return this.numberOfPlayers;
    }

    public void setPlayers(ArrayList<String> list){
        for (String line : list){
            this.players.add(new Player(line));
        }
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }



    public void showMenu() throws IOException{
        framesSingle("Welcome to the game!", "/");
        framesSingle("GAME MENU", "/");
        ArrayList<String> block = new ArrayList<String>();
        block.add("same list of players - 1");
        block.add("remove some players - 2");
        block.add("add some new players - 3");
        block.add("new list of players - 4");
        framesBlock(block, "/");


    }



    public Boolean checkIfLastSessionExists(String path) throws IOException{
        File file = new File(path);
        Boolean check = file.createNewFile();
        if ((!check && file.length() == 0) || check){
            return false;
        }
        else{
            return true;
        }

    }



    public void saveCurrentSession(String path) throws IOException{
        BufferedWriter fw = new BufferedWriter(new FileWriter(path));
        for (Player player : this.players){
            fw.write(player.getName() + "\n");
        }
        fw.close();
    }





    public static void SleepTime(Integer ms){
        try{
            Thread.sleep(ms);

        }
        catch (InterruptedException e){
            System.out.println(e);
        }        
    }



    public static void framesBlock(ArrayList<String> block, String symb) throws IOException{
        Integer maxLength = 0;

        for (String element : block){
            if (element.length()>maxLength){
                maxLength = element.length();
            }
        }
        for (int i = 0; i < maxLength+10; i++){
            System.out.print(symb);
        }
        System.out.print("\n" + symb + symb);

        for (int i = 0; i < maxLength+6; i++){
            System.out.print(" ");
        }

        System.out.println(symb + symb + "");

        
        for (int i = 0; i < block.size(); i++){
            System.out.print(symb + symb + "   " + block.get(i));
            for (int j = 0; j < maxLength-block.get(i).length(); j++){
                System.out.print(" ");
            }
            System.out.println("   " + symb + symb);
        }

        
        System.out.print(symb + symb);

        for (int i = 0; i < maxLength+6; i++){
            System.out.print(" ");
        }

        System.out.println(symb + symb);
        


        for (int i = 0; i < maxLength+10; i++){
            System.out.print(symb);
        }

        System.out.println();



    }




    public static void framesSingle(String string, String symb) throws IOException{
        Integer l = string.length();
        for (int i = 0; i < l+10; i++){
            System.out.print(symb);
        }
        System.out.print("\n" + symb + symb);

        for (int i = 0; i < l+6; i++){
            System.out.print(" ");
        }

        System.out.println(symb + symb);

        
        System.out.print(symb + symb + "   " + string + "   " + symb + symb);
        
        System.out.print("\n" + symb + symb);

        for (int i = 0; i < l+6; i++){
            System.out.print(" ");
        }

        System.out.println(symb + symb);
        


        for (int i = 0; i < l+10; i++){
            System.out.print(symb);
        }

        System.out.println("\n");



    }





    public void playersAreSame(String path) throws IOException{
        BufferedReader fr = new BufferedReader(new FileReader(path));
        String line = fr.readLine();
        ArrayList<String> names = new ArrayList<String>();
        while (line!=null){
            names.add(line);
            line = fr.readLine();
        }
        this.setPlayers(names);
        this.setNumberOfPlayers(this.players.size());
        fr.close();
    }




    public void removePlayers(BufferedReader r, String path) throws IOException{

    BufferedReader fr = new BufferedReader(new FileReader(path));
    System.out.println("Please, input the names of the players you wnat to remove. When you will have all of them input, press 'Enter' one more time: ");
    String line = fr.readLine();
    ArrayList<String> names = new ArrayList<String>();
    while (line!=null){
        names.add(line);
        line = fr.readLine();
    }
    String inputName = r.readLine();
    if (inputName.equals("")){
        framesSingle("You haven't removed any player. List of players in the current game is the same.", "/");
        this.playersAreSame(path);
    }
    else{

        while(!inputName.equals("")){
            for (int i = 0; i<names.size(); i++){
                if (names.get(i).equals(inputName)){
                    names.remove(i);
                    break;
                }
                else if (i==names.size()-1){
                    System.out.println("No player with the name " + inputName + " found. Make sure to input the name correctly: ");
                }

            }
            if (names.size()<2){
                System.out.println("You have removed all the players of the previous session. Create new ones, please: ");
                SleepTime(800);
                this.createNewPlayers(r);
                break;

            }
            inputName = r.readLine();
        }
        this.setPlayers(names);
        this.setNumberOfPlayers(this.players.size());

    }

    fr.close();

    }






    public void addPlayers(BufferedReader r, String path) throws IOException{
        BufferedReader fr = new BufferedReader(new FileReader(path));
        System.out.println("Please, input the names of the players you wnat to add. When you will have all of them input, press 'Enter' one more time: ");

        String line = fr.readLine();
        ArrayList<String> names = new ArrayList<String>();
        while (line!=null){
            names.add(line);
            line = fr.readLine();

        }

        String inputName = r.readLine();
        Boolean check = false;


        if (inputName.equals("")){
            framesSingle("You haven't added any new players. List of players in the current game is the same.", "/");
            this.playersAreSame(path);
        }
        else{
            while(!inputName.equals("")){
                if (names.size()<8){
                    for (String n : names){
                        if (inputName.equals(n)){
                            check = true;
                            break;
                        }
                    }
                    if (check){
                        System.out.println("Player with this name already exists. Please, use different name: ");
                        check = false;
                        inputName = r.readLine();
                    }
                    else{
                        names.add(inputName);
                        inputName = r.readLine();
                    }
                }
                else{
                    framesSingle("Maximum number of players reached.", "/");
                    break;
                }

            }
            this.setPlayers(names);
            this.setNumberOfPlayers(this.players.size());
        }

        fr.close();
    }




    public void createNewPlayers(BufferedReader r) throws IOException{
        System.out.println("Please, input the names of the new players (2-7). When you will have all of them input, press 'Enter' one more time: ");
        String inputName = r.readLine();
        Boolean check1 = false;
        Boolean check0 = false;
        Integer c = 0;
        ArrayList<String> names = new ArrayList<String>();

        while (check0 == false && c < 7){
            if (c<2 && inputName.equals("")){
                System.out.println("Input the name of at list 2 players, please: ");
                inputName = r.readLine();
            }

            else if (!inputName.equals("") && c<7){
                c++;
                for (String n : names){
                    if (n.equals(inputName)){
                        check1 = true;
                        break;
                    }
                }
                if (check1==false){
                    names.add(inputName);
                    if (c!=7){inputName = r.readLine();
                    }
                }
                else{
                    System.out.println("Player with this name already exists. Please, use different name: ");
                    inputName = r.readLine();
                }
                check1 = false;
            }
            else{
                check0 = true;
            }
        }
        this.setPlayers(names);
        this.setNumberOfPlayers(c);
    }

    public Boolean askIfDealAnotherOne(BufferedReader r, Player player) throws IOException{
        Boolean check0 = false;
        Boolean result = false;
        System.out.print("Dealer - to " + player.getName() + ": Your current cards are: " );
        System.out.print(player.formCardsMiddle()); 
        System.out.println(". Deal one more card - 1 or stop now - 2 ?");
        String answer = r.readLine();

        while (check0==false){
            if (!answer.equals("1") && !answer.equals("2")){
                framesSingle("Wrong number or symbol.", "/");
                answer = r.readLine();
            }
            else if (answer.equals("1")){
                check0 = true;
                result = true;
                
            }
            else{
                check0 = true;
            }
        }
        return result;
        
    }



    public void defineWinnerAndPrint() throws IOException{
        Integer a = 0;
        ArrayList<String> winnerNames = new ArrayList<String>();
        for(Player player : this.getPlayers()){
            if(player.getSum() > a && (player.getSum() <= 21 || (player.getSum()==22 && player.getCards().size()==2))){
                a = player.getSum();
                winnerNames.clear();
                winnerNames.add(player.getName());    
            }
            else if (player.getSum()==a && (player.getSum() <= 21 || (player.getSum()==22 && player.getCards().size()==2))){
                winnerNames.add(player.getName());    


            }
            
        }
        String finalString = "";
        for(int i = 0; i < winnerNames.size(); i++){
            finalString += winnerNames.get(i);
            if(i<winnerNames.size()-1){
                finalString += " and ";
            }
        }
        finalString += " wins the game with the sum of " + a + " points!";
        framesSingle(finalString, "/");
    }




    


    






    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        Game game = new Game();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

//      !!!
//         
//      next line requires different path (and) filename for program to launch successfully
//    

        String filePath = "C:\\Users\\ivans\\OneDrive\\Документы\\java programs\\BlackJack\\PreviousPlayersNames.txt";
    
        if (game.checkIfLastSessionExists(filePath) == true){
            game.showMenu();
            String[] inputNumbers = {"1", "2", "3", "4"};
            Boolean check0 = false;
            String s = "";
            while(check0 == false){
                System.out.println("Please, input the number according to game menu: ");
                s = consoleReader.readLine();
                if (!s.equals("") && !s.equals(" ") && Arrays.toString(inputNumbers).contains(s)){
                    if (s.equals("1")){
                        game.playersAreSame(filePath);
                    }
                    else if (s.equals("2")){
                        game.removePlayers(consoleReader, filePath);
                    }
                    else if (s.equals("3")){
                        game.addPlayers(consoleReader, filePath);
                    }
                    else if (s.equals("4")){
                        game.createNewPlayers(consoleReader);
                    }
                    check0 = true;
                }
                else{
                    framesSingle("Wrong number or symbol", "/");
                }
            }
        }
        else{
            game.createNewPlayers(consoleReader);
        }
        game.saveCurrentSession(filePath);

        Deck deck = new Deck();
        deck.shuffleDeck();
        Dealer dealer = new Dealer();
        game.players.add(dealer);
        System.out.println();
        SleepTime(500);
        framesSingle("Starting the game!", "/");
        SleepTime(500);
        dealer.initialDeal(deck, game);
        ArrayList<String> cardsInitial = new ArrayList<String>();
        for (Player p : game.players){
            p.setSum();
            cardsInitial.add(p.formCardsInitial());
        }
        framesBlock(cardsInitial, "/");
        Boolean answer = true;
        for (int i = 0; i < game.numberOfPlayers; i++){
            if (game.players.get(i).getSum()!=21 && game.players.get(i).getSum()!=22){
                while(answer){
                    if (game.players.get(i).getSum()>21){
                        String string = game.players.get(i).getName() + ", your current cards are: ";
                        string += game.players.get(i).formCardsMiddle();
                        string += ". This bet looses (Sum " + game.players.get(i).getSum() + ").";
                        framesSingle(string, "/");
                        break;
                    }
                    else if (game.players.get(i).getSum()==21){
                        String string = game.players.get(i).getName() + ", your current cards are: ";
                        string += game.players.get(i).formCardsMiddle();
                        string += ". Sum is 21!";
                        framesSingle(string, "/");
                        break;
                    }
                    answer = game.askIfDealAnotherOne(consoleReader, game.players.get(i));
                    if (answer){
                        dealer.dealSingleCard(deck, game.players.get(i));
                        game.players.get(i).setSum();
                    }
                    
                }
                answer = true;
            }
            else if (game.players.get(i).getSum()==21){
                framesSingle(game.players.get(i).getName() + " has BlackJack!", "/");
            }
            else{
                framesSingle(game.players.get(i).getName() + " has Golden BlackJack!", "/");

            }
            
        }
        while(dealer.getSum()<17){
            dealer.dealSingleCard(deck, dealer);
            dealer.setSum();
        }
        SleepTime(850);
        if (dealer.getSum() > 21){
            framesSingle("Cards of " + dealer.getName() + ": " + dealer.formCardsMiddle() + ". Dealer looses (Sum " + dealer.getSum() + ").", "/");
            
            
        }
        else{
            framesSingle("Cards of " + dealer.getName() + ": " + dealer.formCardsMiddle(), "/");
            System.out.println();
            game.defineWinnerAndPrint();
        }
        
        


    }
}
