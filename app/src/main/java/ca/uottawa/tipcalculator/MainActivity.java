package ca.uottawa.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button submit;
    EditText amount, people, percent;
    public static final String MSG_KEY="ca.uottawa.tipcalculator";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.submit);
        amount =(EditText) findViewById(R.id.bAmount);
        people = (EditText) findViewById(R.id.people);
        percent = (EditText) findViewById(R.id.percent);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if((validateInput())){
            double a = Double.parseDouble(amount.getText().toString());
            int n = Integer.parseInt(people.getText().toString());
            double p = Double.parseDouble(percent.getText().toString()) / 100;
            double total = a * (1 + p);
            double tip = a * p;
            double tipPP = tip/n;
            double eachPays = total/n;
            Intent intent = new Intent(this, Summary.class);
            Bundle bd = new Bundle();
            bd.putDouble("amount", a);
            bd.putDouble("total", total);
            bd.putDouble("tip", tip);
            bd.putDouble("tipPP", tipPP);
            bd.putDouble("eachPays", eachPays);
            intent.putExtras(bd);
            startActivity(intent);
        }
    }
    public boolean checkEmpty(EditText e){
        if(TextUtils.isEmpty(e.getText().toString())){
            e.setError("This field can not be blank");
            return true;
        }
        return false;
    }
    public boolean validateInput(){
        boolean valid = true;
        if(checkEmpty(amount)){
            valid = false;
        }
        if(checkEmpty(people)){
            valid = false;
        }
        if(checkEmpty(percent)){
            valid =false;
        }
        return valid;
    }
}