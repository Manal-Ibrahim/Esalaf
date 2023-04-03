package ma.fstt.javaprojet;

public class Credit {
    private int id;
    private String client;
    private  Float prix;
    private String datecredit;
    private String datefin;
    private String etat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDatecredit() {
        return datecredit;
    }

    public void setDatecredit(String datecredit) {
        this.datecredit = datecredit;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
