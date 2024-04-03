package org.contofamiglia;

public class AppContoFamiglia {

    public static void main(String[] args) {
        Conto contoCondiviso = new Conto(1000);

        Thread tPadre = new Thread(new Padre(contoCondiviso), "padre");
        tPadre.start();

        Figlio figlio1 = new Figlio(contoCondiviso);

        Thread tFiglio1 = new Thread(new Figlio(contoCondiviso), "figlio 1");
        Thread tFiglio2 = new Thread(new Figlio(contoCondiviso), "figlio 2");

        tFiglio1.start();
        tFiglio2.start();
    }
}
