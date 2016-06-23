import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.Random;


public class GameProcess {



    // метод отрисовки карты если
    // visibleShip = true то рисуем карту с кораблями
    // visibleShip = false то рисуем карту без кораблей (маскируем их)
    public static void drawMap(Map map, boolean visibleShip){
        if(visibleShip)System.out.println("Ваша карта");
        else System.out.println("Карта соперника");
        char[] ch = {'a','b','c','d','e','f','g','h','i','j'};
        System.out.print("    ");
        for(int i=0;i<map.getSizeMap()-2;i++) System.out.print(ch[i]+" ");
        System.out.println();
        for(int i=1;i<map.getSizeMap()-1;i++){
            if(i<map.getSizeMap()-2)System.out.print(i + "   ");
            else System.out.print(i + "  ");
            for(int j=1;j<map.getSizeMap()-1;j++){
                if(visibleShip)System.out.print(map.getMassivMap(i,j) + " ");
                else {
                    if(map.getMassivMap(i,j) == Map.SHIPDECK) System.out.print(Map.WATER + " ");
                    else System.out.print(map.getMassivMap(i,j) + " ");
                }
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

        if (map.getMassivMap(x, y) != Map.SHIPDECK) {
            for(int i=0;i<8;i++) {
                if (map.getMassivMap(x + arroundCoord[i][0], y + arroundCoord[i][1]) != Map.SHIPDECK) {
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
                        map.setMassivMap(Map.SHIPDECK, ships.get(i).getShipCoordX(j), ships.get(i).getShipCoordY(j));
                        result = true;
                    }
                } else result = false;
            }while(!result);
        }
    }

    //Метод обработки выстрела возвращает:
    // 1 - если попал
    // 2 - если попал и корабль убит
    // 3 - если промах
    // 4 - если уже попадал по этому кораблю ранее
    // 5 - все корабли убиты ПОБЕДА!
    public static int fire(ArrayList<Ship> listShips, Map map, int x, int y){
        int resultFire = 3;
        int numDeckDestr = 0;
        int numShipDestr = 0;
        for(int i=0;i<listShips.size();i++){
            for(int j=0;j<listShips.get(i).getNumberOfDecks();j++) {
                //проверяем подбили ли палубу
                if (listShips.get(i).getShipCoordX(j) == x && listShips.get(i).getShipCoordY(j) == y){
                    if(map.getMassivMap(x,y) == Map.SHIPDECK){
                        map.setMassivMap(Map.SHIPDECKDESTROYED,x,y);
                        listShips.get(i).setShipDestroyedDeck(j,true);
                        resultFire = 1;
                        //проверяем убит ли корабль
                        for(int k=0;k<listShips.get(i).getNumberOfDecks();k++){
                            if(listShips.get(i).getShipDestroyedDeck(k)){
                                 numDeckDestr++;
                            }
                        }
                        if(listShips.get(i).getNumberOfDecks() == numDeckDestr){
                            resultFire =2;
                            listShips.get(i).setShipDestroyed(true);

                            //проверяем убиты ли все корабли
                            for(int m=0;m<listShips.size();m++) {
                                if(listShips.get(m).getShipDestroyed()) numShipDestr++;
                            }
                            if(listShips.size() == numShipDestr) resultFire = 5;


                        }
                    }else if(map.getMassivMap(x,y) == Map.SHIPDECKDESTROYED){
                        resultFire = 4;
                    }
                }
            }
        }if(resultFire == 3 )map.setMassivMap(Map.BOSS_SHOT,x,y);
        return resultFire;
    }


    public static void messageFireResult(int numMessage){
        if (numMessage == 1) System.out.println("Попал");
        if (numMessage == 2) System.out.println("Попал, корабль убит");
        if (numMessage == 3) System.out.println("Промах");
        if (numMessage == 4) System.out.println("Промах, корабль уже убит ранее");
        if (numMessage == 5) System.out.println("Победа");
    }

    // проверка попал/не попал
    public static boolean hit(int numMessage){
        if(numMessage == 1) return true;
        if(numMessage == 2) return true;
        if(numMessage == 5) return true;
        return false;
    }

    public static boolean win(int numMessage){
        if(numMessage == 5) return true;
        return false;
    }
    //метод обработки выстрела компьютера

}
