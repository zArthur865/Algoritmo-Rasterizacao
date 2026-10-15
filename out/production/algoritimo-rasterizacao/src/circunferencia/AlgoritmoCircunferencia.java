package circunferencia;

import nucleo.Algoritmo;
import nucleo.Plotador;
import nucleo.Registro;

public interface AlgoritmoCircunferencia extends Algoritmo {

    void desenhar(int xc, int yc, int raio, Plotador p, Registro r);
}