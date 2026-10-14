
package reta;
import nucleo.Algoritmo;
import nucleo.Registro;
import nucleo.Plotador;

public interface AlgoritmoReta extends Algoritmo {
    void drawPixel(int x1, int y1, int x2, int y2, Plotador p, Registro r);
}