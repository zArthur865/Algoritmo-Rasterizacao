package controle;

import circunferencia.AlgoritmoCircunferencia;
import circunferencia.CircPolinomial;
import circunferencia.CircPontoMedio;
import circunferencia.CircTrigonometrico;
import reta.AlgoritmoReta;
import reta.RetaDDA;
import reta.RetaPontoMedio;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositorioAlgoritmos {

    private final Map<String, AlgoritmoReta> retas = new LinkedHashMap<>();
    private final Map<String, AlgoritmoCircunferencia> circunferencias = new LinkedHashMap<>();

    public RepositorioAlgoritmos() {
        registrarReta(new RetaDDA());
        registrarReta(new RetaPontoMedio());
        registrarCircunferencia(new CircPolinomial());
        registrarCircunferencia(new CircTrigonometrico());
        registrarCircunferencia(new CircPontoMedio());
    }

    public void registrarReta(AlgoritmoReta algoritmo) {
        retas.put(algoritmo.nome(), algoritmo);
    }

    public void registrarCircunferencia(AlgoritmoCircunferencia algoritmo) {
        circunferencias.put(algoritmo.nome(), algoritmo);
    }

    public AlgoritmoReta reta(String nome) {
        AlgoritmoReta algoritmo = retas.get(nome);
        if (algoritmo == null) {
            throw new IllegalArgumentException("Algoritmo de reta desconhecido: " + nome);
        }
        return algoritmo;
    }

    public AlgoritmoCircunferencia circunferencia(String nome) {
        AlgoritmoCircunferencia algoritmo = circunferencias.get(nome);
        if (algoritmo == null) {
            throw new IllegalArgumentException("Algoritmo de circunferência desconhecido: " + nome);
        }
        return algoritmo;
    }

    public String[] nomesRetas() {
        return retas.keySet().toArray(new String[0]);
    }

    public String[] nomesCircunferencias() {
        return circunferencias.keySet().toArray(new String[0]);
    }
}