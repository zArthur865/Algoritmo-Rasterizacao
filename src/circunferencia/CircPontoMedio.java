package circunferencia;

import nucleo.Plotador;
import nucleo.Registro;

/**
 * Algoritmo do Ponto Médio (Bresenham) para conversão por varredura da circunferência.
 * Utiliza apenas aritmética inteira: d inicial = 1 - R; escolhe entre E (d < 0) e SE (d >= 0)
 * no 2º octante de (0, R) até y <= x, espelhando por simetria de 8 pontos.
 */
public class CircPontoMedio implements AlgoritmoCircunferencia {

    @Override
    public String nome() {
        return "Ponto médio";
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p) {
        desenhar(xc, yc, raio, p, null);
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        // Inicialização inteira: x = 0; y = raio; d = 1 - raio
        int x = 0;
        int y = raio;
        int d = 1 - raio;
        int passo = 1;

        // Ponto inicial (0, R) e seus simétricos
        SimetriaOitoPontos.plotar(p, xc, yc, x, y);

        if (r != null) {
            r.passo(String.format("inicio: d_init = 1 - R = %d   ponto (%d,%d)",
                    d, xc + x, yc + y));
        }

        // Laço principal: while (y > x)
        while (y > x) {
            int dDecisao = d;   // d usado na decisão
            String escolha;

            if (d < 0) {
                /* escolhe E: d += 2 * x + 3 */
                d += 2 * x + 3;
                escolha = "E";
            } else {
                /* escolhe SE: d += 2 * (x - y) + 5; y--; */
                d += 2 * (x - y) + 5;
                y--;
                escolha = "SE";
            }
            x++;

            // Ponto_circulo(x, y, valor)
            SimetriaOitoPontos.plotar(p, xc, yc, x, y);

            if (r != null) {
                r.passo(String.format("passo %d: d = %d (%s)   x = %d, y = %d -> ponto (%d,%d)",
                        passo++, dDecisao, escolha, x, y, xc + x, yc + y));
            }
        }
    }
}