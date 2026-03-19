Compulsory:

In Main cream 10 intersectii cu ajutorul stream-urilor, dupa care cream 9 strazi tot cu ajutorul stream-urilor care sa reuneasca aceste intersectii 2 cate 2.
Construim o lista de strazi, implementata printr-un LinkedList, pe care o sortam dupa lungime, folosind o referinta de metoda.
Construim apoi si un set de strazi, implementata printr-un HashSet.

Homework:

In clasa City cream un Map care retine pt fiecare intersectie lista de strazi care pleaca din ea.
Metoda filterStreets afiseaza doar strazile care au o lungime mai mare decat cea data ca parametru si care se intersecteaza cu inca macar 3 strazi.
Metoda buildGraph construieste graful, mapand intersectiile in noduri si strazile in muchii.
Metoda calculateDisplayMST construieste MST-ul grafului dat cu o functie specifica bibliotecii, dupa care il afiseaza.
Metoda otherMSTs foloseste o functie specifica bibliotecii care returneaza toti arborii partiali in ordine crescatoare a costurilor, dupa care afiseaza cati sunt ceruti.

Advanced:

Metoda maintenanceRoute din City face un dfs peste MST-ul calculat si calculeaza costul total.
Clasa ProblemGenerator genereaza random un nr de intersectii, respectiv strazi, tinand cont de inegalitatea lui Euclid.

