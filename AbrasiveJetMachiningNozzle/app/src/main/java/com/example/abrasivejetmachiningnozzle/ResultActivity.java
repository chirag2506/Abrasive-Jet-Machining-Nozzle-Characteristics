package com.example.abrasivejetmachiningnozzle;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.Math;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    TextView diaIncRate;

    double nozLen, nozDia, inletAng, orfDia, waterPre, flowRate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        diaIncRate = (TextView) findViewById(R.id.diaIncRate);

//[[-1.12840974e+01],[ 1.15985736e-02],[-1.86811079e+00],[-1.53263321e+01],[ 3.80777349e+01],[ 5.20718839e+01],[ 2.03207468e-01],[-3.88453837e-01],[ 2.29097297e-01],[ 7.30535607e-02],
// [-1.38908113e+00],[ 9.18407861e+00]]
//
//[[ 3.14741959e+00],[-1.58465491e-04],[ 1.93055162e-02],[-1.19945695e-01],[ 2.62551786e-04],[ 4.08681298e+01],[-2.97032952e+01],[ 1.13805178e-01],[-9.52030361e-02],[ 2.70910491e-02],
// [-2.24142165e-03],[ 4.88147083e-02],[-2.06863414e-01]]

        //GET DATA
        Bundle bundle = getIntent().getExtras();
        nozLen = Double.parseDouble(bundle.getString("nozzleLengthData"));
        nozDia = Double.parseDouble(bundle.getString("nozzleDiameterData"));
        inletAng = Double.parseDouble(bundle.getString("inletAngleData"));
        orfDia = Double.parseDouble(bundle.getString("orficeDiameterData"));
        waterPre = Double.parseDouble(bundle.getString("waterPressureData"));
        flowRate = Double.parseDouble(bundle.getString("flowRateData"));

        double[][] theta_dIR = {
                                    { 4.73441215e+01},
                                    { 1.28507926e-02},
                                    {-2.02194837e+00},
                                    {-4.29179826e+00},
                                    { 1.04914004e+01},
                                    {-7.79076535e+01},
                                    {-1.48616702e+01},
                                    {-1.78347890e-01},
                                    { 1.09945532e-01},
                                    { 4.15532729e-02},
                                    {-7.44167607e-01},
                                    { 5.51041483e+00}
                                };
        double[][] theta_wL = {
                                    { 3.14741959e+00},
                                    {-1.58465491e-04},
                                    { 1.93055162e-02},
                                    {-1.19945695e-01},
                                    { 2.62551786e-04},
                                    { 4.08681298e+01},
                                    {-2.97032952e+01},
                                    { 1.13805178e-01},
                                    {-9.52030361e-02},
                                    { 2.70910491e-02},
                                    {-2.24142165e-03},
                                    { 4.88147083e-02},
                                    {-2.06863414e-01}
                                };

        double[][] x_forDIR = {{1, nozLen*nozLen, nozLen, nozDia*nozDia, nozDia, 1/inletAng, Math.sin(orfDia), waterPre*waterPre/1000, waterPre,
                                flowRate*flowRate*flowRate, flowRate*flowRate, flowRate}};

        double[][] x_forWL = {{1, nozLen*nozLen, nozLen, nozDia, inletAng, orfDia*orfDia, orfDia, waterPre*waterPre*waterPre/1000000, waterPre*waterPre/1000, waterPre,
                                flowRate*flowRate*flowRate, flowRate*flowRate, flowRate}};

        //x*theta
        double diaIncRateText = multiplyMatrix(1, 12, x_forDIR, 12, 1, theta_dIR);
        double weightLossText = multiplyMatrix(1, 13, x_forWL, 13, 1, theta_wL);

        diaIncRate.setText(Double.toString(diaIncRateText));




    }

    private static double multiplyMatrix(
            int row1, int col1, double A[][],
            int row2, int col2, double B[][]) {

        int i, j, k;

        // Matrix to store the result
        // The product matrix will
        // be of size row1 x col2
        double C[][] = new double[row1][col2];

        // Multiply the two marices
        for (i = 0; i < row1; i++) {
            for (j = 0; j < col2; j++) {
                for (k = 0; k < row2; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                    System.out.println(A[i][k] * B[k][j]);
                }

            }
        }

        return C[0][0];
    }
}