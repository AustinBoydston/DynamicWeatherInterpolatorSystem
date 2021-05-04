import requests
import sys
import os 
from bs4 import BeautifulSoup

import time
import getch

def updateCSV():
    page = requests.get(
        'https://www.mesonet.org/data/public/mesonet/current/current.csv.txt')
    soup = BeautifulSoup(page.content, 'html.parser')

    weatherData = str(soup).split("\n")

    noHeaderWeatherData = weatherData[1:len(weatherData)]

    file1 = open("../Data/"+"current.csv", "a")

    file1.truncate(0)

    length = len(noHeaderWeatherData)
    itr = 0
    for data in noHeaderWeatherData:
        file1.write(data)
        if itr != len(noHeaderWeatherData):
            file1.write("\n")
        itr+=1 
    
    file1.close()

def contiguousUpdate():

    updateCSV() 
    userInput = "" 

    print("To exit update loop enter Ctrl-C")

    while userInput != "0":

        try:
            while True:

                time.sleep(240)

                updateCSV()

                print("Current.csv Updated")
        except KeyboardInterrupt:
            userInput = "0"
            pass


def main():

    print("1: Continuous scrap, every 5 minutes")
    print("2: Update file now")
    print("3: print menu")
    print("0: Exit program")

    
    val = input("Enter one of the numbers listed above: ")

    while val != "0":
        if val == "1":
            contiguousUpdate()
        elif val == "2":
            updateCSV()
        elif val == "3":
            print("1: Continuous scrap, every 5 minutes")
            print("2: Update file now")
            print("3: print options menu")
            print("0: Exit program")
        else:
            print("Invalid input")

        print("\n")
        val = input("Enter one of the numbers listed above: ")

    print("End of program")


if __name__ == "__main__":
    main()
