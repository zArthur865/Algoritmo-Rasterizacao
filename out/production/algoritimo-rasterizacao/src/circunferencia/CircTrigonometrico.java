package circunferencia;

import nucleo.Plotador;
import nucleo.Registro;

public class CircTrigonometrico implements AlgoritmoCircunferencia {

    private static final double EPS = 1e-9;

    @Override
    public String nome() {
        return "Trigonométrico";
    }

    @Override
    public void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        double theta = 0.0;
        double thetaEnd = Math.PI / 4.0;
        double i = (raio > 0) ? (1.0 / raio) : thetaEnd;
        double ultimoTheta = theta;
        int passo = 1;

        while (theta <= thetaEnd) {
            plotarPasso(p, r, xc, yc, raio, theta, passo++);

            ultimoTheta = theta;

            theta = theta + i;
        }

        if (ultimoTheta < thetaEnd - EPS) {
            plotarPasso(p, r, xc, yc, raio, thetaEnd, passo);
        }
    }

    private void plotarPasso(Plotador p, Registro r, int xc, int yc, int raio, double theta, int passo) {
        double x = raio * Math.cos(theta);
        double y = raio * Math.sin(theta);

        int roundX = (int) Math.round(x);
        int roundY = (int) Math.round(y);

        SimetriaOitoPontos.plotar(p, xc, yc, roundX, roundY);

        r.passo(String.format("passo %d: theta = %.3f rad -> ponto (%d,%d)",
                passo, theta, xc + roundX, yc + roundY));
    }
}