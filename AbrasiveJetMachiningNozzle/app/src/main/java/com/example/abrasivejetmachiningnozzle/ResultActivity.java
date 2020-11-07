package com.example.abrasivejetmachiningnozzle;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    TextView diaIncRate, weightLoss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        diaIncRate = (TextView) findViewById(R.id.diaIncRate);
        weightLoss = (TextView) findViewById(R.id.weightLoss);

        
    }
}
