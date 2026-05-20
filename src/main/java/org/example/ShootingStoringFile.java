package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ShootingStoringFile {
    public static void saveShootingRound(
            String filePath,
            int shooterID,
            int shooterIndex,
            int targetID,
            int targetIndex,
            int targetPrevHealth,
            int damage,
            int targetNewHealth
    ) {
        File file = new File(filePath);

        String entry = String.format(
                """
                {
                  "shooterID": %d,
                  "shooterIndex": %d,
                  "targetID": %d,
                  "targetIndex": %d,
                  "targetPrevHealth": %d,
                  "damage": %d,
                  "targetNewHealth": %d
                }
                """,
                shooterID,
                shooterIndex,
                targetID,
                targetIndex,
                targetPrevHealth,
                damage,
                targetNewHealth
        );

        try {
            if (!file.exists() || file.length() == 0) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[\n");
                    writer.write(entry);
                    writer.write("\n]");
                }
            } else {
                String content = Files.readString(file.toPath()).trim();

                // Remove the final closing bracket ]
                if (content.endsWith("]")) {
                    content = content.substring(0, content.length() - 1).trim();
                }

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(content);
                    writer.write(",\n");
                    writer.write(entry);
                    writer.write("\n]");
                }
            }
        } catch (IOException e) {
            System.out.println("Could not save shooting round: " + e.getMessage());
        }
    }
}
