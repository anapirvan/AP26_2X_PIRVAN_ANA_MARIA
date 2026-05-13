Compulsory:

Clasa Maze implementeaza o matrice formata din mai multe celule, de tipul clasei Cell, iar o astfel de celula contine detalii precum randul si coloana pe care se afla, si daca este iesire sau perete.
Clasele Bunny si Robot contin pozitia pe care se afla in acel moment, precum si o metoda move, care alege random o directie de deplasare care este libera.
Metoda start din clasa Game construieste thread-urile pt bunny si robot, dupa care le porneste, si asteapta sa se termine toate. Sectiunea critica este cea in care un robot sau un iepure se deplaseaza.

Homework:

Clasa SharedMemory este folosita de roboti pt a stoca informatii despre pozitia iepurelui. Robotii nu pot vedea pozitia iepurelui decat daca sunt aproape de acesta. 
Comenzile slow down, speed up, stop, resume modifica viteza dintre mutari, adica concret valoarea data in sleep la bunny, respectiv la robots.
Thread-ul managerThread afiseaza periodic cat timp s-a scurs de la inceputul executiei si opreste jocul daca depaseste limita de timp.