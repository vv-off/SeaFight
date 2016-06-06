import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String args[]) throws IOException {
        Map yourMap = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String comand;

        do {
            comand = reader.readLine();
            Pattern p = Pattern.compile("([a-j][1-9])|[a-j]([1][0])"); //регулярное выражение для координат типа a1-j10
            Matcher m = p.matcher(comand);

            if(comand.compareTo("start")==0) {
                yourMap = new Map(11);
                ArrayList<Ship> yourShipsList = new ArrayList<Ship>();

                yourShipsList.add(new Ship(4));
                yourShipsList.add(new Ship(3));
                yourShipsList.add(new Ship(3));
                yourShipsList.add(new Ship(2));
                yourShipsList.add(new Ship(2));
                yourShipsList.add(new Ship(2));
                yourShipsList.add(new Ship(1));
                yourShipsList.add(new Ship(1));
                yourShipsList.add(new Ship(1));
                yourShipsList.add(new Ship(1));

                setShips(yourShipsList, yourMap);

                System.out.println("Создана новая игра");
                System.out.println("Введите map чтобы нарисовать поле боя и начать игру");

            }else if(comand.compareTo("map") == 0) { ///сделать чтобы можно было выполнить если уже выполнен start
                for (int i = 0; i < yourMap.getSizeMap(); i++) {
                    for (int j = 0; j < yourMap.getSizeMap(); j++) {
                        System.out.print(yourMap.getMassivMap(i, j) + " ");
                    }
                    System.out.println();
                }
            }else if(m.matches()){ //координаты для выстрела
                System.out.println("Вы ввели " + comand);

            }else if (comand.compareTo("exit") == 0) {
                System.out.println("Выхожу из игры");

            }else if(comand.compareTo("?") == 0) {
                System.out.println("Начало новой игры - start");
                System.out.println("Создать поле боя - map");
                System.out.println("Ход - A1....J10");
                System.out.println("Выход из игры - exit");
            }
            else{
                System.out.println("Введите команду");
                System.out.println("Или введите ? для справки");
            }
        }while(comand.compareTo("exit")!=0);
    }

    public static boolean verifCoord(int x, int y, Map map) { //проверяем координату палубы

        final int[][] arroundCoord = { {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1} };
        boolean result = false;

        if (map.getMassivMap(x, y) != 7) {
            for(int i=0;i<8;i++) {
                if (map.getMassivMap(x + arroundCoord[i][0], y + arroundCoord[i][1]) != 7) {
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

    public static boolean veriCoordShip (int x, int y, Ship ship, Map map){ //проверяем координаты палуб корабля и строим его
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

    public static void setShips(ArrayList<Ship> ships, Map map){ //устанавливаем корабли на карте
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
                        map.setMassivMap(7, ships.get(i).getShipCoordX(j), ships.get(i).getShipCoordY(j));
                        result = true;
                    }
                } else result = false;
            }while(!result);
        }
    }
}
