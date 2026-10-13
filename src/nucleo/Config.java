package nucleo;

public final class Config {

    private Config() {
    }

    public static final int X_MIN = -40;
    public static final int X_MAX = 40;
    public static final int Y_MIN = -30;
    public static final int Y_MAX = 30;

    public static final int COLUNAS = X_MAX - X_MIN + 1;
    public static final int LINHAS = Y_MAX - Y_MIN + 1;

    public static final int ESCALA = 10;

    public static final int LARGURA_TELA = COLUNAS * ESCALA;
    public static final int ALTURA_TELA = LINHAS * ESCALA;

    public static final int COR_FUNDO = 0xFFFFFF;
    public static final int COR_EIXOS = 0x000000;
    public static final int COR_FIGURA = 0xD32F2F;
}