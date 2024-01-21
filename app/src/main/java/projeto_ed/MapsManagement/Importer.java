package projeto_ed.MapsManagement;

import projeto_ed.Game.Mapa;

public interface Importer {

    /**
     * Load a map from a file.
     *
     * @param fileName The name of the file containing map details.
     * @return A new Mapa instance based on the information in the file.
     */
    public Mapa loadMapFromFile(String fileName);
}