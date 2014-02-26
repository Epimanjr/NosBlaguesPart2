/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blagueprovider;

import blague.Blague;
import codebase.BlagueProviderPairApair;
import exception.BlagueAbsenteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blaise
 */
public class BlagueProvider implements BlagueProviderPairApair {

    /**
     * Le nom du BlagueProvider
     */
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    private String nom;

    /**
     * Une hashmap contenant les blagues
     */
    private HashMap<String, Blague> listeBlagues;

    /**
     * le nom des autres BlagueProvider connus à leurs références distantes (ces
     * références sont à récupérer au lancement de l’application)
     */
    private HashMap<String, BlagueProvider> listeRef;

    /**
     * Constructeur.
     * @param nom
     */
    public BlagueProvider(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode qui permet d’ajouter une blague en local
     *
     * @param b
     */
    public void ajoutBlague(Blague b) {

        //On ajoute b à la hashmap
        listeBlagues.put(b.getNom(), b);
    }
    
    
    /**
     * Méthode qui télécharge une blague en l'ajoutant à la réf distante.
     * @param ref
     * @param nomBlague 
     */
    public void telechargeBlague(BlagueProvider ref, String nomBlague) {
        
        try {
            //On récupère la blague
            Blague b = getBlague(nomBlague);
            
            //On l'ajoute à la référence distance
            ref.ajoutBlague(b);
            
        } catch (BlagueAbsenteException ex) {
            Logger.getLogger(BlagueProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Retourne tous les noms des blagues
     *
     * @return
     */
    @Override
    public String[] getAllNames() {
        //Création du tableau résultat
        String[] res = new String[listeBlagues.size()];

        //Initialisation iterateur
        Set cles = listeBlagues.keySet();
        Iterator it = cles.iterator();
        int iterateurRes = 0;

        //Parcourt de la hashmap
        while (it.hasNext()) {
            //Récupère la clé
            String cle = (String) it.next();

            //Ajout de la clé au tableau
            res[iterateurRes] = cle;
            iterateurRes++;
        }

        return res;
    }

    /**
     * Méthode qui construit return l'objet blague à partir de son nom.
     *
     * @param nom
     * @return
     * @throws BlagueAbsenteException
     */
    @Override
    public Blague getBlague(String nom) throws BlagueAbsenteException {

        //On vérifie si la clé existe dans la hashmap
        if (listeBlagues.containsKey(nom)) {
            //On récupère la valeur
            Blague blague = listeBlagues.get(nom);
            return blague;
        } else {
            //On lève l'exception
            throw new BlagueAbsenteException();
        }

    }
    
    
    /**
     * Méthode principale.
     *
     * @param args 
     */
    public static void main(String[] args) {
        //On test args
        if(args.length < 1) {
            System.out.println("Erreur : Manque un argument !");
        } else {
            //Création de l'objet
            BlagueProvider bp = new BlagueProvider(args[0]);
        }
    }

}
