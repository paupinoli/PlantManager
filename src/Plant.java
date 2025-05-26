import java.io.Serializable;
import java.time.LocalDate;

/**
 * Die Klasse {@code Plant} repräsentiert eine einzelne Zimmerpflanze mit relevanten Informationen:
 * Name, Standort, Wasserbedarf, Gießintervall und dem letzten Gießdatum.
 *
 * Die Objekte dieser Klasse können gespeichert werden, da sie das {@link Serializable} Interface implementieren.
 *
 * Die Methode {@link #getNextWateringDate()} bildet die Kernlogik für das Erinnern an den nächsten Gießtermin.
 *
 * Es gibt ausschließlich einen Setter für {@code lastWateredDate}, um dieses Datum nach dem Gießen zu aktualisieren.
 * Alle anderen Felder sind unveränderlich.
 */
public class Plant implements Serializable {
    private String name;
    private String location;
    private String waterNeeds;
    private int wateringIntervalDays;
    private LocalDate lastWateredDate;

    /**
     * Erstellt ein neues Pflanzenobjekt mit allen erforderlichen Angaben.
     *
     * @param name Name der Pflanze
     * @param location Standort der Pflanze
     * @param waterNeeds Wasserbedarf
     * @param wateringIntervalDays Gießintervall in Tagen
     * @param lastWateredDate Datum, an dem die Pflanze zuletzt gegossen wurde
     */
    public Plant(String name, String location, String waterNeeds, int wateringIntervalDays, LocalDate lastWateredDate) {
        this.name = name;
        this.location = location;
        this.waterNeeds = waterNeeds;
        this.wateringIntervalDays = wateringIntervalDays;
        this.lastWateredDate = lastWateredDate;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getWaterNeeds() {
        return waterNeeds;
    }

    public int getWateringIntervalDays() {
        return wateringIntervalDays;
    }

    public LocalDate getLastWateredDate() {
        return lastWateredDate;
    }

    public void setLastWateredDate(LocalDate lastWateredDate) {
        this.lastWateredDate = lastWateredDate;
    }

    /**
     * Berechnet den nächsten Gießtermin basierend auf dem letzten Gießdatum
     * und dem definierten Gießintervall.
     *
     * @return Das nächste Gießdatum
     */
    public LocalDate getNextWateringDate() {
        return lastWateredDate.plusDays(wateringIntervalDays);
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", waterNeeds='" + waterNeeds + '\'' +
                ", wateringIntervalDays=" + wateringIntervalDays +
                ", lastWateredDate=" + lastWateredDate +
                '}';
    }
}
