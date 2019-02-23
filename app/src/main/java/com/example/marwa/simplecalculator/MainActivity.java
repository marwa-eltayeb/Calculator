package com.example.marwa.simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtShow;
    double num1 = 0;
    double num2 = 0;
    Double result;
    char ope;



    private boolean readyToClear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);


        txtShow = (EditText) findViewById(R.id.txtShow);
        txtShow.requestFocus();

    }



    private void setAll(String s){
        String all = txtShow.getText() + s;
        txtShow.setText(all);
    }

    private void setOpe(char operation){
        String test = txtShow.getText().toString();
        if (test.isEmpty())
        {

        }else {
            num1 = Double.parseDouble(String.valueOf(txtShow.getText()));
            txtShow.setText("");
            ope = operation;
        }
    }


    public void clear(View view) {
        txtShow.setText("");
    }

    public void add(View view) {
        setOpe('+');
    }

    public void subtract(View view) {
        setOpe('-');
    }

    public void multiply(View view) {
        setOpe('*');
    }

    public void divide(View view) {
        setOpe('/');
    }

    public void percent(View view) {
        setOpe('%');
    }

    public void backSpace(View view) {
        if (!readyToClear) {
                String txt = txtShow.getText().toString();
                if (txt.length() > 0) {
                    txt = txt.substring(0, txt.length() - 1);

                    txtShow.setText(txt);
                }
        }
    }

    public void zero(View view) {
        setAll("0");
        txtShow.setEnabled(false);
    }


    public void one(View view) {
        setAll("1");
        txtShow.setEnabled(false);
    }

    public void two(View view) {
        setAll("2");
        txtShow.setEnabled(false);
    }

    public void three(View view) {
        setAll("3");
        txtShow.setEnabled(false);
    }


    public void four(View view) {
        setAll("4");
        txtShow.setEnabled(false);
    }

    public void five(View view) {
        setAll("5");
        txtShow.setEnabled(false);
    }

    public void six(View view) {
        setAll("6");
        txtShow.setEnabled(false);
    }

    public void seven(View view) {
        setAll("7");
        txtShow.setEnabled(false);
    }


    public void eight(View view) {
        setAll("8");
        txtShow.setEnabled(false);
    }

    public void nine(View view) {
        setAll("9");
        txtShow.setEnabled(false);
    }


    public void dot(View view) {
        setAll(".");
        txtShow.setEnabled(false);
    }

    public void positiveAndNegative(View view) {
        String test2 = txtShow.getText().toString();
        if(test2.isEmpty()){
            //ignore
        }else {
            int num = Integer.parseInt(String.valueOf(txtShow.getText()));
            num = num * (-1);
            txtShow.setText(String.valueOf(num));
        }
    }


    public void equals(View view) {
        try {
            num2 = Double.parseDouble(String.valueOf(txtShow.getText()));
            switch (ope) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) //when denominator becomes zero
                    {
                        Toast.makeText(this, "DIVISION NOT POSSIBLE", Toast.LENGTH_SHORT).show();
                        result = null;
                        txtShow.setText("Error");
                        break;
                    }else{
                        result = num1 / num2;
                    }
                    break;
                case '%':
                    result = num1 % num2;
                    break;
            }
            Integer intResult = result.intValue();
            double testResult = result/intResult;
            if(testResult!=1) {
                txtShow.setText(result.toString());
            }else{
                txtShow.setText(intResult.toString());
            }

        }catch(Exception e){
            //ignore
        }
    }




}
