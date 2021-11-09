package com.example.kseb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button next_Activity_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next_Activity_Button = (Button) findViewById(R.id.go_to_class_and_amount);

        next_Activity_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    public void calculateAmount(View v){
        try {
            EditText unitField = (EditText) findViewById(R.id.units_consumed);
            Editable unitsEditable = unitField.getText();
            String units = unitsEditable.toString();
            int unitConsumed = Integer.parseInt(units);
            float floatUnits = convert(unitConsumed,3.15f,3.7f,4.8f,6.4f,7.6f,7.1f);
            int totalAmount = (int) floatUnits;
            displayAmount(totalAmount);
        }
        catch (Exception e){
            int totalAmount = 0;
            displayAmount(totalAmount);
        }
    }

    public void displayAmount(int totalAmount){
        TextView amountView = (TextView) findViewById(R.id.amount_calculated);
        amountView.setText("₹ " + String.valueOf(totalAmount));
    }

    float convert(int unit,float am1,float am2,float am3,float am4,float am5,float am6){
        float price=0f;
        float fixed=0f;
        float duty = 0f;
        float meterRent = 14.28f;


        float m = unit/2;

        if (m>0 && m<=50)
        {
            price += (m*3.15)*2;
            fixed = 35.0f;
        }
        else if (m>50 && m<=100)
        {
            price += 2*((m-50)*3.7 + 157.5);
            fixed = 45.0f;
        }
        else if (m>100 && m<=150)
        {
            price += ((m-100)*4.8 + 342.5)*2;
            fixed = 55.0f;
        }
        else if (m>150 && m<=200)
        {
            price += ((m-150)*6.4 + 582.5)*2;
            fixed = 70.0f;
        }
        else if (m>200 && m<=250)
        {
            price += ((m-200)*7.6 + 902.5)*2;
            fixed = 80.0f;
        }
        else if (m>250 && m<=300)
        {
            price += (m*5.8)*2;
            fixed = 100.0f;
        }
        else if (m>300 && m<=350)
        {
            price += 2*(m*6.6);
            fixed = 110.0f;
        }
        else if (m>350 && m<=400)
        {
            price += 2*(m*6.9);
            fixed = 120.0f;
        }
        if (m>400 && m<=500)
        {
            price += 2*(m*7.1);
            fixed = 130.0f;
        }
        else if (m>500)
        {
            price += m*7.9*2;
            fixed = 150.0f;
        }
        // else{
        //     price += ((m-250)*7.1 + 380.0 + 902.5)*2;
        // }
        fixed *= 2;
        // System.out.println(fixed);
        price += fixed + (price*0.1) + meterRent;

        return price;
    }
}