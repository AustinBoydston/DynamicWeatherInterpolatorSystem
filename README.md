# Dynamic Weather Interpolator System (DWIS)

#### This Java program opens a window with a user interface in it for inputting a location to get the mesonet data interpolated in one or more ways speficied by the respective radio buttons selected.
Once the data is interpolated, the program generates a Grafana dashboard which can be viewed in any browser on port 3000 (type in "localhost:3000" as the URL).

## Installation
### Download the files as a zip or with git clone
### Install Maven
   * Guide: https://maven.apache.org/install.html
### Maven build (optional)
   * run the following commands from the project root directory to build the project:
   * ``` mvnd compile ```
   * ``` mvnd package ```
### Download and Install Python
   * Download at: https://www.python.org/downloads/
   * After install open a command line window and run the command
   * ``` pip install beautifulsoup4 ```
#### Run the python file
   * navigate to /WebScrapePythonCode directory
   * run the command: ``` python mesonetWebScrap```
   * then type 1 and press enter to run the web scrapper. 
### Install Prometheus and set up environment variable for it
   * Download at: https://prometheus.io/docs/prometheus/latest/installation/
   * copy and paste the configuration yml file from the data folder to replace the original yml file that comes with prometheus in the prometheus install directory.
   * Run prometheus with ``` ./prometheus ``` in the commandline after navigating to the prometheus install directory.
### Run Java Program with maven
   * Go to the project root directory and run the command:
   * ``` mvnd exec:java -Dexec.mainClass="Application" ```
   * Input the gps coordinates of the desired location to monitor and the name of the location
### Install Grafana
   * set up an account with a password
   * create a new dash board
   * set the data source to prometheus
   * add panels for each metric you want to monitor
   * on each panel click the query dropdown and select a metric beginning in "DWIS_" to load the live weather metrics








## Class files
  * GUIBuild: creates the user interface for entering location data and selecting interpolation methods. Also includes event handling.
  * NearestNeighbor: Calculates the nearest station to the inputed data and generates a dashboard with that stations weather data.
  * NearestNeighborTest: The JunitTest class for the nearest neighbor interpolation algorithm.
  * Station: Represents a single station and the associated data it produces.
  * ScriptLaunchPad: intended to launch any neccessary shell scripts to accomplish the programs objective (will possibly be scrapped).

#### mesonetWebScrape.py: python program that scrapes data from the mesonet website and creates an API endpoint to collect data from for the interpolator (java) program
