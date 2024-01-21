package projeto_ed.MapsManagement;

import projeto_ed.Game.Mapa;

public interface Exporter {

    /**
     * Saves a Mapa object to a text file and throws an IllegalArgumentException in case the map is empty.
     *
     * @param map The Mapa object representing the map to be saved.
     * @param fileName A path specifying the name and location of the file.
     */
    public void saveMapToFile(Mapa map, String fileName);
}
