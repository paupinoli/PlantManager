# Plant Manager  – Ein Java-Tool zur Verwaltung von Gießintervallen

Viele Menschen vergessen, ihre Zimmerpflanzen regelmäßig zu gießen – besonders wenn verschiedene Pflanzen unterschiedliche Gießintervalle haben. Plant Manager ist ein Java-Programm, das dabei hilft, den Überblick zu behalten: Es erinnert an das Gießen und speichert alle relevanten Pflanzendaten dauerhaft.

## Features

- Neue Pflanzen anlegen (Name, Standort, Wasserbedarf, Gießintervall, letzter Gießzeitpunkt)
- Erinnerung: Welche Pflanzen müssen heute gegossen werden?
- Automatische Berechnung des nächsten Gießtermins
- Speichern und Laden der Pflanzendaten per Datei im CSV-Format
- JUnit-Test der Methode `getNextWateringDate()`
- Ausgabe von Erinnerungen über die Konsole

## Aufbau & Technik

### Hauptklassen

- `Plant`  
  Attribute: `name`, `location`, `waterNeeds`, `wateringIntervalDays`, `lastWateredDate`  
  Methoden: Getter/Setter, `getNextWateringDate()`

- `PlantManager`  
  Aufgaben: Verwaltung der Pflanzen, Gießprüfung, Speichern & Laden  
  Intern: Nutzung einer `ArrayList<Plant>`

### Technologien

- Datum & Zeichenketten: `String`, `LocalDate`
- Dateizugriff: `FileWriter`, `BufferedWriter`, `BufferedReader`, `FileReader`
- Speicherformat: CSV (Comma Separated Values)

Take care of your plants 🌿