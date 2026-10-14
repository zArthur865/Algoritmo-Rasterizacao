package circunferencia;

import nucleo.Plotador;
import nucleo.Registro;

/**
 * Algoritmo Polinomial (Equação Explícita) para rasterização de circunferência.
 * Equação explícita: y = sqrt(r² - x²), x crescendo de 0 até r / sqrt(2) (arco de 45°),
 * obtendo o restante da circunferência por simetria de 8 pontos.
 */
public class CircPolinomial implements AlgoritmoCircunferencia {

    @Override
    public String nome() {
        return "Polinomial";
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p) {
        desenhar(xc, yc, raio, p, null);
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        // Passo 1: r = raio; (h,k) = (xc,yc); x = 0; i = 1 (passo); xend = r / sqrt(2)
        double x = 0;
        double i = 1.0;
        double xend = raio / Math.sqrt(2.0);
        int passo = 1;

        // Passo 2: Teste para determinar se o círculo foi convertido (Pare se x > xend)
        while (x <= xend) {
            // Passo 3: Compute o valor da coordenada y, em que y = sqrt(R² - x²)
            double y = Math.sqrt((raio * raio) - (x * x));

            int roundX = (int) Math.round(x);
            int roundY = (int) Math.round(y);

            // Passo 4: Plotar pontos determinados por simetria utilizando Círculo_ponto(round x, round y)
            SimetriaOitoPontos.plotar(p, xc, yc, roundX, roundY);

            if (r != null) {
                r.passo(String.format("passo %d: x = %d, y = %d -> ponto (%d,%d)",
                        passo++, roundX, roundY, xc + roundX, yc + roundY));
            }

            // Passo 5 e 6: Incremente x = x + i e volte ao passo 2
            x = x + i;
        }
    }
}