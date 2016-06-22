import java.util.ArrayList;
import java.util.Random;


public class GameProcess {

    public static final int WATER = 1;
    public static final int COAST = 0;
    public static final int SHIPDECK = 7;
    public static final int SHIPDECKDESTROYED = 9;

    //метод отрисовки карты игрока
    public static void drawMapYou(Map map){
        System.out.println("Ваша карта");
        char[] ch = {'a','b','c','d','e','f','g','h','i','j'};
        System.out.print("    ");
        for(int i=0;i<map.getSizeMap()-2;i++) System.out.print(ch[i]+" ");
        System.out.println();
        for(int i=1;i<map.getSizeMap()-1;i++){
            if(i<10)System.out.print(i + "   ");
            else System.out.print(i + "  ");
            for(int j=1;j<map.getSizeMap()-1;j++){
                System.out.print(map.getMassivMap(i,j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    //метод отрисовки карты соперника
    public static void drawMapComp(Map map){
        System.out.println("Карта соперника");
        char[] ch = {'a','b','c','d','e','f','g','h','i','j'};
        System.out.print("    ");
        for(int i=0;i<map.getSizeMap()-2;i++) System.out.print(ch[i]+" ");
        System.out.println();
        for(int i=1;i<map.getSizeMap()-1;i++){
            if(i<10)System.out.print(i + "   ");
            else System.out.print(i + "  ");
            for(int j=1;j<map.getSizeMap()-1;j++){
                if(map.getMassivMap(i,j) == SHIPDECK) System.out.print(WATER + " ");
                else System.out.print(map.getMassivMap(i,j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    //проверяем координату палубы
    public static boolean verifCoord(int x, int y, Map map) {
        final int[][] arroundCoord = { {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1} };
        boolean result = false;

        if (map.getMassivMap(x, y) != SHIPDECK) {
            for(int i=0;i<8;i++) {
                if (map.getMassivMap(x + arroundCoord[i][0], y + arroundCoord[i][1]) != SHIPDECK) {
                    result = true;
                }
                else{
                    result = false;
                    break;
                }
            }
        } else result = false;
        return result;
    }


    //проверяем координаты палуб корабля и строим его
    public static boolean veriCoordShip (int x, int y, Ship ship, Map map){
        Random random = new Random();
        int vector = random.nextInt(4);
        boolean result = false;

        for(int i=0; i<ship.getNumberOfDecks();i++){ //цикл по количеству палуб корабля

            if(vector == 0) {

                if (x + ship.getNumberOfDecks() < 11 && verifCoord(x + i, y, map)) {
                    ship.setShipCoord(i, x + i, y);
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }

            if(vector == 1) {
                if (x - ship.getNumberOfDecks() > 0 && verifCoord(x - i, y, map)) {
                    ship.setShipCoord(i, x - i, y);
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }

            if(vector == 2) {
                if (y + ship.getNumberOfDecks() < 11 && verifCoord(x, y + i, map)) {
                    ship.setShipCoord(i, x, y + i);
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }

            if(vector == 3){
                if(y - ship.getNumberOfDecks() > 0 && verifCoord(x,y-i,map)) {
                    ship.setShipCoord(i, x, y - i);
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }

        }
        return result;
    }

    //устанавливаем корабли на карте
    public static void setShips(ArrayList<Ship> ships, Map map){
        Random random = new Random();
        boolean result;
        int x,y;
        for(int i=0;i<ships.size();i++){
            result = false;
            do {
                x = random.nextInt(9)+1;
                y = random.nextInt(9)+1;
                if (veriCoordShip(x, y, ships.get(i), map)) {
                    for (int j = 0; j < ships.get(i).getNumberOfDecks(); j++) {
                        map.setMassivMap(SHIPDECK, ships.get(i).getShipCoordX(j), ships.get(i).getShipCoordY(j));
                        result = true;
                    }
                } else result = false;
            }while(!result);
        }
    }

    // метод обработки выстрела
    public static boolean fire(int x, int y, ArrayList<Ship> listShip, Map map){
        boolean hit = false;

        for(int i=0;i<listShip.size();i++){
            for(int j=0;j<listShip.get(i).getNumberOfDecks();j++) {
                if (x == listShip.get(i).getShipCoordX(j) && y == listShip.get(i).getShipCoordY(j)){
                    hit = true;
                    listShip.get(i).setShipDestroyed(j,true);
                    map.setMassivMap(SHIPDECKDESTROYED,x,y);

                    if(shipKilled(i,listShip)) System.out.println("Корабль убит");
                }
            }
        }
        return hit;
    }

    public static boolean shipKilled(int i, ArrayList<Ship> listShip){
        int deck = 0;
        //если попали в палубу корабля проверяем не подбили ли мы весь корабль
        for(int k=0;k<listShip.get(i).getNumberOfDecks();k++) {
            if (listShip.get(i).getShipDestroyed(k)){
                deck++;
            }
        }
        if(deck == listShip.get(i).getNumberOfDecks()) return true;
        return false;
    }

}
