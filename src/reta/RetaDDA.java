package reta;
import nucleo.Plotador;
import nucleo.Registro;
public class RetaDDA implements AlgoritmoReta{
    @Override
    public String nome(){
        return "RetaDDA";
    }
    @Override
    public void drawPixel(int x1, int y1, int x2, int y2, Plotador p, Registro r){
        int dx = x2-x1;
        int dy = y2-y1;
        double xInc, yInc, x = x1, y = y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        int px = (int)Math.round(x);
        int py = (int)Math.round(y);
        p.setPixel(px,py);
        r.passo("Passo 0: x = "+ x +"; y = "+y +"; ponto ("+ px +","+ py + ")");

        if(steps!=0){
        xInc = (double)dx/(double)steps;
        yInc = (double)dy/(double)steps;
        for(int i=1; i<=steps; i++){
            x += xInc;
            y += yInc;
            px = (int)Math.round(x);
            py = (int)Math.round(y);
            p.setPixel(px,py);
            r.passo("Passo " + i + ": x = "+ x +"; y = "+y +"; ponto ("+ px+","+ py + ")");
            }
        }
    }
}