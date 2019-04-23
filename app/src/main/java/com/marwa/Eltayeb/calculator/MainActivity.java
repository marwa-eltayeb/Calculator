package com.marwa.Eltayeb.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText showOperation;
    TextView displayResult;
    double firstNumber = 0;
    double secondNumber = 0;
    Double result;
    char mOperation;
    Spannable coloredText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showOperation = (EditText) findViewById(R.id.showOperation);
        showOperation.requestFocus();
        displayResult = (TextView) findViewById(R.id.displayResult);

    }

    /**
     * Sets different numbers
     *
     * @param number is the number that the user choose.
     */
    private void setAll(String number) {
        String all = String.valueOf(showOperation.getText() + number);
        showOperation.setText(all);
    }

    /**
     * Sets different operations
     *
     * @param operation is the operation that the user choose.
     */
    private void setOperation(char operation) {
        if (showOperation.getText().toString().isEmpty()) {
            showOperation.setText("");
        } else {
            try {
                // Get the first Number and parse it into double
                firstNumber = Double.parseDouble(String.valueOf(showOperation.getText()));
                // Store specified operation into mOperation
                mOperation = operation;
                // Show mathematical operation on screen
                String text = showOperation.getText().toString() + operation;
                //showOperation.setText(text);

                int blue = getResources().getColor(R.color.blue);
                coloredText = getColoredString(String.valueOf(operation),blue);
                showOperation.setText(coloredText);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***
     *
     * @param text this will setup to your textView
     * @param colorId  text will fill with this color.
     * @return string with color, it will append to textView.
     */
    private Spannable getColoredString(String text, int colorId) {
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(colorId), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Log.d("tag",spannable.toString());
        return spannable;
    }

    /*
    * Clear the result
    */
    public void clear(View view) {
        showOperation.setText("");
        displayResult.setText("");
    }

    public void add(View view) {
        setOperation('+');
    }

    public void subtract(View view) {
        setOperation('-');
    }

    public void multiply(View view) {
        setOperation('*');
    }

    public void divide(View view) {
        setOperation('÷');
    }

    public void percent(View view) {
        setOperation('%');
    }

    public void backSpace(View view) {
        String txt = showOperation.getText().toString();
        if (txt.length() > 0) {
            txt = txt.substring(0, txt.length() - 1);
            showOperation.setText(txt);
        }
    }

    public void zero(View view) {
        setAll("0");
    }

    public void one(View view) {
        setAll("1");
    }

    public void two(View view) {
        setAll("2");
    }

    public void three(View view) {
        setAll("3");
    }

    public void four(View view) {
        setAll("4");
    }

    public void five(View view) {
        setAll("5");
    }

    public void six(View view) {
        setAll("6");
    }

    public void seven(View view) {
        setAll("7");
    }

    public void eight(View view) {
        setAll("8");
    }

    public void nine(View view) {
        setAll("9");
    }

    public void dot(View view) {
        setAll(".");
    }

    public void positiveAndNegative(View view) {
        if (showOperation.getText().toString().isEmpty()) {
            showOperation.setText("");
        } else {
            int num = Integer.parseInt(String.valueOf(showOperation.getText()));
            num = num * (-1);
            showOperation.setText(String.valueOf(num));
        }
    }

    public void equals(View view) {
        try {
            // Get the Text
            String text = showOperation.getText().toString();
            // Split it
            String[] splittedText = text.split(String.valueOf("\\" + mOperation));
            // Take the Second number
            secondNumber = Double.parseDouble(splittedText[1]);

            switch (mOperation) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;
                case '-':
                    result = firstNumber - secondNumber;
                    break;
                case '*':
                    result = firstNumber * secondNumber;
                    break;
                case '÷':
                    if (secondNumber == 0) //when denominator becomes zero
                    {
                        Toast.makeText(this, "DIVISION NOT POSSIBLE", Toast.LENGTH_SHORT).show();
                        result = null;
                        displayResult.setText(getResources().getString(R.string.error));
                        break;
                    } else {
                        result = firstNumber / secondNumber;
                    }
                    break;
                case '%':
                    result = firstNumber % secondNumber;
                    break;
            }

            Integer intResult = result.intValue();
            double testResult = result / intResult;

            if (testResult != 1) {
                displayResult.setText(getString(R.string.equals) + " " + String.valueOf(result));
            } else {
                displayResult.setText(getString(R.string.equals) + " " + String.valueOf(intResult));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
