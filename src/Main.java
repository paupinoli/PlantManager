import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Einstiegspunkt für das Plant Manager Programm.
 *
 * Dieses Programm erlaubt es Nutzern, Pflanzen mit Gießintervallen zu verwalten.
 * Funktionen zum Hinzufügen, Speichern, Laden und Erinnern an Gießtermine über die Konsole.
 *
 * Daten werden über ein {@link PlantManager} Objekt verwaltet.
 * Verarbeitet Benutzereingaben über {@link Scanner}.
 *
 */
public class Main {

    public static void main(String[] args) {
        PlantManager manager = new PlantManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen zum Pflanzen-Manager!");
        boolean running = true;

        while (running) {
            System.out.println("\nWähle eine Option:");
            System.out.println("1. Neue Pflanze anlegen");
            System.out.println("2. Pflanzen anzeigen, die heute gegossen werden müssen");
            System.out.println("3. Pflanzen speichern");
            System.out.println("4. Pflanzen laden");
            System.out.println("5. Alle Pflanzen anzeigen");
            System.out.println("6. Beenden");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Standort: ");
                    String location = scanner.nextLine();
                    System.out.print("Wasserbedarf: ");
                    String waterNeeds = scanner.nextLine();
                    System.out.print("Gießintervall (in Tagen): ");
                    int interval = Integer.parseInt(scanner.nextLine());
                    Plant plant = new Plant(name, location, waterNeeds, interval, LocalDate.now());
                    manager.addPlant(plant);
                    System.out.println("Pflanze hinzugefügt.");
                    break;

                case "2":
                    manager.remindToWaterToday();
                    break;

                case "3":
                    try {
                        manager.savePlants("plants.txt");
                        System.out.println("Pflanzen gespeichert.");
                    } catch (IOException e) {
                        System.out.println("Fehler beim Speichern: " + e.getMessage());
                    }
                    break;

                case "4":
                    try {
                        manager.loadPlants("plants.txt");
                        System.out.println("Pflanzen geladen.");
                    } catch (IOException e) {
                        System.out.println("Fehler beim Laden: " + e.getMessage());
                    }
                    break;

                case "5":
                    for (Plant p : manager.getAllPlants()) {
                        System.out.println(p);
                    }
                    break;

                case "6":
                    running = false;
                    System.out.println("Programm beendet.");
                    break;

                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }

        scanner.close();
    }
}
