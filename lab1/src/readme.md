Homework:

Functia dreptunghi1 construieste o matrice de dimensiune n*n, iar apoi seteaza variabilele lungime si latime corespunzator pt a putea construi un dreptunghi in matrice. Punem cifra 0 in matrice (care va reprezenta dreptunghiul nostru) atunci cand i apartine intervalului [1,latime] si j apartine intervalului [1,lungime].  
Functia cerc1 construieste o matrice de dimensiune n*n, iar apoi seteaza centrul cercului C(n/2,n/2) si raza=n/4. Ne folosim de inegalitatea care testeaza daca un punct X(a,b) apartine cercului de centru C si raza r, si setam valoarea 255 pt punctele care indeplinesc aceasta conditie.
Functia dreptunghi2 construieste o reprezentare cu caractere in loc de numere a matricei initiale din functia dreptunghi1, cu ajutorul unui StringBuilder.
Functia cerc2 construiste o reprezentare cu caractere in loc de numere a matricei initiale din functia cerc1, cu ajutorul unui StringBuilder. 
In main, preluam argumentele din linia de comanda (dimensiunea matricei si forma), iar daca dimensiunea matricei (n) este mai mare decat 10000, calculam timpul de rulare in milisecunde pt construirea matricii, fara sa o afisam la final. In caz contrar, afisam cele 2 reprezentari in matrice ale formei cerute.

Advanced:

Functia boundingBox primeste ca argument o matrice cu valori de 0 si 1, unde 1 reprezinta forma, iar 0 fundalul. Pt a incadra forma intr un dreptunghi, trebuie sa stabilim care sunt cele 4 puncte care formeaza colturile dreptunghiului. Facem asta calculand linia minima, linia maxima, coloana minima si coloana maxima pe care se afla valori de 1 in matrice. Avand aceste puncte putem calcula si latimea si lungimea dreptunghiului.