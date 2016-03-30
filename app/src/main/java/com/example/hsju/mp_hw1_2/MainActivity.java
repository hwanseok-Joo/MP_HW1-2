/**
 * This Program is Simple Tip Calculator. In the MainActivity, binding with activity_main.xml
 * so if you input the number, and then click the button, C2) the total result & Tip are calculated and showed
 * in the textView. and in the xml file, C1) I used Radio buttons 10% 15% 20%. also C3) I implemented exception handling
 * if you click the button without inputed number or inputed negative number, warning message will show as toast msg.
 * And I use the onSaveInstanceState(), onREstoreInstanceState() methods.
 * OnClickListener method is implemented as interface for multiple button.
 * activity_main.xml file use LinearLayout and RadioGroup & buttons.
 *
 * @author Hwanseok Ju
 * @since 2016-03-28
 */
package com.example.hsju.mp_hw1_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.BufferUnderflowException;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    RadioGroup percent;
    RadioButton first;
    RadioButton second;
    RadioButton third;
    Button btn;
    TextView tip;
    TextView total;

    private String savedAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = (EditText) findViewById(R.id.amount);
        percent = (RadioGroup) findViewById(R.id.percent);
        first = (RadioButton) findViewById(R.id.first);
        second = (RadioButton) findViewById(R.id.second);
        third = (RadioButton) findViewById(R.id.third);
        btn = (Button) findViewById(R.id.btn);
        tip = (TextView) findViewById(R.id.tip);
        total = (TextView) findViewById(R.id.total);

        //when onCreate method restarted, check the saving data.
        if (savedInstanceState != null) {
            // Toast.makeText(this, "Restore the Data", Toast.LENGTH_SHORT).show();
            amount.setText(savedInstanceState.getString(savedAmount));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = percent.getCheckedRadioButtonId();
                String amt = amount.getText().toString();
                // if you input the nothing or negative number, show the toast messages.
                if (amt.trim().compareTo("") == 0) {
                    Toast.makeText(getApplicationContext(), "Input the amount!!", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(amt) <= 0) {
                    Toast.makeText(getApplicationContext(), "Input the positive Number !!", Toast.LENGTH_LONG).show();
                    amount.setText("");
                } else {
                    double tot = Double.parseDouble(amt);
                    double Ftip = Math.round((tot * 0.1) * 100.0) / 100.0;
                    double Stip = Math.round((tot * 0.15) * 100.0) / 100.0;
                    double Ttip = Math.round((tot * 0.2) * 100.0) / 100.0;
                    if (first.getId() == radioId) {
                        total.setText("Total = " + (tot + Ftip));
                        tip.setText("tip = " + Ftip);
                    } else if (second.getId() == radioId) {
                        total.setText("Total = " + (tot + Stip));
                        tip.setText("tip = " + Stip);
                    } else if (third.getId() == radioId) {
                        total.setText("Total = " + (tot + Ttip));
                        tip.setText("tip = " + Ttip);
                    }
                }
            }
        });
    }

    // when the state changed, this method called to save the current data.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(savedAmount, amount.getText().toString());
        Toast.makeText(this, "Save the Data", Toast.LENGTH_SHORT).show();
    }

    // when the state changed, this method called to restore the current data.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        amount.setText(savedInstanceState.getString(savedAmount));
        Toast.makeText(this, "Restore the Data", Toast.LENGTH_SHORT).show();
    }
}
