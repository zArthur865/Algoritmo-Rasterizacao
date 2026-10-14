package circunferencia;

import nucleo.Plotador;

/**
 * Simetria de Ordem 8 para traçado de circunferências.
 */
public class SimetriaOitoPontos {

    /**
     * Dado um ponto (x, y) calculado no arco de 45 graus relativo à origem,
     * plota os 8 pontos simétricos somando o centro (xc, yc) a cada um.
     * Segue exatamente a ordem do procedimento Círculo_ponto / ponto_circulo.
     */
    public static void plotar(Plotador p, int xc, int yc, int x, int y) {
        p.setPixel(xc + x, yc + y);   // writepixel(x, y)
        p.setPixel(xc + y, yc + x);   // writepixel(y, x)
        p.setPixel(xc + y, yc - x);   // writepixel(y, -x)
        p.setPixel(xc + x, yc - y);   // writepixel(x, -y)
        p.setPixel(xc - x, yc - y);   // writepixel(-x, -y)
        p.setPixel(xc - y, yc - x);   // writepixel(-y, -x)
        p.setPixel(xc - y, yc + x);   // writepixel(-y, x)
        p.setPixel(xc - x, yc + y);   // writepixel(-x, y)
    }
}