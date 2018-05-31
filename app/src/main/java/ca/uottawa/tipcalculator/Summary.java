package ca.uottawa.tipcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Summary extends AppCompatActivity {
    TextView bAmount, tAmount, tips, tipsPerPerson, eachPersonPays;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String amount = String.format("%.2f", bd.getDouble("amount"));
        String tip = String.format("%.2f", bd.getDouble("tip"));
        String total = String.format("%.2f", bd.getDouble("total"));
        String tipsPP = String.format("%.2f", bd.getDouble("tipPP"));
        String eachPays = String.format("%.2f", bd.getDouble("eachPays"));
        tAmount = (TextView) findViewById(R.id.tAmount);
        bAmount = (TextView) findViewById(R.id.bAmount);
        tips = (TextView) findViewById(R.id.tips);
        tipsPerPerson = (TextView) findViewById(R.id.tipsPerPerson);
        eachPersonPays = (TextView) findViewById(R.id.eachPays);
        tips.setText(setCurrencySign() + tip);
        tAmount.setText(setCurrencySign() + total);
        bAmount.setText(setCurrencySign() + amount);
        tipsPerPerson.setText(setCurrencySign() + tipsPP);
        eachPersonPays.setText(setCurrencySign() + eachPays);
    }

    public String setCurrencySign() {
        System.out.println(pref.getString("currency", "noneMain"));
        String str = pref.getString("currency", "");
        if (str.equals("Pound")) {
            return "\u00a3";
        } else if (str.equals("Euro")) {
            return "\u20ac";
        } else {
            return "$";
        }
    }
}
