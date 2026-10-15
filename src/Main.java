import controle.ControladorDesenho;
import controle.RepositorioAlgoritmos;
import ui.JanelaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janela = new JanelaPrincipal();
            new ControladorDesenho(janela, new RepositorioAlgoritmos());
            janela.setVisible(true);
        });
    }
}