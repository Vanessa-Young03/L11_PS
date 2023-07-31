package sg.edu.rp.c346.id22038532.l11_ps;

import java.io.Serializable;

public class Movies implements Serializable{
    private int id;
        private String title;
        private String genre;
        private int years;
        private int rating;

    public Movies(int id, String title, String genre, int years, int rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.years = years;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
