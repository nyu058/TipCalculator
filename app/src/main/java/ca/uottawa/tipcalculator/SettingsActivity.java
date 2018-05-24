package ca.uottawa.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class SettingsActivity extends AppCompatActivity implements OnClickListener {
    TextView defaultTipPercentageTextView;
    TextView currencyTextView;
    EditText defaultTipPercentageEditText;
    TextView percentageSymbolTextView;
    Spinner currencySpinner;
    Button confirmButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        defaultTipPercentageTextView = (TextView) findViewById(R.id.defaultTipPercentageTextView);
        currencyTextView = (TextView) findViewById(R.id.currencyTextView);
        defaultTipPercentageEditText = (EditText) findViewById(R.id.defaultTipPercentageEditText);
        percentageSymbolTextView = (TextView) findViewById(R.id.defaultTipPercentageTextView);

        //两个button没有分别做intent,问题可能出在这里
        confirmButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.confirmButton) {

            if (validateInput()) {
                //这里要存一下editText input的值
                Intent intent = new Intent(this, MainActivity.class);
                Bundle bd = new Bundle();
                bd.putString("percent",defaultTipPercentageEditText.getText().toString());
                intent.putExtras(bd);
                startActivity(intent);
            }
        }else if (view.getId() ==R.id.resetButton){
            //todo
        }
    }

    public boolean checkEmpty(EditText editText){
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError("This field cannot be blank!");
            return true;
        }
        return false;
    }

    public boolean validateInput(){
        if(checkEmpty(defaultTipPercentageEditText)){
            return false;
        }
        return true;
    }
}
