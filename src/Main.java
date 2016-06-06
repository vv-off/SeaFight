import java.util.Random;

public class Main {


    public static void main(String args[]) {

        Random random = new Random();
        boolean result;
        int x,y;

        Map yourMap = new Map(11);

        Ship ship4 = new Ship(4);
        Ship ship3_1 = new Ship(3);
        Ship ship3_2 = new Ship(3);
        Ship ship2_1 = new Ship(2);
        Ship ship2_2 = new Ship(2);
        Ship ship2_3 = new Ship(2);
        Ship ship1_1 = new Ship(1);
        Ship ship1_2 = new Ship(1);
        Ship ship1_3 = new Ship(1);
        Ship ship1_4 = new Ship(1);


        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship4, yourMap)) {
                for (int i = 0; i < ship4.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship4.getShipCoordX(i), ship4.getShipCoordY(i));
                    result = true;
                }
            } else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship3_1, yourMap)) {
                for (int i = 0; i < ship3_1.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship3_1.getShipCoordX(i), ship3_1.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship3_2, yourMap)) {
                for (int i = 0; i < ship3_2.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship3_2.getShipCoordX(i), ship3_2.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship2_1, yourMap)) {
                for (int i = 0; i < ship2_1.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship2_1.getShipCoordX(i), ship2_1.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship2_2, yourMap)) {
                for (int i = 0; i < ship2_2.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship2_2.getShipCoordX(i), ship2_2.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship2_3, yourMap)) {
                for (int i = 0; i < ship2_3.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship2_3.getShipCoordX(i), ship2_3.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship1_1, yourMap)) {
                for (int i = 0; i < ship1_1.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship1_1.getShipCoordX(i), ship1_1.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship1_2, yourMap)) {
                for (int i = 0; i < ship1_2.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship1_2.getShipCoordX(i), ship1_2.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship1_3, yourMap)) {
                for (int i = 0; i < ship1_3.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship1_3.getShipCoordX(i), ship1_3.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);

        result = false;
        do {
            x = random.nextInt(9)+1;
            y = random.nextInt(9)+1;
            if (veriCoordShip(x, y, ship1_4, yourMap)) {
                for (int i = 0; i < ship1_4.getNumberOfDecks(); i++) {
                    yourMap.setMassivMap(7, ship1_4.getShipCoordX(i), ship1_4.getShipCoordY(i));
                    result = true;
                }
            }else result = false;
        }while(!result);







        for (int i = 0; i < yourMap.getSizeMap(); i++) {
            for (int j = 0; j < yourMap.getSizeMap(); j++) {
                System.out.print(yourMap.getMassivMap(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static boolean verifCoord(int x, int y, Map map) {

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

    public static boolean veriCoordShip (int x, int y, Ship ship, Map map){
        Random random = new Random();
        int vector = random.nextInt(4);
        boolean result = false;

        for(int i=0; i<ship.getNumberOfDecks();i++){ //цикл по количеству палуб корабля

                    if(vector == 0) {

                        if (x + ship.getNumberOfDecks() < 11 && verifCoord(x + i, y, map)) {
                            ship.setShipCoord(i, x + i, y);  // т.к. x+i то корабли распологаются только по вертикали
                            result = true;
                        } else {
                            result = false;
                            break;
                        }
                    }

                    if(vector == 1) {
                        if (x - ship.getNumberOfDecks() > 0 && verifCoord(x - i, y, map)) {
                            ship.setShipCoord(i, x - i, y);  // т.к. x+i то корабли распологаются только по вертикали
                            result = true;
                        } else {
                            result = false;
                            break;
                        }
                    }

                    if(vector == 2) {
                        if (y + ship.getNumberOfDecks() < 11 && verifCoord(x, y + i, map)) {
                            ship.setShipCoord(i, x, y + i);  // т.к. x+i то корабли распологаются только по вертикали
                            result = true;
                        } else {
                            result = false;
                            break;
                        }
                    }

                    if(vector == 3){
                            if(y - ship.getNumberOfDecks() > 0 && verifCoord(x,y-i,map)) {
                                ship.setShipCoord(i, x, y - i);  // т.к. x+i то корабли распологаются только по вертикали
                                result = true;
                            } else {
                                result = false;
                                break;
                            }
                    }

            }
        return result;
    }
}
