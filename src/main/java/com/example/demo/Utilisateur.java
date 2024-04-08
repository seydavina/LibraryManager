import java.util.ArrayList;

public class Utilisateur {
    private String nom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;

    public Utilisateur(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
    }

    ArrayList<Livre> getLivresEmpruntes() {
        return this.livresEmpruntes;
    }

    String getNom() {
        return this.nom;
    }

    // Méthode pour emprunter un livre
    public void EmpruntLivre(Livre livre) {
        livresEmpruntes.add(livre);
        System.out.println("Le livre " + livre.getTitre() + " a été emprunté par " + this.nom);
    }

    // Méthode pour retourner un livre
    public void RetournerLivre(Livre livre) {
        if (livresEmpruntes.contains(livre)) {
            livresEmpruntes.remove(livre);
            System.out.println("Le livre " + livre.getTitre() + " a été retourné.");
        } else {
            System.out.println("Ce livre n'est pas emprunté par " + this.nom);
        }
    }

    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        if (livresEmpruntes.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
        } else {
            System.out.println("Livres empruntés par " + this.nom + ":");
            for (Livre livre : livresEmpruntes) {
                System.out.println("- " + livre.getTitre());
            }
        }
    }
}
