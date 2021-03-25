import requests
from bs4 import BeautifulSoup


# Outputs array of htmls used to find location informations 
def getDesc():
    page = requests.get('http://www.mesonet.org/index.php/weather/local')
    soup = BeautifulSoup(page.content, 'html.parser')
    results = soup.find(id='Current_obs_map')
    rawSite = results.find_all('area')
    descriptions = []
    for station in rawSite:
        descriptions.append(station.get("alt"))
        #print(station.get("alt"))
    return descriptions

#TODO: 
# Outputs array of htmls used to find weather information 
# def getDescT(url):
#     page = requests.get(url)
#     soup = BeautifulSoup(page.content, 'html.parser')
#     result = soup.find(class="obs_temperatur")
#     print(result)

# Returns array of station abreviations 
def getStations():
    page = requests.get('http://www.mesonet.org/index.php/weather/local')
    soup = BeautifulSoup(page.content, 'html.parser')
    results = soup.find(id='Current_obs_map')
    rawSite = results.find_all('area')
    siteAbrev = []
    for station in rawSite:
        siteAbrev.append(station.get("stid"))
    return siteAbrev

# Outputs array of urls to live weather stations websites
def getURLs(stations):
    siteURLs = []
    for station in stations:
        URL2 = 'http://www.mesonet.org/index.php/weather/local/'
        URL2 += str(station)
        siteURLs.append(URL2)
    return siteURLs

#prints array 
def printL(arr):
    for x in arr:
        print(arr[x])

#works with arrays 
def addTerm(desc, arr, pos):
    cnt = 0
    for t in arr:
        #print(t)
        temp = fData[cnt]
        upd = {desc : t[pos][1]}
        temp.update(upd)
        fData[cnt] = temp
        cnt += 1
       

        #fData[cnt] = {'name': siteAbrs[cnt], 'lat': locInfo[cnt][1][1], 'long': locInfo[cnt][2][1]}

        # if(cnt in tempInfo):
        #     print("found")
        #     temp = fData[cnt]
        #     upd = {'temp': tempInfo[cnt]}
        #     temp.update(upd)
        #     fData[cnt] = temp
        # else:
        #     print("didnt find")


        


# def getStationTemp(site):

#     page = requests.get(site)
#     soup = BeautifulSoup(page.content, 'html.parser')

#     if(len(soup)>0):
#         try:

#             locMeso = soup.find_all('div', {'class': 'obs_temperature'})
#             p1= str(locMeso).partition('>')
#             p2= str(p1[2]).partition(' <')
#         except:
#             print("didnt work")
#             return

#    return(p2[0])

#Returns nested array with location data for each weather station 
def getLocData(descr):
    
    #Saves [pos][label][content]
    #pos: 
    #County 0 
    #Latitude 1 
    #Longitude 2 
    #Elevation 3 

    s1 = descr.split("</br>")
    s2 = []
    for x in s1:
        s2.append(x.split(": "))


    return s2



fData = {}

locInfo = []

tempInfo = []

rawSiteDescT = []

#Parse htmls 
#
#

siteAbrs = getStations()

urls = getURLs(siteAbrs)

rawSiteDesc = getDesc()

for x in rawSiteDesc:
    locInfo.append(getLocData(x))
    #print(getLocData(x))


#Populate Dictionary 
#
#

itr = 0 
for x in siteAbrs:
    fData[itr] = {"Site" : x}
    itr+=1
    #print(fData[itr])
    

addTerm("Latitude", locInfo, 1)
addTerm("Longitude", locInfo, 2)

print(fData)

# TODO: Parse html to get array of temparature data for each station 
# TODO: Integrate code to an endpoint 
