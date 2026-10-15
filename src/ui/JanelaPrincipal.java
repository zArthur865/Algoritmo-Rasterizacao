package ui;

import grafico.PainelDesenho;
import grafico.PlanoCartesiano;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

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
        Rectangle tela = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(new Dimension(Math.min(getWidth(), tela.width), Math.min(getHeight(), tela.height)));
        setLocationRelativeTo(null);
    }

    public PlanoCartesiano getPlano() { return plano; }
    public PainelDesenho getPainelDesenho() { return painelDesenho; }
    public PainelControle getPainelControle() { return painelControle; }
    public PainelConsole getPainelConsole() { return painelConsole; }
}