package controle;

import circunferencia.AlgoritmoCircunferencia;
import grafico.PainelDesenho;
import grafico.PlanoCartesiano;
import nucleo.Config;
import nucleo.Plotador;
import reta.AlgoritmoReta;
import ui.JanelaPrincipal;
import ui.PainelConsole;
import ui.PainelControle;

import java.util.HashSet;
import java.util.Set;

public class ControladorDesenho {

    private final PainelControle painelControle;
    private final PainelConsole painelConsole;
    private final PlanoCartesiano plano;
    private final PainelDesenho painelDesenho;
    private final RepositorioAlgoritmos repositorio;

    public ControladorDesenho(JanelaPrincipal janela, RepositorioAlgoritmos repositorio) {
        this.painelControle = janela.getPainelControle();
        this.painelConsole = janela.getPainelConsole();
        this.plano = janela.getPlano();
        this.painelDesenho = janela.getPainelDesenho();
        this.repositorio = repositorio;

        painelControle.definirAlgoritmosReta(repositorio.nomesRetas());
        painelControle.definirAlgoritmosCircunferencia(repositorio.nomesCircunferencias());
        painelControle.aoClicarDesenhar(this::aoDesenhar);
        painelControle.aoClicarLimpar(this::aoLimpar);
        painelControle.aoAlterarPixelReal(this::aoLimpar);
    }

    public void aoDesenhar() {
        try {
            if (painelControle.isModoReta()) {
                desenharReta();
            } else {
                desenharCircunferencia();
            }
        } catch (IllegalArgumentException e) {
            painelControle.mostrarMensagem(e.getMessage());
        }
    }

    public void aoLimpar() {
        plano.limpar();
        painelDesenho.setPixelReal(painelControle.isPixelReal());
        painelConsole.limpar();
        painelControle.limparMensagem();
        painelDesenho.atualizar();
    }

    private void desenharReta() {
        int x1 = ValidadorEntrada.lerX(painelControle.getX1(), "X1");
        int y1 = ValidadorEntrada.lerY(painelControle.getY1(), "Y1");
        int x2 = ValidadorEntrada.lerX(painelControle.getX2(), "X2");
        int y2 = ValidadorEntrada.lerY(painelControle.getY2(), "Y2");
        AlgoritmoReta algoritmo = repositorio.reta(painelControle.getAlgoritmoReta());

        prepararPlano(Math.min(x1, x2), Math.max(x1, x2), Math.min(y1, y2), Math.max(y1, y2));
        painelConsole.limpar();
        painelConsole.passo("Reta " + algoritmo.nome() + ": (" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")");

        Contagem contagem = new Contagem();
        algoritmo.desenhar(x1, y1, x2, y2, contagem, painelConsole);
        concluir("Reta " + algoritmo.nome(), contagem);
    }

    private void desenharCircunferencia() {
        int xc = ValidadorEntrada.lerX(painelControle.getXc(), "Xc");
        int yc = ValidadorEntrada.lerY(painelControle.getYc(), "Yc");
        int raio = ValidadorEntrada.lerRaio(painelControle.getRaio(), "Raio");
        AlgoritmoCircunferencia algoritmo = repositorio.circunferencia(painelControle.getAlgoritmoCircunferencia());

        prepararPlano(xc - raio, xc + raio, yc - raio, yc + raio);
        painelConsole.limpar();
        painelConsole.passo("Circunferência " + algoritmo.nome() + ": centro (" + xc + "," + yc + "), raio " + raio);

        Contagem contagem = new Contagem();
        algoritmo.desenhar(xc, yc, raio, contagem, painelConsole);
        concluir("Circunferência " + algoritmo.nome(), contagem);
    }

    private void prepararPlano(int minX, int maxX, int minY, int maxY) {
        boolean real = painelControle.isPixelReal();
        painelDesenho.setPixelReal(real);
        if (real) {
            int meioX = (limitarImpar(painelDesenho.getWidth()) - 1) / 2;
            int meioY = (limitarImpar(painelDesenho.getHeight()) - 1) / 2;
            plano.ajustarLimites(-meioX, meioX, -meioY, meioY);
        } else {
            plano.ampliarPara(minX - Config.MARGEM, maxX + Config.MARGEM, minY - Config.MARGEM, maxY + Config.MARGEM);
        }
    }

    private static int limitarImpar(int tamanho) {
        int n = Math.min(tamanho, 2 * Config.LIMITE_PLANO + 1);
        if (n % 2 == 0) {
            n--;
        }
        return Math.max(n, 1);
    }

    private void concluir(String titulo, Contagem contagem) {
        painelConsole.passo("total: " + contagem.acesos.size() + " pontos");
        String mensagem = titulo + ": " + contagem.acesos.size() + " pixels desenhados.";
        if (contagem.fora.size() > 0) {
            mensagem += "\n" + contagem.fora.size() + " pontos ficaram fora do plano e foram ignorados.";
        }
        painelControle.mostrarMensagem(mensagem);
        painelDesenho.atualizar();
    }

    private class Contagem implements Plotador {

        private final Set<Long> acesos = new HashSet<>();
        private final Set<Long> fora = new HashSet<>();

        @Override
        public void setPixel(int x, int y) {
            long chave = ((long) x << 32) ^ (y & 0xFFFFFFFFL);
            if (plano.contem(x, y)) {
                acesos.add(chave);
            } else {
                fora.add(chave);
            }
            plano.setPixel(x, y);
        }
    }
}