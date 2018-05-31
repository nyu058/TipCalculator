package ca.uottawa.tipcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View.OnClickListener;
import android.widget.*;
public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button submit, suggest;
    EditText amount, people, percent;
    public static final String MSG_KEY="ca.uottawa.tipcalculator";
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.submit);
        suggest =(Button) findViewById(R.id.suggest);
        amount =(EditText) findViewById(R.id.bAmount);
        people = (EditText) findViewById(R.id.people);
        percent = (EditText) findViewById(R.id.percent);
        //percent.setText(setPercent);
        submit.setOnClickListener(this);
        suggest.setOnClickListener(this);
        pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        percent.setText(pref.getString("percentage",""));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings) {
            Intent intent3 = new Intent(this, SettingsActivity.class);
            this.startActivity(intent3);
            return true;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.submit) {
            if ((validateInput())) {
                double a = Double.parseDouble(amount.getText().toString());
                int n = Integer.parseInt(people.getText().toString());
                double p = Double.parseDouble(percent.getText().toString()) / 100;
                double total = a * (1 + p);
                double tip = a * p;
                double tipPP = tip / n;
                double eachPays = total / n;
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
        }else if(v.getId()==R.id.suggest){
            if(validateInput2()) {
                double amount2 = Double.parseDouble(amount.getText().toString());
                int n2 = Integer.parseInt(people.getText().toString());
                Intent intent2 = new Intent(this, Suggestion.class);
                Bundle bd2 = new Bundle();
                bd2.putDouble("amount2", amount2);
                bd2.putInt("n2", n2);
                intent2.putExtras(bd2);
                startActivity(intent2);
            }
        }
    }
    public boolean checkEmpty(EditText e){
        if(TextUtils.isEmpty(e.getText().toString())){
            e.setError("This field can not be blank");
            return true;
        }
        return false;
    }
    public boolean validateInput2(){
        boolean valid = true;
        if(checkEmpty(amount)){
            valid = false;
        }
        if(checkEmpty(people)){
            valid = false;
        }
        return valid;
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