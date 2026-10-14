package teste;
import java.util.ArrayList;
import java.util.List;
import nucleo.Plotador;
import nucleo.Registro;
import reta.RetaDDA;

public class TesteReta {

    public static void main(String[] args) {
        List<String> pontos = new ArrayList<>();

        Plotador testePlot = (x, y) -> pontos.add("(" + x + "," + y + ")");
        Registro console = texto -> System.out.println(texto);

        new RetaDDA().drawPixel(6, 9, 11, 12, testePlot, console);
        String esperado = "[(6,9), (7,10), (8,10), (9,11), (10,11), (11,12)]";
        System.out.println(pontos.toString().equals(esperado) ? "OK" : "FALHOU");

        System.out.println(pontos);
    }
}