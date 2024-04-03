package org.contofamiglia4;

public class AppContoFamiglia {

    public static void main(String[] args) {
        Conto contoCondiviso = new Conto(1000);

        Thread tPadre = new Thread(new Genitore(contoCondiviso), "padre");
        tPadre.start();
        Thread tMadre = new Thread(new Genitore(contoCondiviso), "madre");
        tMadre.start();

        Figlio figlio1 = new Figlio(contoCondiviso);

        Thread tFiglio1 = new Thread(new Figlio(contoCondiviso), "figlio 1");
        Thread tFiglio2 = new Thread(new Figlio(contoCondiviso), "figlio 2");

        tFiglio1.start();
        tFiglio2.start();
    }
}
