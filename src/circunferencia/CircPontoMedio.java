package circunferencia;

import nucleo.Plotador;
import nucleo.Registro;

public class CircPontoMedio implements AlgoritmoCircunferencia {

    @Override
    public String nome() {
        return "Ponto médio";
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        int x = 0;
        int y = raio;
        int d = 1 - raio;
        int passo = 1;

        SimetriaOitoPontos.plotar(p, xc, yc, x, y);

        r.passo(String.format("inicio: d_init = 1 - R = %d   ponto (%d,%d)",
                d, xc + x, yc + y));

        while (y > x) {
            int dDecisao = d;
            String escolha;

            if (d < 0) {
                d += 2 * x + 3;
                escolha = "E";
            } else {
                d += 2 * (x - y) + 5;
                y--;
                escolha = "SE";
            }
            x++;

            SimetriaOitoPontos.plotar(p, xc, yc, x, y);

            r.passo(String.format("passo %d: d = %d (%s)   x = %d, y = %d -> ponto (%d,%d)",
                    passo++, dDecisao, escolha, x, y, xc + x, yc + y));
        }
    }
}