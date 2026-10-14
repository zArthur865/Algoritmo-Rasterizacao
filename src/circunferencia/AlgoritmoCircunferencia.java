package circunferencia;

import nucleo.Algoritmo;
import nucleo.Plotador;
import nucleo.Registro;

/**
 * Interface comum das circunferências, para o controlador trocar de algoritmo.
 * Baseada no Documento de Arquitetura (Lab 2 - CG).
 */
public interface AlgoritmoCircunferencia extends Algoritmo {

    /**
     * Contrato principal definido na tabela de arquitetura.
     */
    void desenhar(int xc, int yc, int raio, Plotador p);

    /**
     * Sobrecarga para suportar o Console de Passos (Registro),
     * conforme seção "Console de passos" do documento de arquitetura.
     */
    default void desenhar(int xc, int yc, int raio, Plotador p, Registro r) {
        desenhar(xc, yc, raio, p);
    }
}