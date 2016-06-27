package com.github.woff.SeaFigh;

public class Map {
    private int massivMap[][];
    private int sizeMap;

    public static final int WATER = 1;
    public static final int COAST = 0;
    public static final int SHIPDECK = 7;
    public static final int SHIPDECKDESTROYED = 9;
    public static final int BOSS_SHOT = 3;

    Map(int size) { //инициализируем поле боя нулями
        sizeMap = size;
        massivMap = new int[sizeMap][sizeMap];

        for (int i = 0; i < sizeMap; i++) {
            massivMap[i][0] = COAST;
            massivMap[0][i] = COAST;
        }

        for (int i = 1; i < sizeMap - 1; i++) {
            for (int j = 1; j < sizeMap - 1; j++) {
                massivMap[i][j] = WATER;
            }
        }
    }


    public int getMassivMap(int x, int y) {
        return massivMap[y][x];
    }

    public void setMassivMap(int value, int x, int y) {
        massivMap[y][x] = value;
    }


    public int getSizeMap() {
        return sizeMap;
    }
}
