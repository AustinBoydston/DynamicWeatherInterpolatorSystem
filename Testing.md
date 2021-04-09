DWIS Testing Procedures 

# Scraping



# Interpolation
We are implementing two interpolation methods: Nearest neighbor, and inverse linear distance. These two methods will be implemented in java by pulling our scrpaed data out of prometheus, processing it, and then returning a new continuous data set back. From there Grafana will take over in the dashboard generated from the java program. 

We can unit test our interpolation methods by inserting dummy data that we know what the outcome should be provided our program runs as expected. For the nearest neighbor algorithm, the values that will need unit testing are the results of our distance calculations (which finds the distance between GPS coordinates), and the station ID being returned. 

The inverse linear distance interpolation method will require similar unit tests on its distance functions, however it differs from nearest neighbor in that it calculates weighted averages and applies that to the incoming weather data. This method will also need to export its own data to prometheus likely in the form of a csv file so that our display software (Grafana) can collect and present it. 




# Grafana





