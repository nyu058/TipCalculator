package ca.uottawa.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Summary extends AppCompatActivity {
TextView bAmount, tAmount, tips, tipsPerPerson, eachPersonPays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String amount = String.format("%.2f",bd.getDouble("amount"));
        String tip = String.format("%.2f",bd.getDouble("tip"));
        String total = String.format("%.2f", bd.getDouble("total"));
        String tipsPP = String.format("%.2f", bd.getDouble("tipPP"));
        String eachPays = String.format("%.2f", bd.getDouble("eachPays"));
        tAmount = (TextView) findViewById(R.id.tAmount);
        bAmount = (TextView) findViewById(R.id.bAmount);
        tips = (TextView) findViewById(R.id.tips);
        tipsPerPerson = (TextView) findViewById(R.id.tipsPerPerson);
        eachPersonPays = (TextView) findViewById(R.id.eachPays);
        tips.setText(tip);
        tAmount.setText(total);
        bAmount.setText(amount);
        tipsPerPerson.setText(tipsPP);
        eachPersonPays.setText(eachPays);
    }
}
