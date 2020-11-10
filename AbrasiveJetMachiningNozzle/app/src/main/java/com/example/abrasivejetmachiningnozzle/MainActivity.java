package com.example.abrasivejetmachiningnozzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText nozzleLength, nozzleDiameter, inletAngle, orficeDiamter, waterPressure, flowRate;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nozzleLength = (EditText) findViewById(R.id.nozzleLength);
        nozzleDiameter = (EditText) findViewById(R.id.nozzleDiameter);
        inletAngle = (EditText) findViewById(R.id.inletAngle);
        orficeDiamter = (EditText) findViewById(R.id.orficeDiamter);
        waterPressure = (EditText) findViewById(R.id.waterPressure);
        flowRate = (EditText) findViewById(R.id.abrasiveFlowRate);
        ok = (Button) findViewById(R.id.okButton);


        //TO ENABLE BUTTON AFTER TEXT IS WRITTEN

        nozzleLength.addTextChangedListener(loginTextWatch);
        nozzleDiameter.addTextChangedListener(loginTextWatch);
        inletAngle.addTextChangedListener(loginTextWatch);
        orficeDiamter.addTextChangedListener(loginTextWatch);
        waterPressure.addTextChangedListener(loginTextWatch);
        flowRate.addTextChangedListener(loginTextWatch);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), ResultActivity.class);

                //PASS DATA
                String nozzleLengthData = nozzleLength.getText().toString().trim();
                String nozzleDiameterData = nozzleDiameter.getText().toString().trim();
                String inletAngleData = inletAngle.getText().toString().trim();
                String orficeDiameterData = orficeDiamter.getText().toString().trim();
                String waterPressureData = waterPressure.getText().toString().trim();
                String flowRateData = flowRate.getText().toString().trim();

                s.putExtra("nozzleLengthData", nozzleLengthData);
                s.putExtra("nozzleDiameterData", nozzleDiameterData);
                s.putExtra("inletAngleData", inletAngleData);
                s.putExtra("orficeDiameterData", orficeDiameterData);
                s.putExtra("waterPressureData", waterPressureData);
                s.putExtra("flowRateData", flowRateData);

                startActivity(s);
            }
        });

    }

    //TO ENABLE BUTTON AFTER TEXT IS WRITTEN

    private TextWatcher loginTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String nLInput = nozzleLength.getText().toString().trim();
            String nDInput = nozzleDiameter.getText().toString().trim();
            String iAInput = inletAngle.getText().toString().trim();
            String oDInput = orficeDiamter.getText().toString().trim();
            String wPInput = waterPressure.getText().toString().trim();
            String fRInput = flowRate.getText().toString().trim();

            ok.setEnabled(!nLInput.isEmpty()
                    && !nDInput.isEmpty()
                    && !iAInput.isEmpty()
                    && !oDInput.isEmpty()
                    && !wPInput.isEmpty()
                    && !fRInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
