/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.dvdlibrary.UI;

import com.sg.dvdlibrary.DTO.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff
 */

@Component
public class DVDLibraryView {
    private UserIoConsoleImpl io;
    
    
    // uses Dependency injection. 
    @Autowired
    public DVDLibraryView(UserIoConsoleImpl io){
        this.io = io;
    }
    
    
     public int printMenuSelection(){
        io.print("Initial Menu: ");
        io.print("\t\t1. Add a DVD.");
        io.print("\t\t2. Remove a DVD.");
        io.print("\t\t3. Edit a DVD.");
        io.print("\t\t4. List All DVDs.");
        io.print("\t\t5. Find DVD: ");
        io.print("\t\t6. Exit.");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
     
     public int getFindMenuSelection(){
         io.print("\t\t1. Find all movies in the last N years.");
         io.print("\t\t2. Find all movies with a given MPAA Rating.");
         io.print("\t\t3. Find all movies by studio.");
         io.print("\t\t4. Find all movies by director.");
         io.print("\t\t5. Exit");
         
         return io.readInt("Please select from the choices above.", 1, 5);
     }
     
     public int findMovieWithinNYears(){
         return io.readInt("Find movies within N years. Enter N: ");
     }
     
     public DVD getDVDInformation(){
        String title = io.readString("Enter movie title: ");
        String releaseDate = io.readString("Enter release date (YYYY/MM/DD): ");
        String mpaaRating = getMPAARating();
        String directorName = io.readString("Enter Director's Name: ");
        String studio = io.readString("Enter studio name: ");
        String userRating = io.readString("Enter User rating or note: ");


        DVD dvd = new DVD(title);

        dvd.setReleaseDate(parseReleaseDate(releaseDate));
        dvd.setMpaaRating(mpaaRating);
        dvd.setDirectorName(directorName);
        dvd.setStudio(studio);
        dvd.setUserRating(userRating);
        return dvd;
    }
     
     public LocalDate changeReleaseDate(){
        String releaseDate = io.readString("Enter new release date (YYYY/MM/DD):");
        LocalDate date = parseReleaseDate(releaseDate);
        return date;
    }

    public LocalDate parseReleaseDate(String releaseDate){
        LocalDate date = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return date;
    }

    public String getDVDByTitle(){
       return io.readString("Enter the title of the DVD: ");
    }

    public int getEditMenuSelection(){
        io.print("Select field you would like to edit: ");
        io.print("Edit DVD Menu: ");
        io.print("\t\t1. Edit release year. ");
        io.print("\t\t2. Edit MPAA Rating. ");
        io.print("\t\t3. Edit Director's name.");
        io.print("\t\t4. Enter studio name.");
        io.print("\t\t5. Edit user rating.");
        io.print("\t\t6. Exit Program. ");

        return io.readInt("Please select from the above choices", 1, 6);
    }

    public void editStudioBanner(){
        io.print("=== EDIT STUDIO ===");
    }
    
    public String getDirectorName(){
       return io.readString("Enter Director name: ");
    }
    
    public String getStudioName(){
        return io.readString("Enter Studio Name: ");
    }
    
    public String getMPAARating(){
        io.print("List of MPAA Ratings: ");
        io.print("\t\t1. G");
        io.print("\t\t2. PG");
        io.print("\t\t3. R");
        io.print("\t\t4. X");
        int choice = io.readInt("Please select from the above choices", 1, 4);
        
        String mpaa = "";
        
        switch(choice){
            case 1: mpaa = "G";
            case 2: mpaa =  "PG";
            case 3: mpaa = "R";
            case 4: mpaa = "X";
        }
        
        return mpaa;
    }

    public void unknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    public void displayExitMenuBanner(){
        io.print("GoodBye!!!");
    }

    public String getNewStudioName(){
        return io.readString("Enter new studio name: ");
    }

    public void displayStudioChangeSuccessBanner(DVD dvd, String newStudio){
        if (dvd.getStudio().equalsIgnoreCase(newStudio)){
            io.print("=== Studio Changed Successfully");
        } else {
            io.print("Studio Change Unsuccessful");
        }
    }

    public void editUserRatingBanner(){
        io.print("=== EDIT USER RATING ===");
    }

    public String enterNewUserRating(){
        return io.readString("Enter new rating: ");
    }

    public void displayUserRatingSuccessBanner(DVD dvd, String userRating){
        if (dvd.getStudio().equalsIgnoreCase(userRating)){
            io.print("User Rating Updated.");
        } else {
            io.print("User Rating Update Unsuccessful.");
        }
    }

    public void displayDVDAddedSuccessBanner(){
        io.print("DVD added successfully.");
        displayMenu();
    }

    public void displayEditDirectorBanner(){
        io.print("Edit DVD Director.");
        displayMenu();
    }

    public void displayDVDRemovedSuccessBanner(){
        io.print("DVD removed successfully.");
        displayMenu();
    }



    public void newReleaseDateSuccessBanner(DVD dvd, LocalDate newReleaseDate){
        if (dvd.getReleaseDate().equals(newReleaseDate)){
            io.print("=== Release Date Successfully updated.");
        } else {
            io.print("=== Release Date Update Unsuccessful.");
        }
    }



    public void displayAddDVDBanner(){
        io.print("=== Add DVD ===");
    }

    public void displayRemoveDVDBanner(){
        io.print("=== Delete DVD ===");
    }

    public void displayEditDVDBanner(){
        io.print("=== Edit DVD Menu ===");
    }


    public void displayAllDVDsBanner(){ io.print("===Display aLL DVDs ===");}

    public void displayDVDBanner(){ io.print("=== Display DVD ===");}


    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayMenu(){
        io.readString("Please 1 to go to Main Menu.");
    }

    public void dvdCountBanner(){
        io.print("List DVD Count Menu: ");
    }

    public void printDVDLibrary(int size){
        if (size > 1){
            io.print("There are " + size + " DVDs in the library.");
        } else {
            io.print("There is " + size + " DVD in the library.");
        }
        displayMenu();
    }


    public void displayReleaseDateBanner(){
        io.print("=== Edit DVD Release Date ===");
    }

    public void displayEditMpaaRatingBanner(){
        io.print("=== Edit MPAA Rating ====");
    }

    public String getNewMPAARating(){
       return io.readString("Enter new MPAA Rating: ");
    }

    public void editMpaaSuccessBanner(DVD dvd, String newMpaaRating){
        if (dvd.getMpaaRating().equalsIgnoreCase(newMpaaRating)){
            io.print("=== MPAA Rating Successfully Updated ===");
        } else {
            io.print("=== MPAA Rating Update Unsuccessful. ===");
        }
    }

    public void displayEditDirectorNameBanner(){
        io.print("=== Edit Director's Name ===");
    }

    public String getNewDirectorName(){
        return io.readString("Enter new Director name: ");
    }

    public void editDirectorSuccessBanner(DVD dvd, String directName){
        if (dvd.getDirectorName().equalsIgnoreCase(directName)){
            io.print("=== Director's name Successfully Updated");
        } else {
            io.print("=== Director's name Update Unsuccessful. ");
        }
        displayMenu();
    }

    public String printReleaseDate(LocalDate localDate){
        String date = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return date;
    }

    public void printAllDVDs(List<DVD> dvd){
        displayAllDVDsBanner();
        for(int i = 0; i < dvd.size(); i++){
            System.out.println("Movie: " + dvd.get(i).getTitle() );
            System.out.println("\t\tRelease year: " + printReleaseDate(dvd.get(i).getReleaseDate()));
            System.out.println("\t\tMPAA Rating: " + dvd.get(i).getMpaaRating());
            System.out.println("\t\tDirector's name: " + dvd.get(i).getDirectorName());
            System.out.println("\t\tStudio: " + dvd.get(i).getStudio());
            System.out.println("\t\tUser Rating: " + dvd.get(i).getUserRating());
        }
        System.out.println();
    }

    public void printDVD(DVD dvd){
        System.out.println("Movie Information: ");
        System.out.println("\t\tMovie: " + dvd.getTitle());
        System.out.println("\t\tRelease Date: " + printReleaseDate(dvd.getReleaseDate()));
        System.out.println("\t\tMPAA Rating: " + dvd.getMpaaRating());
        System.out.println("\t\tDirector's name: " + dvd.getDirectorName());
        System.out.println("\t\tStudio: " + dvd.getStudio());
        System.out.println("\t\tUser Rating: " + dvd.getUserRating());
    }
     
     
}
