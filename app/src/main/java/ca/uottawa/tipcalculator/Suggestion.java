package ca.uottawa.tipcalculator;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;

public class Suggestion extends AppCompatActivity implements OnClickListener {
    Button cool;
    RadioGroup rg;
    double a;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        cool = (Button) findViewById(R.id.coolButton);
        rg = (RadioGroup) findViewById(R.id.rg);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        a = bd.getDouble("amount2");
        n = bd.getInt("n2");
        cool.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        double p = getPercent();
        double total = a * (1 + p);
        double tip = a * p;
        double tipPP = tip / n;
        double eachPays = total / n;
        Intent new_intent = new Intent(this, Summary.class);
        Bundle new_bd = new Bundle();
        new_bd.putDouble("amount", a);
        new_bd.putDouble("total", total);
        new_bd.putDouble("tip", tip);
        new_bd.putDouble("tipPP", tipPP);
        new_bd.putDouble("eachPays", eachPays);
        new_intent.putExtras(new_bd);
        startActivity(new_intent);
    }
    public double getPercent(){
        RadioButton ratingbtn = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        double rating = Integer.parseInt(ratingbtn.getText().toString());
        double percent = (10+(rating*2))/100;
        return percent;
    }
}
