/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.dvdlibrary.DAO;

import com.sg.dvdlibrary.DTO.DVD;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jeff
 */
public class DVDLibraryDaoImplTest {
    
   DVDLibraryDao testDao;
    
    public DVDLibraryDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "testlibrary.txt";
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testAddDvd() throws IOException, DVDLibraryDaoException{
        String movieTitle = "Fast and Furious";
        DVD dvd = new DVD(movieTitle);
        
        dvd.setDirectorName("John Singleton");
        dvd.setReleaseDate(LocalDate.parse("2006/06/06"));
        dvd.setStudio("Universal Pictures");
        dvd.setMpaaRating("PG-13");
        dvd.setUserRating("Great movie!");
        
        // Add the DVD to the test DAO. 
        testDao.addDVD(movieTitle, dvd);
        
        //Get the DVD from the testDAO
        DVD retrievedDVD = testDao.getDVD(movieTitle);
        
        //Check if the data is equal
        assertEquals(dvd, retrievedDVD);
        
    }
    
    @Test 
    public void testGetAllDVDs() throws IOException, DVDLibraryDaoException{
        String movieTitle = "Fast and Furious";
        DVD dvd = new DVD(movieTitle);
        
        dvd.setDirectorName("John Singleton");
        dvd.setReleaseDate(LocalDate.parse("2006/06/06"));
        dvd.setStudio("Universal Pictures");
        dvd.setMpaaRating("PG-13");
        dvd.setUserRating("Great movie!");
        
        
        String movie2 = "Uncharted: Drake's Fortune";
        DVD dvd2 = new DVD(movie2);
        dvd2.setDirectorName("Ruben Fleischer");
        dvd2.setReleaseDate(LocalDate.parse("2022/02/18"));
        dvd2.setMpaaRating("PG-13");
        dvd2.setStudio("Columbia Pictures PlayStation Productions");
        dvd2.setUserRating("Great movie. Been a fan since playing unchartered on my ps3.");
        
        // Add the DVD to the test DAO. 
        testDao.addDVD(movieTitle, dvd);
        testDao.addDVD(movie2, dvd2);
        
        List<DVD> allDVDs = testDao.getAllDvds();
        
        // checks whether the two DVDs have been saved. 
        assertEquals(2, allDVDs.size());
        assertNotNull(allDVDs, "The List of DVDs must not null");
        
        
        // test the specifics
        assertTrue(testDao.getAllDvds().contains(dvd));
        assertTrue(testDao.getAllDvds().contains(movie2));
    }
    
    public void testRemoveDVD() throws DVDLibraryDaoException{
        String movieTitle = "Fast and Furious";
        DVD dvd = new DVD(movieTitle);
        
        dvd.setDirectorName("John Singleton");
        dvd.setReleaseDate(LocalDate.parse("2006/06/06", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd.setStudio("Universal Pictures");
        dvd.setMpaaRating("PG-13");
        dvd.setUserRating("Great movie!");
        
        
        String movie2 = "Uncharted: Drake's Fortune";
        DVD dvd2 = new DVD(movie2);
        dvd2.setDirectorName("Ruben Fleischer");
        dvd2.setReleaseDate(LocalDate.parse("2022/02/18", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setMpaaRating("PG-13");
        dvd2.setStudio("Columbia Pictures PlayStation Productions");
        dvd2.setUserRating("Great movie. Been a fan since playing unchartered on my ps3.");
        
        // Add the DVD to the test DAO. 
        testDao.addDVD(movieTitle, dvd);
        testDao.addDVD(movie2, dvd2);
        
        DVD deletedDVD = testDao.removeDVD(movieTitle);
        
        // checks if deleted DVD is the same as the first DVD added. 
        assertEquals(dvd, deletedDVD);
        
        List<DVD> allDVDs = testDao.getAllDvds();
        
        assertEquals(1, allDVDs.size());
        assertNotNull(allDVDs, "All DVDs list should not be null");
        
        DVD removeMovie2 = testDao.removeDVD(movie2);
        assertEquals(dvd2, removeMovie2, "The name should be Uncharted: Drake's Fortune");
        
        allDVDs = testDao.getAllDvds();
        
        assertTrue(allDVDs.isEmpty(), "The retrieved list of DVDs should be empty");
        
        
    }
    
    public void testChangeDirector() throws DVDLibraryDaoException{
        String movieTitle = "Fast and Furious";
        DVD dvd = new DVD(movieTitle);
        
        dvd.setDirectorName("John Singleton");
        dvd.setReleaseDate(LocalDate.parse("2006/06/06", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd.setStudio("Universal Pictures");
        dvd.setMpaaRating("PG-13");
        dvd.setUserRating("Great movie!");
        
        testDao.addDVD(movieTitle, dvd);
        
        // check if current director's namw is John Singleton. 
        
        assertEquals(testDao.getDVD(movieTitle).getDirectorName(), dvd.getDirectorName());
        
        // Change director's name. 
        String newDirectorrName = "Jefferson Agyekum";
        testDao.getDVD(movieTitle).setDirectorName(newDirectorrName);
        
        // check if new director's name is Jefferson Agyekum
        assertEquals(testDao.getDVD(movieTitle).getDirectorName(), newDirectorrName);
    }
    
}
