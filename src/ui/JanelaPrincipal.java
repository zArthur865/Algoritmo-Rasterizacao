package ui;

import grafico.PainelDesenho;
import grafico.PlanoCartesiano;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Janela principal: painel de controle à esquerda; área de desenho e console
 * de cálculos à direita. Só monta e expõe os componentes; quem liga os botões
 * aos algoritmos é o controle.
 */
public class JanelaPrincipal extends JFrame {

    private final PlanoCartesiano plano = new PlanoCartesiano();
    private final PainelDesenho painelDesenho = new PainelDesenho(plano);
    private final PainelControle painelControle = new PainelControle();
    private final PainelConsole painelConsole = new PainelConsole();

    public JanelaPrincipal() {
        super("Rasterização de Retas e Circunferências");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel direita = new JPanel(new BorderLayout());
        direita.add(painelDesenho, BorderLayout.CENTER);
        direita.add(painelConsole, BorderLayout.SOUTH);

        add(painelControle, BorderLayout.WEST);
        add(direita, BorderLayout.CENTER);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public PlanoCartesiano getPlano() { return plano; }
    public PainelDesenho getPainelDesenho() { return painelDesenho; }
    public PainelControle getPainelControle() { return painelControle; }
    public PainelConsole getPainelConsole() { return painelConsole; }
}