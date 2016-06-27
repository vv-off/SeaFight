package com.github.woff.SeaFigh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    public static void main(String args[]) throws IOException {

        GameCommands commands = new GameCommands();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Random random = new Random();

        ArrayList<Ship> yourShipList = null;
        ArrayList<Ship> compShipList = null;
        Map yourMap = null;
        Map compMap = null;
        int fireResult;
        int fireCompX = 0;
        int fireCompY = 0;
        int fireVectorComp;
        boolean trueFire;


        do {
            System.out.println("Введите start для начала игры");
            commands.setCommand(reader.readLine());
            start:
            if (commands.getCommand().compareTo("start") == 0) {
                System.out.println("Игра началась");
                //код начала игры
                yourShipList = new ArrayList<Ship>();
                compShipList = new ArrayList<Ship>();
                int[] decks = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
                for (int i = 0; i < 10; i++) {
                    yourShipList.add(new Ship(decks[i]));
                    compShipList.add(new Ship(decks[i]));
                }
                do {
                    System.out.println("Введите map чтобы создать карту");
                    commands.setCommand(reader.readLine());
                    if (commands.getCommand().compareTo("start") == 0) break start;
                    if (commands.getCommand().compareTo("map") == 0) {
                        System.out.println("Карта боя создана");
                        //код создания карты
                        compMap = new Map(12);
                        yourMap = new Map(12);
                        //устанавливаем корабли на созданной карте и рисуем карту
                        GameProcess.setShips(yourShipList, yourMap);
                        GameProcess.setShips(compShipList, compMap);
                        //рисуем карты компьютера и игрока
                        GameProcess.drawMap(yourMap, true);
                        GameProcess.drawMap(compMap, false);
                        //цикл выстрелов игроков
                        do {
                            System.out.println("Введите коорднаты выстрела: ");
                            commands.setCommand(reader.readLine());
                            if (commands.getCommand().compareTo("start") == 0) break start;
                            if (commands.fireCoord()) {
                                System.out.println("Огонь");
                                //код боя
                                fireResult = GameProcess.fire(compShipList, compMap, commands.getXcoordFire(), commands.getYcoordFire());
                                GameProcess.messageFireResult(fireResult);
                                if (GameProcess.hit(fireResult)) {
                                    //проверка на победу игрока
                                    if (GameProcess.win(fireResult)) {
                                        commands.setCommand("exit");
                                    }
                                } else {
                                    do {
                                        //код стрельбы компьютера
                                        if (fireCompX == 0 && fireCompY == 0) {
                                            fireCompX = random.nextInt(10) + 1;
                                            fireCompY = random.nextInt(10) + 1;
                                        }
                                        fireResult = GameProcess.fire(yourShipList, yourMap, fireCompX, fireCompY);
                                        System.out.println("x = " + fireCompX);
                                        System.out.println("y = " + fireCompY);
                                        GameProcess.messageFireResult(fireResult);
                                        if (GameProcess.hit(fireResult)) {
                                            //пытаемся добить корабль
                                            do {
                                                fireVectorComp = random.nextInt(4);
                                                if (GameProcess.nextCoordFire(fireCompX, fireCompY, fireVectorComp)) {
                                                    switch (fireVectorComp) {
                                                        case 0:
                                                            fireCompY--;
                                                            break;
                                                        case 1:
                                                            fireCompX++;
                                                            break;
                                                        case 2:
                                                            fireCompY++;
                                                            break;
                                                        case 3:
                                                            fireCompX--;
                                                            break;
                                                    }
                                                    trueFire = true;

                                                } else {
                                                    trueFire = false;

                                                }
                                            } while (!trueFire);
                                        }
                                    } while (GameProcess.hit(fireResult));
                                    fireCompX = 0;
                                    fireCompY = 0;
                                }
                            }
                            GameProcess.drawMap(yourMap, true);
                            GameProcess.drawMap(compMap, false);
                        } while (commands.getCommand().compareTo("exit") != 0);
                    }
                } while (commands.getCommand().compareTo("exit") != 0);
            }
        } while (commands.getCommand().compareTo("exit") != 0);
    }
}



