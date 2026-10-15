package grafico;

import nucleo.Config;
import nucleo.Plotador;

import java.util.Arrays;

public class PlanoCartesiano implements Plotador {

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private int[][] cores;
    private int corAtual = Config.COR_FIGURA;

    public PlanoCartesiano() {
        limpar();
    }

    @Override
    public void setPixel(int x, int y) {
        if (!contem(x, y)) {
            return;
        }
        cores[yMax - y][x - xMin] = corAtual;
    }

    public void definirCor(int rgb) {
        corAtual = rgb;
    }

    public boolean contem(int x, int y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    public void limpar() {
        xMin = Config.X_MIN;
        xMax = Config.X_MAX;
        yMin = Config.Y_MIN;
        yMax = Config.Y_MAX;
        cores = alocar(xMin, xMax, yMin, yMax);
    }

    public void ajustarLimites(int novoXMin, int novoXMax, int novoYMin, int novoYMax) {
        novoXMin = limitar(novoXMin);
        novoXMax = limitar(novoXMax);
        novoYMin = limitar(novoYMin);
        novoYMax = limitar(novoYMax);
        if (novoXMin == xMin && novoXMax == xMax && novoYMin == yMin && novoYMax == yMax) {
            return;
        }
        int[][] novo = alocar(novoXMin, novoXMax, novoYMin, novoYMax);
        int deX = Math.max(xMin, novoXMin);
        int ateX = Math.min(xMax, novoXMax);
        int deY = Math.max(yMin, novoYMin);
        int ateY = Math.min(yMax, novoYMax);
        if (deX <= ateX) {
            for (int y = deY; y <= ateY; y++) {
                System.arraycopy(cores[yMax - y], deX - xMin, novo[novoYMax - y], deX - novoXMin, ateX - deX + 1);
            }
        }
        cores = novo;
        xMin = novoXMin;
        xMax = novoXMax;
        yMin = novoYMin;
        yMax = novoYMax;
    }

    public void ampliarPara(int minX, int maxX, int minY, int maxY) {
        ajustarLimites(Math.min(xMin, minX), Math.max(xMax, maxX), Math.min(yMin, minY), Math.max(yMax, maxY));
    }

    public int getXMin() { return xMin; }
    public int getXMax() { return xMax; }
    public int getYMin() { return yMin; }
    public int getYMax() { return yMax; }
    public int getColunas() { return xMax - xMin + 1; }
    public int getLinhas() { return yMax - yMin + 1; }

    public int cor(int coluna, int linha) {
        return cores[linha][coluna];
    }

    private static int limitar(int valor) {
        return Math.max(-Config.LIMITE_PLANO, Math.min(Config.LIMITE_PLANO, valor));
    }

    private static int[][] alocar(int minX, int maxX, int minY, int maxY) {
        int[][] novo = new int[maxY - minY + 1][maxX - minX + 1];
        for (int[] linha : novo) {
            Arrays.fill(linha, Config.COR_FUNDO);
        }
        return novo;
    }
}