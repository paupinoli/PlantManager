import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse {@code PlantManager} verwaltet eine Sammlung von {@link Plant}-Objekten.
 *
 * Methoden zum Hinzufügen neuer Pflanzen, zum Speichern und Laden aus Dateien sowie
 * zur Ermittlung von Pflanzen, die am aktuellen Tag gegossen werden müssen.
 *
 * Die Pflanzen werden intern in einer {@code ArrayList<Plant>} gespeichert.
 *
 */
public class PlantManager {

    private List<Plant> plants;

    /**
     * Erstellt einen neuen leeren {@code PlantManager}.
     */
    public PlantManager() {
        plants = new ArrayList<>();
    }

    /**
     * Fügt eine neue Pflanze zur internen Liste hinzu.
     *
     * @param plant Die hinzuzufügende Pflanze
     */
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    /**
     * Gibt eine Liste aller Pflanzen zurück, deren nächster Gießtermin heute oder früher ist.
     *
     * @return Liste der zu gießenden Pflanzen
     */
    public List<Plant> getPlantsToWaterToday() {
        List<Plant> toWater = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Plant plant : plants) {
            if (!plant.getNextWateringDate().isAfter(today)) {
                toWater.add(plant);
            }
        }
        return toWater;
    }

    /**
     * Speichert alle Pflanzen in einer Textdatei im CSV-Format.
     *
     * @param filename Der Name der Zieldatei
     * @throws IOException Wenn beim Schreiben ein Fehler auftritt
     */
    public void savePlants(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Plant plant : plants) {
                writer.write(plant.getName() + "," +
                        plant.getLocation() + "," +
                        plant.getWaterNeeds() + "," +
                        plant.getWateringIntervalDays() + "," +
                        plant.getLastWateredDate());
                writer.newLine();
            }
        }
    }

    /**
     * Lädt Pflanzendaten aus einer CSV-Datei und ersetzt die aktuelle Pflanzenliste.
     *
     * @param filename Der Name der Quelldatei
     * @throws IOException Wenn beim Lesen ein Fehler auftritt
     */
    public void loadPlants(String filename) throws IOException {
        plants.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Plant plant = new Plant(
                            parts[0],
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3]),
                            LocalDate.parse(parts[4])
                    );
                    plants.add(plant);
                }
            }
        }
    }

    /**
     * Gibt in der Konsole aus, welche Pflanzen heute gegossen werden müssen.
     * Falls keine Pflanze fällig ist, wird eine Meldung ausgegeben.
     */
    public void remindToWaterToday() {
        List<Plant> toWater = getPlantsToWaterToday();
        if (toWater.isEmpty()) {
            System.out.println("Keine Pflanzen müssen heute gegossen werden.");
        } else {
            System.out.println("Heute gießen:");
            for (Plant plant : toWater) {
                System.out.println("- " + plant.getName());
            }
        }
    }

    /**
     * Gibt die vollständige Liste aller gespeicherten Pflanzen zurück.
     *
     * @return Liste aller Pflanzen
     */
    public List<Plant> getAllPlants() {
        return plants;
    }
}
