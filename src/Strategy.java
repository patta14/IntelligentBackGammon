public interface Strategy {
    /*
    Laufspiel: möglichst hohe Würfelkombinationen (dice1+dice2) schnell am Gegner vorbeikommen und darauf achten,
    dass möglichst mindestens zwei Steine auf einem Point liegen.
     */

    /*
    Blockadespiel: mehrere Punkte hintereinander mit mindestens zwei Steinen besetzen, größtmögliche Blockade nennt sich
    Prime mit 6 aufeinanderfolgenden Points mit mindestens zwei Steinen.
     */

    /*
    Haltespiel: Steine im gegnerischen Heimfeld so positionieren, dass Möglichkeiten zum Schlagen des Gegners entstehen,
    Ankerpunkt optimal auf Point 4,5 und 6.
     */

    /*
    Angriffsspiel: Versuch die Steine des Gegners möglichst früh zu schlagen und das eigene Heimfeld zu besetzen und
    es zu blockieren.
     */

    /*
    Wichtige Points: Bar-Spitzen, 7. bzw 18. und Fünf-Punkte Spitzen, 5. bzw. 20
    Sollten diese Points mit mindestens zwei Steinen belegt sein, ist es deutlich schwerer für den Gegner zu spielen.
     */
}
