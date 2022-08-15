package dev.schulte.entities;

import java.util.Objects;

public class Constituent {

    private int constituentId;

    private String fname;

    private String lname;

    private Title title;

    public Constituent(){

    }

    public Constituent(int constituentId, String fname, String lname, Title title) {
        this.constituentId = constituentId;
        this.fname = fname;
        this.lname = lname;
        this.title = title;
    }

    public int getConstituentId() {
        return constituentId;
    }

    public void setConstituentId(int constituentId) {
        this.constituentId = constituentId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constituent that = (Constituent) o;
        return constituentId == that.constituentId && fname.equals(that.fname) && lname.equals(that.lname) && title == that.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(constituentId, fname, lname, title);
    }
}
