package lv.dium.riskclient;

public class riskArea {
    private Integer x;
    private Integer y;

    private Integer baseX;
    private Integer baseY;

    private Float colorA;
    private Float colorB;
    private Float colorC;
    private int isBoom = 0;
    private int[] boomEffect;

    float secondsSinceLastCheck = 0f;

    public riskArea(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.baseX = x;
        this.baseY = y;

        boomEffect = new int[12];
        boomEffect[0] = 0;
        boomEffect[1] = -2;
        boomEffect[2] = -1;
        boomEffect[3] = 0;
        boomEffect[4] = 1;
        boomEffect[5] = 2;
        boomEffect[6] = 1;
        boomEffect[7] = 0;
        boomEffect[8] = -1;
        boomEffect[9] = 0;
        boomEffect[10] = 1;
        boomEffect[11] = 0;

        mayBeNewColor();
    }

    public Integer getY() {
        return y;
    }
    public Integer getX() {
        return x;
    }
    public Float getColorA() {
        return colorA;
    }

    public Float getColorB() {
        return colorB;
    }

    public Float getColorC() {
        return colorC;
    }

    public void mayBeNewColor() {
        if(colorA == null || colorB == null || colorC == null){
            setRandomColor();
        }
        else if(riskclient.getRandomNumberInRange(0, 150) == 1){
            setRandomColor();
        }
    }

    private void setRandomColor() {
        this.colorA = (float) riskclient.getRandomNumberInRange(0, 255);
        this.colorB = (float) riskclient.getRandomNumberInRange(0, 255);
        this.colorC = (float) riskclient.getRandomNumberInRange(0, 255);
    }

    public void doBoom(){
        isBoom = 1;
    }

    public void tick(float secondsPassed){

        secondsSinceLastCheck += secondsPassed;

        if(isBoom > 0){
            x = baseX + boomEffect[isBoom];

            isBoom++;
            if(isBoom>10){
                isBoom = 0;
            }
        }
        else if(secondsSinceLastCheck>1){
            if(riskclient.getRandomNumberInRange(0, 100) == 1){
                System.out.println(secondsSinceLastCheck+"s - doing boom");
                doBoom();
            }
            secondsSinceLastCheck = 0f;
        }
    }
}
