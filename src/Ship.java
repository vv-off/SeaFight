import java.util.ArrayList;

public class Ship {
    private int numberOfDecks;
    private ArrayList<Deck> listDeck = new ArrayList();

    Ship(int numDecks){
        numberOfDecks = numDecks;
        for(int i=0;i<numberOfDecks;i++){
            listDeck.add(new Deck()); //создаём и добавляем объекты типа Deck в коллекцию
            //в итоге у нас получается корабль из коллекции палуб
        }
    }

    public void setShipCoord(int numDeck, int x, int y){
        listDeck.get(numDeck).setX(x);// установить координаты X палубы корабля
        listDeck.get(numDeck).setY(y);// установить координаты Y палубы корабля
    }

    public void setShipDestroyed(int numDeck, boolean destroyed){
        listDeck.get(numDeck).setDestroyed(destroyed);//установить признак разрушения палубы корабля
    }

    public int getShipCoordX(int numDeck){
        return listDeck.get(numDeck).getX(); //взять координату X палубы корабля
    }

    public int getShipCoordY(int numDeck){
        return listDeck.get(numDeck).getY(); //взять координату Y палубы корабля
    }

    public boolean getShipDestroyed(int numDeck){
        return  listDeck.get(numDeck).isDestroyed(); //взять признак разрушения палубы корабля
    }

    public int getNumberOfDecks(){
        return numberOfDecks; //взять количество палуб
    }

    public void setShipTrueCoord (int numDeck, boolean trueCoord){
        listDeck.get(numDeck).setTrueCoord(trueCoord);
    }

    public boolean getShipTrueCoord(int numDeck){
        return listDeck.get(numDeck).isTrueCoord();
    }


}
