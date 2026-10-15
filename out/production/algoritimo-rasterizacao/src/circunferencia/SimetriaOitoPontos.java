package circunferencia;

import nucleo.Plotador;

public final class SimetriaOitoPontos {

    private SimetriaOitoPontos() {
    }

    public static void plotar(Plotador p, int xc, int yc, int x, int y) {
        p.setPixel(xc + x, yc + y);
        p.setPixel(xc + y, yc + x);
        p.setPixel(xc + y, yc - x);
        p.setPixel(xc + x, yc - y);
        p.setPixel(xc - x, yc - y);
        p.setPixel(xc - y, yc - x);
        p.setPixel(xc - y, yc + x);
        p.setPixel(xc - x, yc + y);
    }
}