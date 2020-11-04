import csv
import numpy as np

with open("exitDiameter.data","r") as csvfile:
        lines = csv.reader(csvfile)
        exitDia_dataset = list(lines)

        for x in range(len(exitDia_dataset)- 1):    # iterate each row of dataset
            for y in range(8):              #iterate fielement of xth row
                exitDia_dataset[x][y] = float(exitDia_dataset[x][y])

#print(exitDia_dataset)
#print("################################################################################################################")

with open("weightLoss.data","r") as csvfile:
        lines = csv.reader(csvfile)
        wLoss_dataset = list(lines)

        for x in range(len(wLoss_dataset)- 1):    # iterate each row of dataset
            for y in range(8):              #iterate fielement of xth row
                wLoss_dataset[x][y] = float(wLoss_dataset[x][y])

#print(wLoss_dataset)

#Theta = (X T .X) -1 .X T .Y



