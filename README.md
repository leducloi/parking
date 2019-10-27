PARKING ZONE
------------------------
There are 3 levels in the Parking Lots: Zone 1, Zone 2, Zone 3.

Test case text file:

    - Each line:
        + No empty line

        + "Enter" + ";" + "level=" + ";" + "time" : ID of the car will be generated and store into a List, also Enter time.
            Level is where car wants to park.
            Time is case sensitive, assuming correct input in the format "HH:MM:SS a"

        + "Exit" + ";" + "id=" + ";" + "level=" + "time: ID left the Parking zone and exit time.
            Level is where car exits.
            Assume ID exists. If not, Console will print out the vehicle does not exist (This is impossible since ID is generated without dublicate)

    - After reading all lines (Assuming the Parking is closed):
        Report will be printed out in Console
            including how many cars have parked in each Zone, and profit in each Zone.
