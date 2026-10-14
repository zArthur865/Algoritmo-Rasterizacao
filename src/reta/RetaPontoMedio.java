package reta;

import nucleo.Plotador;
import nucleo.Registro;

public class RetaPontoMedio implements AlgoritmoReta{
    @Override
    public String nome() {
        return "RetaPontoMedio";
    }

    @Override
    public void drawPixel(int x1, int y1, int x2, int y2, Plotador p, Registro r) {

        TransformacaoOitante t = new TransformacaoOitante(x1, y1, x2, y2);
        int x = t.getX1();
        int y = t.getY1();
        int xFim = t.getX2();
        int yFim = t.getY2();

        int k =0;
        int dx = xFim - x;
        int dy = yFim - y;
        int d = 2*dy - dx;
        int incE = 2*dy;
        int incDE = 2*(dy-dx);

        plotar(x, y, k, d, t, p, r);

        while( x < xFim){
            k++;
            x++;
            if(d > 0){
                d += incDE;
                y++;
            }
            else{
                d += incE;
            }
            plotar(x, y, k, d, t, p, r);
        }

    }

    private void plotar(int x, int y, int k, int d, TransformacaoOitante t, Plotador p, Registro r) {
        int[] o = t.paraOriginal(x, y);
        p.setPixel(o[0], o[1]);
        r.passo("Passo " + k + ": d = " + d + "; ponto (" + o[0] + "," + o[1] + ")");
    }

}
