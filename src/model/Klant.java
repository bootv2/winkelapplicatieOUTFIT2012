package model;

public class Klant {
    private int id;
    private String voornaam;
    private String achternaam;
    private String adres;
    private String postcode;
    private String woonplaats;
    private String telefoon;
    private String email;
    private String notities;
    
    public Klant() {
        
    }
    
    public Klant(int id, String voornaam, String achternaam, String adres, String postcode, String woonplaats, String telefoon, String email, String notities) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.telefoon = telefoon;
        this.email = email;
        this.notities = notities;
    }
    
    public Klant(String voornaam, String achternaam, String adres, String postcode, String woonplaats) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }

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
     * @return the adres
     */
    public String getAdres() {
        return adres;
    }

    /**
     * @param adres the adres to set
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the woonplaats
     */
    public String getWoonplaats() {
        return woonplaats;
    }

    /**
     * @param woonplaats the woonplaats to set
     */
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    /**
     * @return the voornaam
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * @param voornaam the voornaam to set
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * @return the achternaam
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * @param achternaam the achternaam to set
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * @return the telefoon
     */
    public String getTelefoon() {
        return telefoon;
    }

    /**
     * @param telefoon the telefoon to set
     */
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the notities
     */
    public String getNotities() {
        return notities;
    }

    /**
     * @param notities the notities to set
     */
    public void setNotities(String notities) {
        this.notities = notities;
    }

    public String getNaam() {
        return voornaam + " " + achternaam;
    }
}
