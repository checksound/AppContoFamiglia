# App Conto Famiglia

[Esercitazione 2 - Sincronizzazione (indiretta-indiretta) in Java](https://iisponti.gitbook.io/tecnologie_quinta_2023_24/esercizi-su-thread-in-java#esercitazione-2-sincronizzazione-indiretta-indiretta-in-java)

Conto. Si progetti una applicazione Java che permetta a più thread con ruoli diversi di operare 
contemporaneamente su un oggetto condiviso di classe `Conto`, sfruttando opportunamente il 
modificatore `synchronized` per evitare inconsistenze sull’oggetto condiviso. In particolare:
* un thread padre deve poter aprire un “conto” (un oggetto di classe `Conto`) e condividerlo con altri n thread 
(considerati figli del thread padre);
* il saldo iniziale del conto è pari a 10 000 euro;
* thread differenti devono poter prelevare denaro, con importi diversi, dallo stesso “conto” 
(stesso oggetto di classe `Conto`) invocando il metodo `prelievo(…)` della classe `Conto`;
le operazioni di prelievo devono essere possibili solo se mantengono il saldo del conto in positivo;
* il thread padre deve poter aggiungere denaro al conto tutte le volte in cui i figli non riescono più a prelevare.

Per testare il corretto funzionamento dell’oggetto di classe `Conto`, si preveda un programma 
principale che istanzi n 
thread figli che tentano di prelevare denaro continuamente (in un ciclo iterativo) dal conto.

Cosa succede se si utilizza `notify()` anzichè `notifyAll()` per il risveglio dei thread in 
attesa di cambiamento dell’ammontare del conto? 
*[Suggerimento: Definire la classe
Conto
dell’oggetto condiviso ed utilizzare la sincronizzazione diretta (modificatore synchronized + i metodi wait-notify) per la mutua esclusione e la comunicazione 
tra thread. Si modelli poi il thread padre ed i thread figli come classi Java diverse. 
Introdurre, infine, una classe con il metodo main per creare ed avviare i vari thread, e 
testare l’applicazione.]*

## Soluzioni

### Caso 1

Soluzione con il padre e più figli che accedono al 
[org.contofamiglia.Conto](./src/main/java/org/contofamiglia/Conto.java) condiviso, 
[org.contofamiglia.AppContoFamiglia](./src/main/java/org/contofamiglia/AppContoFamiglia.java).

### Caso 2

Soluzione con i due genitori, padre e madre, e più figli che accedono al 
[org.contofamiglia2.Conto](./src/main/java/org/contofamiglia2/Conto.java) condiviso,
[org.contofamiglia2.AppContoFamiglia](./src/main/java/org/contofamiglia2/AppContoFamiglia.java).

Quando i figli rimangono bloccati perché non riescono a prelevare, uno e solo uno dei due genitori, 
deve depositare sul conto.

### Caso 3

Differente implementazione di [org.contofamiglia3.Conto](./src/main/java/org/contofamiglia3/Conto.java)
condiviso e applicazione [org.contofamiglia3.AppContoFamiglia](./src/main/java/org/contofamiglia3/AppContoFamiglia.java).

### Caso 4

Utilizzo della classi `java.util.concurrent.locks.Lock` e `java.util.concurrent.locks.Condition`
per la sincronizzazione in modo esplicito: [org.contofamiglia4.Conto](./src/main/java/org/contofamiglia4/Conto.java) condiviso
e applicazione [org.contofamiglia4.AppContoFamiglia](./src/main/java/org/contofamiglia4/AppContoFamiglia.java).

