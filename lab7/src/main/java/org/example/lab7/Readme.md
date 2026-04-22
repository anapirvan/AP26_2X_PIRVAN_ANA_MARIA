Compulsory:

In pachetul model, clasele Genre si Movie mapeaza tabelele genres si movies, folosindu-se de Hibernate.
In pachetul repository, MovieRepository si GenreRepository sunt interfete care extind JpaRepository, oferind implementarile pt toate operatiile de tip CRUD pt entitatile Movie si Genre.
MovieService implementeaza toate metodele de care este nevoie pt a face endpoint-urile, folosindu-se de MovieRepository si GenreRepository. De asemenea, aceste metode includ si anumite validari inainte de alterarea bazei de date.
MovieController se ocupa de maparea endpoint-urilor propriu-zise, apland metodele necesare din MovieService.

Homework:

In pachetul dto, MovieRequestDTO si ScoreRequestDTO sun folosite pentru metodele care au nevoie de body, precum POST, PUT si PATCH, iar MovieResponseDTO este intors ca raspuns de POST, PUT si PATCH.
In GlobalExceptionHandler personalizam exceptiile de care avem nevoie in MovieService atunci cand facem anumite verificari inainte de alterarea bazei de date.
MovieClientService utilizeaza RestClient pt a interactiona cu endpoint-urile REST ale serverului.
ClientRunner simuleaza comportamnetul unui user real al API-ului construit.