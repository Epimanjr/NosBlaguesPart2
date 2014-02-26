/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codebase;

import blague.Blague;
import exception.BlagueAbsenteException;

/**
 *
 * @author blaise
 */
public interface BlagueProviderPairApair {

    /**
     * récupère le nom du blagueprovider
     *
     * @return
     */
    public String getNom();

    /**
     * Retournant la liste des noms des blagues connues par le
     * BlagueProviderPairAPair
     *
     * @return
     */
    public String[] getAllNames();

    /**
     * Retournant la blague ayant pour nom le String passé en parametre.
     *
     * @param nom
     * @return
     * @throws exception.BlagueAbsenteException
     */
    public Blague getBlague(String nom) throws BlagueAbsenteException;
}
