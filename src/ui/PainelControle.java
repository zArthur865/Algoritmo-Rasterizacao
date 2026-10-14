package ui;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.BorderLayout;


public class PainelControle extends JPanel {

    private static final String CARTAO_RETA = "reta";
    private static final String CARTAO_CIRCUNFERENCIA = "circunferencia";

    private final JToggleButton botaoReta = new JToggleButton("Reta", true);
    private final JToggleButton botaoCircunferencia = new JToggleButton("Circunferência");

    private final CardLayout cartoes = new CardLayout();
    private final JPanel painelCartoes = new JPanel(cartoes);

    private final JTextField campoX1 = criarCampo();
    private final JTextField campoY1 = criarCampo();
    private final JTextField campoX2 = criarCampo();
    private final JTextField campoY2 = criarCampo();
    private final JTextField campoXc = criarCampo();
    private final JTextField campoYc = criarCampo();
    private final JTextField campoRaio = criarCampo();

    private final JComboBox<String> listaReta =
            new JComboBox<>(new String[]{"DDA", "Ponto médio"});
    private final JComboBox<String> listaCircunferencia =
            new JComboBox<>(new String[]{"Polinomial", "Trigonométrico", "Ponto médio"});

    private final JButton botaoDesenhar = new JButton("Desenhar");
    private final JButton botaoLimpar = new JButton("Limpar");

    private final JTextArea areaMensagens = new JTextArea();

    public PainelControle() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(310, 0));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(0, 0, 10, 0);

        JLabel titulo = new JLabel("PAINEL DE CONTROLE");
        titulo.setFont(titulo.getFont().deriveFont(java.awt.Font.BOLD, 12f));
        c.gridy = 0;
        add(titulo, c);

        c.gridy = 1;
        add(criarSeletorDeModo(), c);

        painelCartoes.add(criarCartaoReta(), CARTAO_RETA);
        painelCartoes.add(criarCartaoCircunferencia(), CARTAO_CIRCUNFERENCIA);
        c.gridy = 2;
        add(painelCartoes, c);

        c.gridy = 3;
        add(criarBotoes(), c);

        JLabel rotulo = new JLabel("Mensagens e nº de pixels");
        rotulo.setForeground(Color.GRAY);
        c.gridy = 4;
        c.insets = new Insets(0, 0, 4, 0);
        add(rotulo, c);

        areaMensagens.setEditable(false);
        areaMensagens.setLineWrap(true);
        areaMensagens.setWrapStyleWord(true);
        areaMensagens.setMargin(new Insets(6, 6, 6, 6));
        areaMensagens.setBackground(new Color(0xF7F7F7));
        c.gridy = 5;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        add(new JScrollPane(areaMensagens), c);

        configurarEventosInternos();
    }

    // ---------------------------------------------------------------- montagem

    private JPanel criarSeletorDeModo() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botaoReta);
        grupo.add(botaoCircunferencia);
        JPanel painel = new JPanel(new GridLayout(1, 2, 6, 0));
        painel.add(botaoReta);
        painel.add(botaoCircunferencia);
        return painel;
    }

    private JPanel criarCartaoReta() {
        JPanel p = new JPanel(new GridBagLayout());
        adicionarLinha(p, 0, "X1", campoX1, "Y1", campoY1);
        adicionarLinha(p, 1, "X2", campoX2, "Y2", campoY2);
        adicionarAlgoritmo(p, 2, listaReta);
        return p;
    }

    private JPanel criarCartaoCircunferencia() {
        JPanel p = new JPanel(new GridBagLayout());
        adicionarLinha(p, 0, "Xc", campoXc, "Yc", campoYc);
        adicionarLinha(p, 1, "Raio", campoRaio, null, null);
        adicionarAlgoritmo(p, 2, listaCircunferencia);
        return p;
    }

    private void adicionarLinha(JPanel p, int linha, String rotulo1, JTextField campo1,
                                String rotulo2, JTextField campo2) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = linha;
        c.insets = new Insets(0, 0, 8, 6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        p.add(new JLabel(rotulo1), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        p.add(campo1, c);

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        if (rotulo2 != null) {
            c.gridx = 2;
            p.add(new JLabel(rotulo2), c);
            c.gridx = 3;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.insets = new Insets(0, 0, 8, 0);
            p.add(campo2, c);
        }
    }

    private void adicionarAlgoritmo(JPanel p, int linha, JComboBox<String> lista) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = linha;
        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 6);
        c.anchor = GridBagConstraints.WEST;
        p.add(new JLabel("Algoritmo:"), c);

        c.gridx = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        p.add(lista, c);
    }

    private JPanel criarBotoes() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 6, 0));
        painel.add(botaoDesenhar);
        painel.add(botaoLimpar);
        return painel;
    }

    private static JTextField criarCampo() {
        JTextField campo = new JTextField(4);
        campo.setHorizontalAlignment(JTextField.RIGHT);
        return campo;
    }

    private void configurarEventosInternos() {
        botaoReta.addActionListener(e -> cartoes.show(painelCartoes, CARTAO_RETA));
        botaoCircunferencia.addActionListener(e -> cartoes.show(painelCartoes, CARTAO_CIRCUNFERENCIA));

        // Enter em qualquer campo equivale a clicar em Desenhar
        for (JTextField campo : new JTextField[]{campoX1, campoY1, campoX2, campoY2,
                campoXc, campoYc, campoRaio}) {
            campo.addActionListener(e -> botaoDesenhar.doClick());
        }
    }

    // ----------------------------------------------------- contrato para o controle

    public boolean isModoReta() {
        return botaoReta.isSelected();
    }

    // Os getters devolvem o texto digitado; quem converte em inteiro é o ValidadorEntrada.
    public String getX1() { return campoX1.getText(); }
    public String getY1() { return campoY1.getText(); }
    public String getX2() { return campoX2.getText(); }
    public String getY2() { return campoY2.getText(); }
    public String getXc() { return campoXc.getText(); }
    public String getYc() { return campoYc.getText(); }
    public String getRaio() { return campoRaio.getText(); }

    public String getAlgoritmoReta() {
        return (String) listaReta.getSelectedItem();
    }

    public String getAlgoritmoCircunferencia() {
        return (String) listaCircunferencia.getSelectedItem();
    }

    public void definirAlgoritmosReta(String... nomes) {
        listaReta.setModel(new DefaultComboBoxModel<>(nomes));
    }

    public void definirAlgoritmosCircunferencia(String... nomes) {
        listaCircunferencia.setModel(new DefaultComboBoxModel<>(nomes));
    }

    public void aoClicarDesenhar(Runnable acao) {
        botaoDesenhar.addActionListener(e -> acao.run());
    }

    public void aoClicarLimpar(Runnable acao) {
        botaoLimpar.addActionListener(e -> acao.run());
    }

    public void mostrarMensagem(String texto) {
        areaMensagens.setText(texto);
        areaMensagens.setCaretPosition(0);
    }

    public void limparMensagem() {
        areaMensagens.setText("");
    }

    public void limparCampos() {
        for (JTextField campo : new JTextField[]{campoX1, campoY1, campoX2, campoY2,
                campoXc, campoYc, campoRaio}) {
            campo.setText("");
        }
    }
}