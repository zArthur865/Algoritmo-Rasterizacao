package circunferencia;

import nucleo.Plotador;
import nucleo.Registro;

public class CircPolinomial implements AlgoritmoCircunferencia {

    @Override
    public String nome() {
        return "Polinomial";
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        double x = 0;
        double i = 1.0;
        int passo = 1;

        while (true) {
            double y = Math.sqrt((raio * raio) - (x * x));

            int roundX = (int) Math.round(x);
            int roundY = (int) Math.round(y);

            if (roundX > roundY) {
                break;
            }

            SimetriaOitoPontos.plotar(p, xc, yc, roundX, roundY);

            r.passo(String.format("passo %d: x = %d, y = %d -> ponto (%d,%d)",
                    passo++, roundX, roundY, xc + roundX, yc + roundY));

            x = x + i;
        }
    }
}