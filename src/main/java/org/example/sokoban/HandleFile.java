package org.example.sokoban;

/**
 * Rajapinta tiedostonkäsittelylle
 */
public interface HandleFile {
    /**
     *Metodi tiedostosta lukemiselle
     */
    void readFile();
    /**
     * Metodi tiedostoon kirjoittamiselle
     */
    void writeFile();
}
