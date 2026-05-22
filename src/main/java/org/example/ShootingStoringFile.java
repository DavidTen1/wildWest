package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ShootingStoringFile {

    // Removes the old file so each shootout begins with a clean protocol.
    public static void resetFile(String filePath) {
        try {
            Files.deleteIfExists(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Could not reset protocol file: " + filePath, e);
        }
    }


    public static void saveShootingRound(
            String filePath,
            int shooterID,
            int shooterIndex,
            int shooterHealth,
            int targetID,
            int targetIndex,
            int targetPrevHealthPoints,
            int damage,
            int targetNewHealth
    ) {
        Path shootingsFilePath = Path.of(filePath);
        File shootingsFile = shootingsFilePath.toFile();
        // JSON object for one shot in the shootout protocol.
        String entry = String.format(
                """
                {
                  "shooterID": %d,
                  "shooterIndex": %d,
                  "shooterHealth": %d,
                  "targetID": %d,
                  "targetIndex": %d,
                  "targetPrevHealthPoints": %d,
                  "damage": %d,
                  "targetNewHealth": %d
                }
                """,
                shooterID,
                shooterIndex,
                shooterHealth,
                targetID,
                targetIndex,
                targetPrevHealthPoints,
                damage,
                targetNewHealth
        );

        try {
            // First shot: create a new JSON file and array.
            if (!shootingsFile.exists() || shootingsFile.length() == 0) {
                String content = "[\n" + entry + "\n]";
                Files.writeString(shootingsFilePath, content, StandardCharsets.UTF_8);

            } else {
                // Further shots: read current JSON array and remove the closing bracket.
                String shootingsFileContent = Files.
                        readString(shootingsFilePath, StandardCharsets.UTF_8)
                        .trim();

                if (shootingsFileContent.endsWith("]")) {
                    shootingsFileContent = shootingsFileContent.substring(0, shootingsFileContent.length() - 1)
                            .trim();
                }
                
                // Append the new shot and close the JSON array again.
                String shootingStatusContent = shootingsFileContent + ",\n" + entry + "\n]";
                Files.writeString(shootingsFilePath, shootingStatusContent, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.out.println("Could not save shooting round: " + e.getMessage());
        }
    }
}