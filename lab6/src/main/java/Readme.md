Compulsory:

Database este o clasa singleton ca asigura conexiunea la baza de date.
Clasa GenreDAO ne ofera posibilitatea sa inseram genuri noi in tabela si sa gasim un gen dupa nume, respectiv id.

Homework:

In clasa Database initializez un connection pool Hikari, pe care il configurez cu atributele necesare. Metoda getConnection furnizeaza o conexiune din pool.
Clasele Actor, Genre si Movie mapeaza tabelele actors, genres si movies din baza de date postgres.
Clasele DAO pt Actor, Genre si Movie implementeaza operatiile CRUD, iar metoda mapRow este folosita la operatiile Read pt a converti un rand din ResultSet intr-un obiect Java.
Clasa ReportGenerator populeaza template-ul Freemarker, "template.ftl", cu date despre filme extrase din baza de date. Apoi metoda process genereaza raportul html cu ajutorul template-ului.
Clasa Main testeaza operatiile CRUD pe fiecare clasa DAO si apoi apeleaza metoda din clasa ReportGenerator care genereaza raportul html.

Advanced:

Clasa Database aduce in plus configurarea Flyway, in comparatie cu clasa Database din homework.

Clasa MovieImporter importa date din "The Movies Dataset". 
Constructorul deschide fisierul csv dat prin path, si sare peste primul rand, care e header-ul.
Metoda extractFirstGenre extrage si returneaza numele primului gen dintr-o coloana de genuri a fisierului csv, data ca paramtru.
Metoda importMovie construieste un obiect Movie, pe care il populeaza cu date din fisierul csv. In plus, daca genul acestui film din fisierul csv nu exista in baza de date, il adauga.

Primul script Flyway creeaza tabelele initiale, iar cel de-al doilea creeaza tabelele movie_lists si movie_list_entries(tabela de legatura care implementeaza relatia many-to-many dintre movie_lists si movies).

Clasa MovieList contine atat campurile din tabela movie_lists, cat si o lista de filme, pt a reprezenta relatia many-to-many dintre movie_lists si movie direct in acest obiect.
Clasa MovieListDAO implementeaza operatiile create, read si delete pt tabela movie_lists.

Clasa MovieDAO aduce in plus fata de homework metoda findAllWithActors, care printr-o interogare sql obtine detalii despre toate filmele, impreuna cu detalii despre actorii care joaca in ele. 
Folosim un Map, pt ca un film poate aparea pe mai multe randuri in ResultSet(daca are mai multi actori), si astfel folosim id-ul filmului ca si cheie, iar pt fiecare film adaugam toti actorii care joaca in el(daca exista).

Metoda partition din clasa MoviePartitioner partitioneaza toate filmele in liste care contin doar filme care nu au actori comuni, astfel:
extrage toate filmele impreuna cu actorii lor din MovieDAO si sorteaza aceasta lista descrescator dupa nr de actori din fiecare film, pt ca filmele cu mai multi actori sa fie procesate primele (au mai multe sanse de conflict); 
apoi se parcurg toate filmele, iar pt fiecare se cauta o lista de filme dintre cele existente, cu care nu are conflict;
daca nu exista o astfel de lista, se construieste una noua.

Clasa Main importa 50 de filme din fisierul csv, pe care le insereaza in tabela movies.
Apoi testeaza sistemul de movie lists, si in final testeaza si algoritmul de partitionare pe cele 50 de filme importate.