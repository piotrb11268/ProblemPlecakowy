package com.company;

public class Przedmiot {
    private int waga;
    private int wartosc;

    public Przedmiot(int waga, int wartosc) {
        this.waga = waga;
        this.wartosc = wartosc;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public String toString(){
        return wartosc + " zł, " + waga + " kg";
    }
}
