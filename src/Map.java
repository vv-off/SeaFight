import java.util.Random;

public class Map {
    private int massivMap[][];
    private int sizeMap;

    Map(int size){ //инициализируем поле боя нулями
        sizeMap = size;
        massivMap = new int[sizeMap][sizeMap];

        for(int i=0;i<sizeMap;i++){
            massivMap[i][0] = 0;
            massivMap[0][i] = 0;
        }

        for(int i=1;i<sizeMap-1;i++){
            for(int j=1;j<sizeMap-1;j++){
                massivMap[i][j]=1;
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
