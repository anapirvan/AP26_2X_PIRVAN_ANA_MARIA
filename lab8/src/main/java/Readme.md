In pachetul compulsory se afla si rezolvarile pt homework si advanced.


Compulsory:

In MazeApp cream butoanele de care avem nevoie in partea de sus a ferestrei, respectiv in partea de jos.
In partea de sus user-ul poate specifica nr de linii si de coloane in casetele aferente, iar butonul "Draw maze" va desena matricea cu dimensiunile introduse.
Apoi in partea de jos butonul Create va elimina random un nr de (nr. de linii* nr. de coloane)/2 pereti din labirint, de fiecare data cand este apasat.

Clasa Cell se foloseste pt desenarea matricii, speficand linia si coloana pe care se afla, si setand cei 4 pereti corespunzator.

Homework:

In MazeApp am adaugat metoda setOnMouseClicked in cadrul butonului Create, care permite user ului sa editeze manual pretii labirintului, cu ajutorul unui click.
Butonul Validate verifica daca se poate ajunge de la celula din stanga sus la cea din dreapta jos, folosind un algoritm bfs. Daca se poate ajunge, se coloreaza celulele de inceput si final cu verde, iar daca nu se poate ajunge, se coloreaza cu rosu.
Butonul "Export PNG" exporta un screenshot al labirintului in format PNG.
Butoanele "Save" si "Load" ne permit sa salvam local, respectiv sa incarcam un astfel de labirint, iar pt a implementa aceste functionalitati avem nevoie ca si clasa Cell sa implementeze Serializable. 

Advanced:

Butonul "Generate" apeleaza metoda generate care, cu ajutorul unui algoritm dfs animat in timp real, construieste un labirint valid.