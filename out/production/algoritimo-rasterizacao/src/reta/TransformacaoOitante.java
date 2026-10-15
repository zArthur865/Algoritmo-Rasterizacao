package reta;

public class TransformacaoOitante {

    private boolean trocaXY = false;
    private boolean espelhaY = false;

    private int x1, y1, x2, y2;

    public TransformacaoOitante(int x1, int y1, int x2, int y2) {
        if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
            trocaXY = true;
            int t = x1; x1 = y1; y1 = t;
            t = x2; x2 = y2; y2 = t;
        }

        if (x1 > x2) {
            int t = x1; x1 = x2; x2 = t;
            t = y1; y1 = y2; y2 = t;
        }

        if (y2 < y1) {
            espelhaY = true;
            y1 = -y1;
            y2 = -y2;
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }

    public int[] paraOriginal(int x, int y) {
        if (espelhaY) {
            y = -y;
        }
        if (trocaXY) {
            int t = x; x = y; y = t;
        }
        return new int[]{x, y};
    }
}