Compulsory:

Clasa GameServer creeaza un ServerSocket care ruleaza la portul 8100, si primeste comenzi de la clienti, pe care le executa in thread uri separate.
Clasa ClientThread comunica direct cu clientul printr-un socket, iar daca primeste comanda "stop" de la client opreste serverul.

Homework:

Clasa Question se ocupa de parsarea intrebarilor din questions.txt din resources, apoi amesteca raspunsurile.
In GameServer cream o lista de clienti in care adaugam toti clientii care se conecteaza, si care sunt automat si jucatori, iar pt fiecare pornim un ClientThread nou.
Cand se conecteaza 2 clienti, jocul incepe si se trimit pe rand intrebarile. 
Metoda submitAnswer este apelata in ClientThread cand un jucator a trimis raspunsul. Aceasta evaluaza raspunsul si trimite un mesaj corespunzator catre jucator, iar dupa ce toti jucatorii au raspuns se trimite si o tabela cu scorurile jucatorilor.
Dupa ce s-au trimis toate intrabarile, serverul apeleaza metoda endGame, care termina jocul si afiseaza scorurile finale.