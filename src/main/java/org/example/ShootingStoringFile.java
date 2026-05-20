package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ShootingStoringFile {
    public static void saveShootingRound(
            String filePath,
            int shooterID,
            int shooterIndex,
            int targetID,
            int targetIndex,
            int damage,
            int targetNewHealth
    ) {
        Path path = Path.of(filePath);
        File file = path.toFile();

        String entry = String.format(
                """
                {
                  "shooterID": %d,
                  "shooterIndex": %d,
                  "targetID": %d,
                  "targetIndex": %d,
                  "damage": %d,
                  "targetNewHealth": %d
                }
                """,
                shooterID,
                shooterIndex,
                targetID,
                targetIndex,
                damage,
                targetNewHealth
        );

        try {
            if (!file.exists() || file.length() == 0) {
                String content = "[\n" + entry + "\n]";

                Files.writeString(
                        path,
                        content,
                        StandardCharsets.UTF_8
                );
            } else {
                String content = Files.readString(
                        path,
                        StandardCharsets.UTF_8
                ).trim();

                if (content.endsWith("]")) {
                    content = content.substring(0, content.length() - 1).trim();
                }

                String newContent = content + ",\n" + entry + "\n]";

                Files.writeString(
                        path,
                        newContent,
                        StandardCharsets.UTF_8
                );
            }
        } catch (IOException e) {
            System.out.println("Could not save shooting round: " + e.getMessage());
        }
    }
}