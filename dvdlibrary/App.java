/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.Controller.DVDLibraryController;
import com.sg.dvdlibrary.DAO.DVDLibraryDao;
import com.sg.dvdlibrary.DAO.DVDLibraryDaoImpl;
import com.sg.dvdlibrary.UI.DVDLibraryView;
import com.sg.dvdlibrary.UI.UserIO;
import com.sg.dvdlibrary.UI.UserIoConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jeff
 */
public class App {
    public static void main(String[] args){
        
        UserIoConsoleImpl io = new UserIoConsoleImpl();
        DVDLibraryView view = new DVDLibraryView(io);
        DVDLibraryDaoImpl dao = new DVDLibraryDaoImpl();
        DVDLibraryController controller = new DVDLibraryController(dao, view);
        controller.run();
        
    }
}
