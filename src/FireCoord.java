
public class FireCoord {
    private String[][] massFireCoord;
    FireCoord(){
        massFireCoord = new String[10][10];
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][0] = "a"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][1] = "b"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][2] = "c"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][3] = "d"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][4] = "e"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][5] = "f"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][6] = "g"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][7] = "h"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][8] = "i"+j;
        }
        for(int i=0;i<10;i++){
            int j = i+1;
            massFireCoord[i][9] = "j"+j;
        }
    }


    public String getMassFireCoord(int i, int j) {
        return massFireCoord[i][j];
    }
}
