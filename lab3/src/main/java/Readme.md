Compulsory:

Clasele Person si Company implementeaza interfata Profile si interfata Comparable. Functia compareTo este suprascrisa
astfel incat sa ordoneze persoanele, respectiv companiile dupa nume.
In Main cream o lista cu persoane si companii, pe care o sortam ulterior cu un comparator ce sorteaza dupa nume.

Homework:

Clasele Programmer si Designer extind clasa Person, iar fiecare dintre ele are cate un camp specific in plus, pe care clasa Person nu il are.
In clasa Person implementam un Map care sa mapeze relatiile fiecarei persoane cu o alta persoana/companie.
Clasa SocialNetwork implementeaza o lista de profiluri, iar pt fiecare profil(persoana si companie) calculeaza importanta, exprimata ca nr de relatii al fiecarui profil.

Advanced:

Clasa SocialNetwork are in plus metoda dfs, care este folosita de metodele findCutPoints si findBiconnectedComponents.
Metoda buildGraph construieste graful cu ajutorul unui Map, unde pt fiecare profil cheie retinem setul de profiluri cu care acesta are o relatie directa.