package compulsory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
            GenreDAO genreDAO=new GenreDAO();
            genreDAO.create("SF");
            Database.getConnection().commit();

            Integer genreID=genreDAO.findByName("SF");
            if(genreID!=null){
                System.out.println("Id: "+genreID);
                String genreName=genreDAO.findById(genreID);
                System.out.println("Nume:"+genreName);
            }
            else{
                System.out.println("Genul nu a fost gasit");
            }
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
