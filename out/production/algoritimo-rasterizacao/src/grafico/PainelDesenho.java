package grafico;

import nucleo.Config;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class PainelDesenho extends JPanel {

    private static final int ESPACO_MINIMO_ROTULO = 45;
    private static final int[] BASES = {1, 2, 5};

    private final PlanoCartesiano plano;
    private boolean pixelReal;

    public PainelDesenho(PlanoCartesiano plano) {
        this.plano = plano;
        setPreferredSize(new Dimension(Config.LARGURA_TELA, Config.ALTURA_TELA));
        setMinimumSize(new Dimension(Config.COLUNAS * 4, Config.LINHAS * 4));
        setBackground(new Color(Config.COR_FUNDO));
    }

    public void atualizar() {
        repaint();
    }

    public void setPixelReal(boolean valor) {
        pixelReal = valor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int colunas = plano.getColunas();
        int linhas = plano.getLinhas();
        double escala = pixelReal ? 1.0 : Math.min((double) getWidth() / colunas, (double) getHeight() / linhas);
        double largura = escala * colunas;
        double altura = escala * linhas;
        int ox = (int) ((getWidth() - largura) / 2);
        int oy = (int) ((getHeight() - altura) / 2);
        Transformacao t = new Transformacao(plano, ox, oy, largura, altura);

        for (int linha = 0; linha < linhas; linha++) {
            int y = plano.getYMax() - linha;
            int topo = t.mundoParaDispositivoY(y + 0.5);
            int base = t.mundoParaDispositivoY(y - 0.5);
            for (int coluna = 0; coluna < colunas; coluna++) {
                int cor = plano.cor(coluna, linha);
                if (cor != Config.COR_FUNDO) {
                    int x = plano.getXMin() + coluna;
                    int esquerda = t.mundoParaDispositivoX(x - 0.5);
                    int direita = t.mundoParaDispositivoX(x + 0.5);
                    g.setColor(new Color(cor));
                    g.fillRect(esquerda, topo, Math.max(1, direita - esquerda), Math.max(1, base - topo));
                }
            }
        }

        int xEixo = centroX(t, 0);
        int yEixo = centroY(t, 0);
        int fimX = ox + (int) Math.round(largura);
        int fimY = oy + (int) Math.round(altura);
        g.setColor(new Color(Config.COR_EIXOS));
        g.drawLine(ox, yEixo, fimX - 1, yEixo);
        g.drawLine(xEixo, oy, xEixo, fimY - 1);
        g.drawRect(ox, oy, fimX - ox - 1, fimY - oy - 1);

        int passo = escolherPasso(escala);
        g.setColor(Color.GRAY);
        for (int x = primeiroMultiplo(plano.getXMin(), passo); x <= plano.getXMax(); x += passo) {
            if (x != 0) {
                int px = centroX(t, x);
                g.drawLine(px, yEixo - 3, px, yEixo + 3);
                g.drawString(String.valueOf(x), px - 8, yEixo + 15);
            }
        }
        for (int y = primeiroMultiplo(plano.getYMin(), passo); y <= plano.getYMax(); y += passo) {
            if (y != 0) {
                int py = centroY(t, y);
                g.drawLine(xEixo - 3, py, xEixo + 3, py);
                g.drawString(String.valueOf(y), xEixo + 6, py + 4);
            }
        }
    }

    private int centroX(Transformacao t, int x) {
        return (t.mundoParaDispositivoX(x - 0.5) + t.mundoParaDispositivoX(x + 0.5) - 1) / 2;
    }

    private int centroY(Transformacao t, int y) {
        return (t.mundoParaDispositivoY(y + 0.5) + t.mundoParaDispositivoY(y - 0.5) - 1) / 2;
    }

    private static int escolherPasso(double escala) {
        for (int magnitude = 1; ; magnitude *= 10) {
            for (int base : BASES) {
                int passo = base * magnitude;
                if (passo * escala >= ESPACO_MINIMO_ROTULO) {
                    return passo;
                }
            }
        }
    }

    private static int primeiroMultiplo(int minimo, int passo) {
        return (int) Math.ceil(minimo / (double) passo) * passo;
    }
}