/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.dvdlibrary.DTO;

import java.time.LocalDate;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jeff
 */

@Component
public class DVD {
     private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;

    @Autowired
    public DVD(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (!(o instanceof DVD)) return false;
        DVD dvd = (DVD) o;
        return Objects.equals(title, dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) 
                && Objects.equals(mpaaRating, dvd.mpaaRating) && Objects.equals(directorName, 
                        dvd.directorName) && Objects.equals(studio, dvd.studio) && 
                Objects.equals(userRating, dvd.userRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, mpaaRating, directorName, studio, userRating);
    }
    
    @Override
    public String toString(){
        return "Movie= " + this.title + ", release date=" + this.releaseDate + ", mpaaRating= " + this.mpaaRating + 
                ", directorName= " + this.directorName + ", studio=" + this.studio + ", userRating=" + this.userRating; 
    }
}
