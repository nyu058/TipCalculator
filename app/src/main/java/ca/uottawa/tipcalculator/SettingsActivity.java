package ca.uottawa.tipcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class SettingsActivity extends AppCompatActivity implements OnClickListener {
    TextView defaultTipPercentageTextView;
    TextView currencyTextView;
    EditText defaultTipPercentageEditText;

    Spinner currencySpinner;
    Button confirmButton;
    Button resetButton;
    Button cancelButton;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        currencySpinner= (Spinner) findViewById(R.id.currencySpinner);

        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,R.array.entries, R.layout.spinner);
        currencyAdapter.setDropDownViewResource(R.layout.spinner);
        currencySpinner.setAdapter(currencyAdapter);

        defaultTipPercentageTextView = (TextView) findViewById(R.id.defaultTipPercentageTextView);
        currencyTextView = (TextView) findViewById(R.id.currencyTextView);
        defaultTipPercentageEditText = (EditText) findViewById(R.id.defaultTipPercentageEditText);

        confirmButton = (Button) findViewById(R.id.confirmButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        confirmButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        defaultTipPercentageEditText.setText(pref.getString("percentage", "15"));
        currencySpinner.setSelection(setCurrencySpinner());

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.confirmButton) {

            if (validateInput()) {
                //这里要存一下editText input的值
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("percentage",defaultTipPercentageEditText.getText().toString());
                String str = currencySpinner.getSelectedItem().toString();
                editor.putString("currency",str);
                editor.apply();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }else if (view.getId() == R.id.resetButton) {

            defaultTipPercentageEditText.setText("");
            currencySpinner.setSelection(0);

        }else if(view.getId() == R.id.cancelButton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean checkEmpty(EditText editText){
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError("This field cannot be blank!");
            return true;
        }
        return false;
    }

    public boolean checkValidity(EditText editText){
        int amount = Integer.parseInt(editText.getText().toString());
        if(amount <0 || amount >100){
            editText.setError("Please enter an amount between 0 and 100");
            return true;
        }
        return false;
    }

    public boolean validateInput(){
        if(checkEmpty(defaultTipPercentageEditText)){
            return false;
        }
        else if(checkValidity(defaultTipPercentageEditText)){
            return false;
        }
        return true;
    }
    public int setCurrencySpinner(){
        String str = pref.getString("currency","");
        if(str.equals("Pound")){
            return 2;
        }else if(str.equals("Euro")){
            return 1;
        }
        else {
            return 0;
        }
    }
}
