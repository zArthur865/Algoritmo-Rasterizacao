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
        int x,y, k =0;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2*dy - dx;
        int incE = 2*dy;
        int incDE = 2*(dy-dx);

        if (x1 > x2){
            x = x2;
            y = y2;
            x2 = x1;
            y2 = y1;
        }
        else{
            x = x1;
            y = y1;
        }
        p.setPixel(x,y);
        r.passo("Passo " + k + ": x = "+ x +"; y = "+ y +"; ponto ("+ x +","+ y + ")");
        while( x < x2){
            k++;
            x++;
            if(d > 0){
                d += incDE;
                y++;
            }
            else{
                d += incE;
            }
            p.setPixel(x,y);
            r.passo("Passo " + k + ": x = "+ x +"; y = "+y +"; ponto ("+ x +","+ y + ")");
        }

    }

}
