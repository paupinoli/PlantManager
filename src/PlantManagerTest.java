import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für die {@link Plant}- und {@link PlantManager}-Klassen.
 *
 * Diese Klasse enthält Unit-Tests zur Überprüfung der Berechnung des Gießzeitpunkts,
 * insbesondere der Methoden {@link Plant#getNextWateringDate()} und
 * {@link PlantManager#getPlantsToWaterToday()}.
 *
 * Verwendung von JUnit 5.
 */
public class PlantManagerTest {

    /**
     * Testet, ob {@code getNextWateringDate()} das korrekte Gießdatum zurückgibt
     */
    @Test
    public void testNextWateringDate() {
        Plant plant = new Plant("Monstera", "Fenster", "Hoch", 3, LocalDate.of(2025, 5, 1));
        LocalDate expected = LocalDate.of(2025, 5, 4);
        assertEquals(expected, plant.getNextWateringDate(),
                "Das nächste Gießdatum sollte korrekt berechnet werden.");
    }

    /**
     * Testet, ob {@code getPlantsToWaterToday()} alle Pflanzen zurückgibt,
     * deren nächstes Gießdatum heute oder in der Vergangenheit liegt.
     *
     * Es werden drei Pflanzen erstellt:
     * - Eine Pflanze, die vor 5 Tagen zuletzt gegossen wurde (sollte fällig sein).
     * - Eine Pflanze, die heute zuletzt gegossen wurde (sollte heute fällig sein).
     * - Eine Pflanze, deren nächstes Gießdatum in der Zukunft liegt (sollte nicht erscheinen).
     *
     */
    @Test
    public void testGetPlantsToWaterToday() {
        PlantManager manager = new PlantManager();

        Plant plant1 = new Plant("Aloe Vera", "Küche", "Mittel", 5, LocalDate.now().minusDays(5));

        Plant plant2 = new Plant("Basilikum", "Balkon", "Hoch", 0, LocalDate.now());

        Plant plant3 = new Plant("Ficus", "Wohnzimmer", "Niedrig", 7, LocalDate.now());

        manager.addPlant(plant1);
        manager.addPlant(plant2);
        manager.addPlant(plant3);

        List<Plant> toWater = manager.getPlantsToWaterToday();

        assertEquals(2, toWater.size(), "Es sollten zwei Pflanzen zum Gießen fällig sein.");
        assertTrue(toWater.contains(plant1), "Aloe Vera sollte gegossen werden.");
        assertTrue(toWater.contains(plant2), "Basilikum sollte heute gegossen werden.");
        assertFalse(toWater.contains(plant3), "Ficus sollte nicht gegossen werden.");
    }
}
