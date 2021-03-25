Dynamic Weather Interpolator System (DWIS)

This Java program opens a window with a user interface in it for inputting a location to get the mesonet data interpolated in one or more ways speficied by the respective radio buttons selected.
Once the data is interpolated, the program generates a Grafana dashboard which can be viewed in any browser on port 3000 (type in "localhost:3000" as the URL) provided Grafana is installed properly. 

Class files
  * GUIBuild: creates the user interface for entering location data and selecting interpolation methods. Also includes event handling.
  * NearestNeighbor: Calculates the nearest station to the inputed data and generates a dashboard with that stations weather data.
  * Station: Represents a single station and the associated data it produces.
  * ScriptLaunchPad: intended to launch any neccessary shell scripts to accomplish the programs objective (will possibly be scrapped).

mesonetWebScrape.py: python program that scrapes data from the mesonet website and creates an API endpoint to collect data from for the interpolator (java) program
