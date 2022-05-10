/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.dvdlibrary.DAO;

import com.sg.dvdlibrary.DTO.DVD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeff
 */
public interface DVDLibraryDao {
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    ArrayList<DVD> getAllDvds() throws DVDLibraryDaoException;

    DVD getDVD(String title) throws DVDLibraryDaoException;

    DVD removeDVD(String title) throws DVDLibraryDaoException;


    DVD changeReleaseDate(String title, LocalDate newLocalDate) throws DVDLibraryDaoException;
    DVD changeMPAARating(String title, String mpaaRating) throws DVDLibraryDaoException;
    DVD changeDirectorName(String title, String directorName) throws DVDLibraryDaoException;
    DVD changeStudio(String title, String studio) throws DVDLibraryDaoException;
    DVD changeUserRating(String title, String userRating) throws DVDLibraryDaoException;

    List<DVD> getDVDByDirector(String directorName) throws DVDLibraryDaoException;
    List<DVD> getDVDByStudio(String studio) throws DVDLibraryDaoException;
    List<DVD> getDVDByMpaaRating(String MppaRating) throws DVDLibraryDaoException;
}
