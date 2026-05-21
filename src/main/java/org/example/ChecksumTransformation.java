package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChecksumTransformation {

    public static String calculateSHA512(String filePath) {
        try {
            // Read the complete protocol file as bytes.
            byte[] shootingsFileBytes = Files.readAllBytes(Path.of(filePath));

            // Create a SHA-512 digest and calculate the hash of the file bytes.
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = digest.digest(shootingsFileBytes );

            // Transform the binary hash into hexadecimal content
            return bytesToHex(hashBytes);

        }

        // error if file does not exist
        catch (IOException e) {
            throw new RuntimeException("Could not read file for checksum: " + filePath, e);
        }
        // error if hash algo does not exist
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not available", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        // Each byte becomes a combo of 2 hex characters
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        // return string of hex characters
        return hexString.toString();
    }
}