package com.example.rachid.smscatcher.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rachid on 22/03/17.
 */
public class ParticipationDto {

    private int numero;
    private String fete;
    private String nom;
    private String prenom;
    private String boisson;
    private String date;
    //private static int seq;

    public ParticipationDto() {
        super();
        Calendar date = Calendar.getInstance(Locale.FRENCH);
        this.date = date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"/"+date.get(Calendar.YEAR);
    }

    public ParticipationDto(String fete, String nom, String prenom, String boisson) {
        super();
        this.fete = fete;
        this.nom = nom;
        this.prenom = prenom;
        this.boisson = boisson;
        Calendar date = Calendar.getInstance(Locale.FRENCH);
        this.date = date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"/"+date.get(Calendar.YEAR);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFete() {
        return fete;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getBoisson() {
        return boisson;
    }

    public void setFete(String fete) {
        if(this.fete == null)
            this.fete = fete;
    }

    public void setNom(String nom) {
        if(this.nom == null)
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        if(this.prenom == null)
        this.prenom = prenom;
    }

    public void setBoisson(String boisson) {
        if(this.boisson == null)
        this.boisson = boisson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipationDto)) return false;

        ParticipationDto that = (ParticipationDto) o;

        if (numero != that.numero) return false;
        if (fete != null ? !fete.equals(that.fete) : that.fete != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        return boisson != null ? boisson.equals(that.boisson) : that.boisson == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (numero ^ (numero >>> 32));
        result = 31 * result + (fete != null ? fete.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (boisson != null ? boisson.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  numero+ "\t"+ fete +"\t"+ nom +"\t"+ prenom +"\t"+ boisson;
    }
}
