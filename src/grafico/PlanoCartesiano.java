package grafico;

import nucleo.Config;
import nucleo.Plotador;

import java.util.Arrays;

public class PlanoCartesiano implements Plotador {

    private final int[][] cores = new int[Config.LINHAS][Config.COLUNAS];
    private int corAtual = Config.COR_FIGURA;

    public PlanoCartesiano() {
        limpar();
    }

    @Override
    public void setPixel(int x, int y) {
        if (!contem(x, y)) {
            return;
        }
        cores[Config.Y_MAX - y][x - Config.X_MIN] = corAtual;
    }

    public void definirCor(int rgb) {
        corAtual = rgb;
    }

    public boolean contem(int x, int y) {
        return x >= Config.X_MIN && x <= Config.X_MAX
                && y >= Config.Y_MIN && y <= Config.Y_MAX;
    }

    public void limpar() {
        for (int[] linha : cores) {
            Arrays.fill(linha, Config.COR_FUNDO);
        }
        desenharEixos();
    }

    public void desenharEixos() {
        for (int x = Config.X_MIN; x <= Config.X_MAX; x++) {
            cores[Config.Y_MAX][x - Config.X_MIN] = Config.COR_EIXOS;
        }
        for (int y = Config.Y_MIN; y <= Config.Y_MAX; y++) {
            cores[Config.Y_MAX - y][-Config.X_MIN] = Config.COR_EIXOS;
        }
    }

    public int cor(int coluna, int linha) {
        return cores[linha][coluna];
    }
}