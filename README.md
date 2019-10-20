PARKING ZONE
------------------------
*Test case text file:

    - First 3 lines: Cost per hour, Cost over 4 hours and Capacity

    - Each line:
        + No empty line

        + "Enter" + ";" + "time" : ID of the car will be generated and store into a List, also Enter time.
            Time is case sensitive, assuming correct input in the format "HH:MM:SS a"

        + "Exit" + ";" + "ID" + ";" + "time: ID left the Parking zone and exit time.
            Assume ID exists. If not, Console will print out the vehicle does not exist (This is impossible since ID is generated without dublicate)

    - After reading all lines (Assuming the Parking is closed):
        Report will be printed out in Console.
