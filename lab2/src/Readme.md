Compulsory:

Clasa Location contine 4 variabile private(nume, tip, coordonata X si coordonata Y), un constructor care primeste 4 parametrii, iar pt fiecare dintre aceste variabile se implementeaza cate un Getter si Setter.
Metoda toString din clasa Object este suprascrisa, astfel incat sa afiseze o reprezentare textuala a obiectlui Location.

Clasa Road contine 5 variabile private(tipul, lungimea, limita de viteza, locatia de start si locatia destinatie), un constructor care primeste 5 parametrii, iar pt fiecare dintre aceste variabile se implementeaza cate un Getter si un Setter.
Metoda toString din clasa Object este suprascrisa, astfel incat sa afiseze o reprezentare textuala a obiectlui Road.
Enum-ul RoadType contine cele 3 tipuri posibile de drumuri.

In clasa Main sunt create 2 instante ale clasei Location si o instanta a clasei Road, si pt fiecare obiect se apeleaza metoda toString.

Homework:

Clasele Airport, City si GasStation sunt mostenite din clasa Location. Fiecare clasa are si atribute specifice, pe langa cele mostenite.
Clasa Location este abstracta si sealed, permitand numai claselor Airport, City si Gasstation sa o extinda.

Metodele Object.equals si hashCode sunt suprascrise atat in clasa Location, cat si in clasa Road.
Tipurile de drumuri sunt implementate folosind un enum.

Clasa Problem descrie o instanta a problemei. Aici initializam 2 Set-uri, locations si roads, unde adaugam locatii, respectiv drumuri folosind metoda addLocation, respectiv addRoad. 
In plus, tot in aceasta clasa verificam daca instanta problemei este valida, prin faptul ca lungimea fiecarui drum adaugat in Set-ul roads trebuie sa fie mai mare sau egala decat distanta euclidiana dintre coordonatele localitatilor intre care se afla respectivul drum.
Metoda canReach determina daca se poate ajunge de la prima locatie data ca parametru la a doua locatie data ca parametru cu ajutorul drumurilor pe care le avem deja. Metoda implementeaza un bfs, folosindu-se de o coada, un Set de localitati deja vizitate si de un Map care implementeaza lista de adiacenta a localitatilor. In bucla principala returnam true atunci cand dam de localitatea la care voiam sa ajungem. 

Advanced:

Clasa Solution contine metoda solve care gaseste cea mai buna ruta intre 2 localitati, fie d.p.d.v. al timpului, fie al distantei. Metoda implementeaza algoritmul Dijkstra, folosindu-se de un Map care asociaza fiecarei localitati distanta de la ea la localitatea de start, un Map care asociaza fiecarei localitati parintele ei (pt a putea recrea la final ruta) si o coada cu prioritati. Atunci cand apelam acesta metoda, ne folosim de al doilea parametru pt a specifica daca vrem cea mai buna ruta d.p.d.v. al timpului, respectiv al distantei.
