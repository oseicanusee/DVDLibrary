/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.dvdlibrary.DAO;

import com.sg.dvdlibrary.DTO.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff
 */

@Component
public class DVDLibraryDaoImpl implements DVDLibraryDao {
    
    private HashMap<String, DVD> dvdLibrary;
    private List<DVD> dvds;
    public final String DVD_FILE;
    public static final String DELIMITER = "::";

    @Autowired
    public DVDLibraryDaoImpl() {
        this.dvdLibrary = new HashMap<>();
        this.DVD_FILE = "src/dvdlibrary.txt";
        this.dvds = new ArrayList<>(this.dvdLibrary.values());
    }
    
    @Autowired
    public DVDLibraryDaoImpl(String rosterTextFile){
        this.DVD_FILE = rosterTextFile;
    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVD();
        DVD currentDVD = this.dvdLibrary.put(title, dvd);
        writeDVD();
        return currentDVD;
    }

    @Override
    public ArrayList<DVD> getAllDvds() throws DVDLibraryDaoException {
        return new ArrayList<DVD>(this.dvdLibrary.values());
    }

    
    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVD();
        return this.dvdLibrary.get(title);
    }

    
    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVD();
        DVD removeDVD = this.dvdLibrary.remove(title);
        writeDVD();
        return removeDVD;
    }



    
    @Override
    public DVD changeReleaseDate(String title, LocalDate newReleaseDate) throws DVDLibraryDaoException {
        loadDVD();
        DVD dvd = this.dvdLibrary.get(title);
        dvd.setReleaseDate(newReleaseDate);
        writeDVD();
        return dvd;
    }

   
    @Override
    public DVD changeMPAARating(String title, String mpaaRating) throws DVDLibraryDaoException {
        loadDVD();
        DVD dvd = this.dvdLibrary.get(title);
        dvd.setMpaaRating(mpaaRating);
        writeDVD();
        return dvd;
    }

    
    @Override
    public DVD changeDirectorName(String title, String directorName) throws DVDLibraryDaoException {
        loadDVD();
        DVD dvd = this.dvdLibrary.get(title);
        dvd.setDirectorName(directorName);
        writeDVD();
        return dvd;
    }

    
    @Override
    public DVD changeStudio(String title, String studio) throws DVDLibraryDaoException {
        loadDVD();
        DVD dvd = this.dvdLibrary.get(title);
        dvd.setStudio(studio);
        writeDVD();
        return dvd;
    }


    
    @Override
    public DVD changeUserRating(String title, String userRating) throws DVDLibraryDaoException {
        loadDVD();
        DVD dvd = this.dvdLibrary.get(title);
        dvd.setUserRating(userRating);
        writeDVD();
        return dvd;
    }

    
    @Override
    public List<DVD> getDVDByDirector(String directorName) throws DVDLibraryDaoException {
        List<DVD> directorDvds = dvds.stream().filter(director -> director.getDirectorName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());
        return directorDvds;
    }

    @Override
    public List<DVD> getDVDByStudio(String studio) throws DVDLibraryDaoException {
       List<DVD> studioDVDs = dvds.stream()
               .filter(studioName -> studioName.getStudio().equalsIgnoreCase(studio))
               .collect(Collectors.toList());
       return studioDVDs;
    }

    
    @Override
    public List<DVD> getDVDByMpaaRating(String MppaRating) throws DVDLibraryDaoException {
        List<DVD> moviesByMPAA = dvds.stream()
                .filter((mpaa) -> mpaa.getMpaaRating().equalsIgnoreCase(MppaRating)).collect(Collectors.toList());
        return moviesByMPAA;
        
    }

    public void loadDVD() throws DVDLibraryDaoException {

        Scanner scanner;
        try {
                scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
                
        } catch (FileNotFoundException e){
            throw new DVDLibraryDaoException("Could not load DVD data in memory.", e);
        }

        DVD currentDVD;

        while(scanner.hasNext()){
            String dvdObject = scanner.nextLine();

            currentDVD = unmarshallDVD(dvdObject);
            dvdLibrary.put(currentDVD.getTitle(), currentDVD);
        }

        scanner.close();
    }

    private void writeDVD() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));

        } catch (IOException e){
            throw new DVDLibraryDaoException("Could not save DVD data.", e);
        }

        String dvdAsText;
        List<DVD> dvds = this.getAllDvds();

        for (DVD currentDVD : dvds){
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    private String marshallDVD(DVD dvd){
        String currentDVD = dvd.getTitle() + DELIMITER;
        currentDVD += dvd.getReleaseDate() + DELIMITER;
        currentDVD += dvd.getMpaaRating() + DELIMITER;
        currentDVD += dvd.getDirectorName() + DELIMITER;
        currentDVD += dvd.getStudio() + DELIMITER;
        currentDVD += dvd.getUserRating();
        return currentDVD;
    }

    public DVD unmarshallDVD(String dvdAsText){

        //______________________________________________________________________________________________
        //        // |                |            |     |              |                  |           |
        //        // |2 Fast 2 Furious|June 6, 2003|4.5/5|John Singleton|Universal Pictures|Great movie|
        //        // |                |            |     |              |                   |          |
        //        // ------------------------------------------------------------------------------------
        //        //  [0]  Title      [1]ReleaseDate[2]   [3]Director       [4] Studio        [5] User rating

        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        LocalDate releaseDate = LocalDate.parse(dvdTokens[1]);
        String mpaaRating = dvdTokens[2];
        String directorName = dvdTokens[3];
        String studio = dvdTokens[4];
        String userRating = dvdTokens[5];

        DVD currentDvd = new DVD(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }
    
    public List<DVD> findMovieWithin(int year){
       LocalDate theDate = LocalDate.now().minusYears(year);
       ArrayList<DVD> dvds = new ArrayList<>(this.dvdLibrary.values());
       List<DVD> dvdWithin = dvds.stream().filter((y) -> y.getReleaseDate().
               isAfter(theDate)).collect(Collectors.toList());
        return dvdWithin;
    }

   
}
