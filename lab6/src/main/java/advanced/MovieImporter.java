package advanced;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import homework.Actor;
import homework.Genre;
import homework.GenreDAO;
import homework.Movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.*;

public class MovieImporter {
    private String path;
    CSVReader reader;

    public MovieImporter(String path) {
        this.path = path;

        try {
            reader = new CSVReaderBuilder(new FileReader(path)).build();
        } catch (FileNotFoundException e) {
            System.err.println("Fisierul nu a fost gasit: " + e);
            reader = null;
        }

        try {
            reader.readNext();
        } catch (Exception e) {
            System.out.println("Eroare la read: " + e);
        }
    }

    private String extractFirstGenre(String raw) {
        int nameIndex = raw.indexOf("'name':");
        if (nameIndex == -1) return null;

        int start = raw.indexOf("'", nameIndex + 7);
        if (start == -1) return null;

        int end = raw.indexOf("'", start + 1);
        if (end == -1) return null;

        return raw.substring(start + 1, end).trim();
    }

    public Movie importMovie() {

        if (reader == null) {
            System.err.println("Fisierul csv nu a fost gasit");
            return null;
        }

        String[] nextLine;
        try {
            nextLine = reader.readNext();
        } catch (Exception e) {
            System.out.println("Eroare la read: " + e);
            return null;
        }
        if (nextLine == null) {
            System.err.println("Nu mai sunt randuri de citit");
            return null;
        }

        GenreDAO genreDAO = new GenreDAO();
        String genreName = extractFirstGenre(nextLine[3]);
        if (genreName == null) return null;
        Genre genre;
        try {
            genre = genreDAO.findByName(genreName);
            if (genre == null) {
                genre = new Genre(genreName);
                genreDAO.create(genre);
                genre = genreDAO.findByName(genreName);
            }
        } catch (SQLException e) {
            System.err.println("Eroare genre: " + e.getMessage());
            return null;
        }

        return new Movie(nextLine[20], nextLine[14], (int)Double.parseDouble(nextLine[16]), (int)Double.parseDouble(nextLine[22]), genre);
    }

}