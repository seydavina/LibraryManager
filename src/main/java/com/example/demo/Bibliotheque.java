import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    public int MAX_LIVRES_EMPRUNTES = 3; // nombre max de livres empruntés par un utilisateur
    private ArrayList<Livre> listeLivres;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;

    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
    }

    // Méthode pour ajouter un livre à la bibliothèque
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
        System.out.println("Le livre " + livre.getTitre() + " a été ajouté à la bibliothèque.");
    }

    // Méthode pour supprimer un livre de la bibliothèque
    public void supprimerLivre(Livre livre) {
        if (listeLivres.contains(livre)) {
            listeLivres.remove(livre);
            System.out.println("Le livre " + livre.getTitre() + " a été supprimé de la bibliothèque.");
        } else {
            System.out.println("Le livre " + livre.getTitre() + " n'est pas présent dans la bibliothèque.");
        }
    }

    // Méthode pour rechercher un livre par titre, auteur ou ISBN
    public ArrayList<Livre> rechercherLivres(String critere) {
        ArrayList<Livre> livresTrouves = new ArrayList<>();
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(critere) || livre.getAuteur().equalsIgnoreCase(critere)
                    || livre.getISBN().equalsIgnoreCase(critere)) {
                livresTrouves.add(livre);
            }
        }
        return livresTrouves;
    }

    public boolean verifierEligibilite(Utilisateur utilisateur) {
        return utilisateur.getLivresEmpruntes().size() < MAX_LIVRES_EMPRUNTES;
    }

    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        // Vérifier l'éligibilité de l'utilisateur
        if (verifierEligibilite(utilisateur)) {
            // Vérifier si le livre est disponible dans la bibliothèque
            if (listeLivres.contains(livre)) {
                // Retirer le livre de la bibliothèque
                listeLivres.remove(livre);
                // Ajouter le livre emprunté à la liste de l'utilisateur
                utilisateur.EmpruntLivre(livre);
                // Enregistrer l'emprunt
                if (!empruntsUtilisateurs.containsKey(utilisateur)) {
                    empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
                }
                empruntsUtilisateurs.get(utilisateur).add(livre);
                System.out.println("Emprunt enregistré : " + utilisateur.getNom() + " a emprunté " + livre.getTitre());
            } else {
                System.out.println("Le livre demandé n'est pas disponible.");
            }
        } else {
            System.out.println("Vous avez atteint la limite du nombre de livres empruntés.");
        }
    }

    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        // Vérifier si l'utilisateur a emprunté ce livre
        if (empruntsUtilisateurs.containsKey(utilisateur) && empruntsUtilisateurs.get(utilisateur).contains(livre)) {
            // Retirer le livre de la liste des livres empruntés par l'utilisateur
            utilisateur.RetournerLivre(livre);
            // Ajouter le livre retourné à la bibliothèque
            listeLivres.add(livre);
            // Enregistrer le retour
            empruntsUtilisateurs.get(utilisateur).remove(livre);
            System.out.println("Retour enregistré : " + utilisateur.getNom() + " a retourné " + livre.getTitre());
        } else {
            System.out.println("Vous n'avez pas emprunté ce livre.");
        }
    }

    public void afficherStatistiques() {
        System.out.println("Statistiques de la bibliothèque :");
        System.out.println("Nombre total de livres : " + listeLivres.size());
        int totalEmprunts = 0;
        for (ArrayList<Livre> emprunts : empruntsUtilisateurs.values()) {
            totalEmprunts += emprunts.size();
        }
        System.out.println("Nombre total de livres empruntés : " + totalEmprunts);
        // Ajouter d'autres statistiques selon les besoins
    }
}