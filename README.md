## Manual

1.Run the program by selecting `Main.java` in IntelliJ IDEA and clicking the **Play** button.

2.After the program starts, enter an integer number in the console. This number defines the amount of cowboys participating in the shootout.

3.The shootout is simulated automatically. Each shot is printed to the console and also written to a JSON protocol file in the root directory of the project.

4.The JSON protocol contains the shooter ID, target ID, shooter health, target previous health, damage, target remaining health, and the current indexes of shooter and target.

5.When only one cowboy remains, the winner is printed to the console. Afterwards, a SHA-512 checksum of the JSON protocol file is calculated and printed.

6.To start a new simulation, run `Main.java` again by clicking the **Play** button and entering a new number of cowboys.