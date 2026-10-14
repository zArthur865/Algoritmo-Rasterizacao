package grafico;

import nucleo.Config;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class PainelDesenho extends JPanel {

    private final PlanoCartesiano plano;

    public PainelDesenho(PlanoCartesiano plano) {
        this.plano = plano;
        setPreferredSize(new Dimension(Config.LARGURA_TELA, Config.ALTURA_TELA));
        setBackground(new Color(Config.COR_FUNDO));
    }

    public void atualizar() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int linha = 0; linha < Config.LINHAS; linha++) {
            for (int coluna = 0; coluna < Config.COLUNAS; coluna++) {
                int cor = plano.cor(coluna, linha);
                if (cor != Config.COR_FUNDO) {
                    g.setColor(new Color(cor));
                    g.fillRect(coluna * Config.ESCALA, linha * Config.ESCALA,
                            Config.ESCALA, Config.ESCALA);
                }
            }
        }
    }
}