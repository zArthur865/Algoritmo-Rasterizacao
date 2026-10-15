package ui;

import nucleo.Registro;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

public class PainelConsole extends JPanel implements Registro {

    private final JTextArea area = new JTextArea();

    public PainelConsole() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("CONSOLE DE CÁLCULOS"));
        setPreferredSize(new Dimension(0, 150));

        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        area.setBackground(new Color(0xF2F2F2));
        area.setMargin(new Insets(4, 6, 4, 6));

        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    @Override
    public void passo(String texto) {
        executarNaThreadDaTela(() -> {
            area.append(texto + "\n");
            area.setCaretPosition(area.getDocument().getLength());
        });
    }

    public void limpar() {
        executarNaThreadDaTela(() -> area.setText(""));
    }

    private static void executarNaThreadDaTela(Runnable acao) {
        if (SwingUtilities.isEventDispatchThread()) {
            acao.run();
        } else {
            SwingUtilities.invokeLater(acao);
        }
    }
}