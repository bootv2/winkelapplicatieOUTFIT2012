/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sarfaraaz
 */
public class Order {
    private int id;
    private Klant klant;
    private String betaalmethode;
    private String datum;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the klant
     */
    public Klant getKlant() {
        return klant;
    }

    /**
     * @param klant the klant to set
     */
    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    /**
     * @return the betaalmethode
     */
    public String getBetaalmethode() {
        return betaalmethode;
    }

    /**
     * @param betaalmethode the betaalmethode to set
     */
    public void setBetaalmethode(String betaalmethode) {
        this.betaalmethode = betaalmethode;
    }

    /**
     * @return the datum
     */
    public String getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(String datum) {
        this.datum = datum;
    }
}
