package com.github.woff.SeaFigh;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class GameCommands {
    private String command;
    private int xCoordFire;
    private int yCoordFire;

    public int getXcoordFire() {
        return xCoordFire;
    }

    public int getYcoordFire() {
        return yCoordFire;
    }

    private char[] ch = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};


    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    //регулярное выражение для координат типа a1-j10
    public boolean fireCoord() {
        Pattern p = Pattern.compile("([a-j][1-9])|[a-j]([1][0])");
        Matcher m = p.matcher(command);

        if (m.matches()) {
            parseCommandFire();
            return true;
        }
        return false;
    }


    private void parseCommandFire() {
        String numStr = "";
        Pattern patChar = Pattern.compile("([a-j])");
        Matcher matcherChar = patChar.matcher(command);
        while (matcherChar.find()) {
            numStr = matcherChar.group();
        }
        for (int i = 0; i < ch.length; i++) {

            if (numStr.compareTo(Character.toString(ch[i])) == 0) {
                yCoordFire = i + 1;

            }
        }

        Pattern patNum = Pattern.compile("([0-9]+)");
        Matcher matcherNum = patNum.matcher(command);
        while (matcherNum.find()) {
            xCoordFire = Integer.parseInt(matcherNum.group());
        }
    }
}
