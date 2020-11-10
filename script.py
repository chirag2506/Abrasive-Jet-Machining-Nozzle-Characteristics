import csv
import numpy as np

with open("exitDiameter.data","r") as csvfile:

                # Nozzle length^2 + nozzle length
                # Nozzle diameter^2 + nozzle dia
                # 1/inlet angle
                # sin(orfice dia)
                # (waterpre^2/1000) + water press
                # abrasiveflow^3 + af^2 + af

        lines = csv.reader(csvfile)
        exitDia_dataset = list(lines)

        for x in range(len(exitDia_dataset)):    # iterate each row of dataset
            for y in range(13):              #iterate fielement of xth row
                exitDia_dataset[x][y] = float(exitDia_dataset[x][y])

# print(exitDia_dataset)
# print("################################################################################################################")

with open("weightLoss.data","r") as csvfile:

                # Nozzle length^2 + nozz len
                # Nozzle diameter
                # inlet angle
                # orfice dia ^2 + od
                # (waterpre^3/1000000) + (waterpre^2/1000) + water press
                # abrasiveflow^3 + af^2 + af

        lines = csv.reader(csvfile)
        wLoss_dataset = list(lines)

        for x in range(len(wLoss_dataset)):    # iterate each row of dataset
            for y in range(14):              #iterate fielement of xth row
                wLoss_dataset[x][y] = float(wLoss_dataset[x][y])

# print(wLoss_dataset)

#Theta = (X T .X) -1 .X T .Y

exitDia_dataset = np.array(exitDia_dataset)
wLoss_dataset = np.array(wLoss_dataset)

exitDia_dataset_features = exitDia_dataset[:,[0,1,2,3,4,5,6,7,8,9,10,11]]
wLoss_dataset_features = wLoss_dataset[:,[0,1,2,3,4,5,6,7,8,9,10,11,12]]
# print(exitDia_dataset_features)
# print("###########################################################################################################################################")
# print(wLoss_dataset_features)

exitDia_dataset_result = exitDia_dataset[:,[12]]
wLoss_dataset_result = wLoss_dataset[:,[13]]
# print(exitDia_dataset_result)
# print(wLoss_dataset_result)

def cost(x,y):
    x_transpose = x.transpose()

    xTx = np.matmul(x_transpose, x)
    xTxInverse = np.linalg.inv(xTx)

    xTxInvxT = np.matmul(xTxInverse, x_transpose)

    cost_matrix = np.matmul(xTxInvxT, y)

    return cost_matrix

exitDia_cost = cost(exitDia_dataset_features, exitDia_dataset_result)
#print(exitDia_cost)

wLoss_cost = cost(wLoss_dataset_features, wLoss_dataset_result)
# print(wLoss_cost)

# ON TRAINING DATA:
# {{ 4.73441215e+01},{ 1.28507926e-02},{-2.02194837e+00},{-4.29179826e+00},{ 1.04914004e+01},{-7.79076535e+01},{-1.48616702e+01},{-1.78347890e-01},{ 1.09945532e-01},
#  { 4.15532729e-02},{-7.44167607e-01},{ 5.51041483e+00}}

# [[ 3.14741959e+00],[-1.58465491e-04],[ 1.93055162e-02],[-1.19945695e-01],[ 2.62551786e-04],[ 4.08681298e+01],[-2.97032952e+01],[ 1.13805178e-01],[-9.52030361e-02],
# [ 2.70910491e-02],[-2.24142165e-03],[ 4.88147083e-02],[-2.06863414e-01]]

x= wLoss_dataset[[26],[0,1,2,3,4,5,6,7,8,9,10,11,12]]
p = np.matmul(x, wLoss_cost)
print(p)