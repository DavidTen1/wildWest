## Manual

1. Run the program by selecting `Main.java` in IntelliJ IDEA and clicking the **Play** button.

2. After the program starts, enter an integer number in the console. This number defines how many cowboys participate in the shootout.

3. The shootout is simulated automatically. Each shot is printed to the console and written to a JSON protocol file in the project root directory.

4. The JSON protocol contains the shooter's  ID, target's ID, shooter's  health, target's  previous health, damage, 
 target's  remaining health, and the current indexes of both the cowboys.

5. When only one cowboy remains, the winner is printed to the console. 
Afterwards, a SHA-512 checksum of the JSON protocol file is calculated, printed, and stored in a separate checksum file.

6. When the application starts again, the previous protocol file is checked against the stored checksum. 
   If the values differ, the protocol file may have been changed or no checksum exists.

7. To start a new simulation, run `Main.java` again by clicking the **Play** button and entering a new number of cowboys.