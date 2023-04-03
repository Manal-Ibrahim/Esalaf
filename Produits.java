package ma.fstt.javaprojet;

public class Produits {
    private int id;
    private String nom;
    private int quantitestock;
    private float prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantitestock() {
        return quantitestock;
    }

    public void setQuantitestock(int quantitestock) {
        this.quantitestock = quantitestock;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
