package projeto_ed.MapsManagement;

import projeto_ed.Game.Mapa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapExporter implements Exporter{

    public void saveMapToFile(Mapa map, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            if(!map.isEmpty()) {
                writer.write(map.size() + "\n");
                for (int i = 0; i < map.size(); i++) {
                    for (int j = 0; j < map.size(); j++) {
                        double weight = map.getAdjMatrix()[i][j];
                        if (weight > 0) {
                            writer.write( (i + 1) + "," + (j + 1) + "," + weight + "\n");
                        }
                    }
                }
            }else{
                throw new IllegalArgumentException("This map is empty");
            }
        } catch (IOException e) {
            System.err.println("Error saving map to file: " + e.getMessage());
        }
    }
}
