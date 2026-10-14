package circunferencia;

import nucleo.Plotador;
import nucleo.Registro;

/**
 * Algoritmo Trigonométrico para rasterização de circunferência.
 * Equações paramétricas: x = r * cos(theta), y = r * sen(theta),
 * com theta variando de 0 até pi/4 radianos (45°).
 */
public class CircTrigonometrico implements AlgoritmoCircunferencia {

    private static final double EPS = 1e-9;

    @Override
    public String nome() {
        return "Trigonométrico";
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p) {
        desenhar(xc, yc, raio, p, null);
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        // Passo 1: r = raio; (h,k) = (xc,yc); theta = 0 a theta_end = pi/4 radianos (45°)
        // O passo i = 1.0 / raio garante arco máximo de 1 pixel por incremento, evitando falhas (gaps)
        double theta = 0.0;
        double thetaEnd = Math.PI / 4.0;
        double i = (raio > 0) ? (1.0 / raio) : thetaEnd;
        double ultimoTheta = theta;
        int passo = 1;

        // Passo 2: Teste para determinar se o círculo foi convertido (pare se theta > theta_end)
        while (theta <= thetaEnd) {
            // Passos 3 e 4: calcula x e y, arredonda e plota por simetria
            plotarPasso(p, r, xc, yc, raio, theta, passo++);

            ultimoTheta = theta;

            // Passo 5 e 6: Incremente theta = theta + i e vá para o passo 2
            theta = theta + i;
        }

        // Garante o ponto da diagonal (theta = pi/4) quando o laço terminou antes dele
        if (ultimoTheta < thetaEnd - EPS) {
            plotarPasso(p, r, xc, yc, raio, thetaEnd, passo);
        }
    }

    /** Passos 3 e 4: x = r*cos(theta), y = r*sen(theta); arredonda e plota os 8 pontos simétricos. */
    private void plotarPasso(Plotador p, Registro r, int xc, int yc, int raio, double theta, int passo) {
        double x = raio * Math.cos(theta);
        double y = raio * Math.sin(theta);

        int roundX = (int) Math.round(x);
        int roundY = (int) Math.round(y);

        SimetriaOitoPontos.plotar(p, xc, yc, roundX, roundY);

        if (r != null) {
            r.passo(String.format("passo %d: theta = %.3f rad -> ponto (%d,%d)",
                    passo, theta, xc + roundX, yc + roundY));
        }
    }
}