/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.dvdlibrary.Controller;

import com.sg.dvdlibrary.DAO.DVDLibraryDao;
import com.sg.dvdlibrary.DAO.DVDLibraryDaoException;
import com.sg.dvdlibrary.DAO.DVDLibraryDaoImpl;
import com.sg.dvdlibrary.DTO.DVD;
import com.sg.dvdlibrary.UI.DVDLibraryView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff
 */

@Component
public class DVDLibraryController {
     private DVDLibraryDaoImpl dao;
    private DVDLibraryView view;

    
    @Autowired
    public DVDLibraryController(DVDLibraryDaoImpl dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }

    public void run(){
        int menuSelection;
        boolean keepGoing = true;

        try {
            while(keepGoing){
                
                // gets the user's selection and returns it.
                menuSelection = getMenuSelection();

                switch (menuSelection){
                    
                    case 1: addDVD();
                            break;
                    case 2: removeDVD();
                            break;
                    case 3: editDVD();
                            break;
                    case 4: listAllDvds();
                            break;
                    case 5:  findDVD();
                            break;
                    case 6: keepGoing = false;
                            break;
                }
            }
            exitMenu();

        } catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        
    }
    
    
    public int getMenuSelection(){
        return view.printMenuSelection();
    }
    
    public void addDVD() throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD currentDVD = view.getDVDInformation();
        dao.addDVD(currentDVD.getTitle(),  currentDVD);
        view.displayDVDAddedSuccessBanner();
    }
    
    
    public void findDVD() throws DVDLibraryDaoException {
        int choice;
        boolean keepGoing = true;
        
        try {
            while(keepGoing){
                choice = findMenu();
                
                switch(choice){
                    case 1: findMoviesWithinYear();
                            break;
                    case 2: findAllMoviesWithMPAARating();     
                            break;
                    case 3: findMoviesByStudio();
                            break;
                    case 4: findMoviesByDirector();
                            break;
                    case 5: keepGoing = false;
                            break;
                    default : unknownCommand();
            }
                exitMenu();
        }
        } catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
            }
    }
    
    public void findAverageAgeOfMovies() throws DVDLibraryDaoException{
        
    }
    
    public void findAllMoviesWithMPAARating() throws DVDLibraryDaoException{
        String mpaaRating = view.getMPAARating();
        List<DVD> dvds = dao.getDVDByMpaaRating(mpaaRating);
        view.printAllDVDs(dvds);
    }
    
    public int findMenu(){
        return view.getFindMenuSelection();
    }
    
    public void findMoviesByDirector() throws DVDLibraryDaoException{
        String directorName = view.getDirectorName();
        List<DVD> directorDVDs = dao.getDVDByDirector(directorName);
        view.printAllDVDs(directorDVDs);
    }
    
    public void findMoviesByStudio() throws DVDLibraryDaoException{
        String studioName = view.getStudioName();
        List<DVD> studioDVDs = dao.getDVDByStudio(studioName);
        view.printAllDVDs(studioDVDs);
    }
    
   
    
    public void findMoviesWithinYear(){
        int year = view.findMovieWithinNYears();
        List<DVD> dvdsWithinNYears = this.dao.findMovieWithin(year);
        view.printAllDVDs(dvdsWithinNYears);
    }
    
    

    public void listAllDvds() throws DVDLibraryDaoException {
        ArrayList<DVD> dvdList = dao.getAllDvds();
        view.printAllDVDs(dvdList);
    }


   

    public void removeDVD() throws DVDLibraryDaoException{
        view.displayRemoveDVDBanner();
        String title = view.getDVDByTitle();
        DVD dvd = dao.removeDVD(title);
        view.displayDVDRemovedSuccessBanner();
    }

    public int editMenu(){
        return view.getEditMenuSelection();
    }

    public void editDVD() throws DVDLibraryDaoException {
        String title = view.getDVDByTitle();

        int choice;
        boolean keepGoing = true;

        try {

        while (keepGoing) {
            choice = editMenu();

            switch (choice) {
                case 1:     editReleaseDate(title);
                            break;
                case 2:     editMPAARating(title);
                            break;
                case 3:     editDirectorName(title);
                            break;
                case 4:     editStudio(title);
                            break;
                case 5:     editUserRating(title);
                            break;
                case 6:     keepGoing = false;
                            break;
                default:    unknownCommand();
            }
            exitMenu();
        }

    } catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    public void exitMenu(){
        view.displayExitMenuBanner();
    }


    public void editReleaseDate(String title) throws DVDLibraryDaoException{
        view.displayReleaseDateBanner();
        LocalDate newReleaseDate = view.changeReleaseDate();
        DVD dvd = dao.changeReleaseDate(title, newReleaseDate);
        view.newReleaseDateSuccessBanner(dvd, newReleaseDate);
    }

    public void editMPAARating(String title) throws DVDLibraryDaoException{
        view.displayEditMpaaRatingBanner();
        String newMPAARating = view.getNewMPAARating();
        DVD dvd = dao.changeMPAARating(title, newMPAARating);
        view.editMpaaSuccessBanner(dvd, newMPAARating);
    }

    public void editDirectorName(String title) throws DVDLibraryDaoException{
        view.displayEditDirectorBanner();
        String directorName = view.getNewDirectorName();
        DVD dvd = dao.changeDirectorName(title, directorName);
        view.editDirectorSuccessBanner(dvd, directorName);
    }

    public void editStudio(String title) throws DVDLibraryDaoException {
        view.editStudioBanner();
        String newStudio = view.getNewStudioName();
        DVD dvd = dao.changeStudio(title, newStudio);
        view.displayStudioChangeSuccessBanner(dvd, newStudio);
    }

    public void editUserRating(String title) throws DVDLibraryDaoException {
        view.editUserRatingBanner();
        String newUserRating = view.enterNewUserRating();
        DVD dvd = dao.changeUserRating(title, newUserRating);
        view.displayUserRatingSuccessBanner(dvd, newUserRating);
    }

    public void unknownCommand(){
        view.unknownCommandBanner();
    }

   
}
