package com.marwa.Eltayeb.calculator;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.showOperation) EditText showOperation;
    @BindView(R.id.displayResult) TextView displayResult;
    double firstNumber = 0;
    double secondNumber = 0;
    Double result;
    char mOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        showOperation.setOnTouchListener(onTouchListener);

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
        String text;
        if (showOperation.getText().toString().isEmpty()) {
            showOperation.setText("");
        } else {
            try {
                // Get the first Number and parse it into double
                firstNumber = Double.parseDouble(String.valueOf(showOperation.getText()));
                // Store specified operation into mOperation
                mOperation = operation;
                // Show mathematical operation on screen
                text = showOperation.getText().toString() + operation;
                showOperation.setText(text);

                /*
                int blue = ContextCompat.getColor(this, R.color.blue);
                coloredOperation = getColoredString(String.valueOf(operation), blue);
                showOperation.setText(coloredOperation);
                */

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Do another operation
        if (!TextUtils.isEmpty(displayResult.getText().toString())) {
            // Get teh result
            String result = displayResult.getText().toString();
            // Remove Equal sign
            Double textWithoutEqual = Double.parseDouble(result.replace("=", ""));
            // Store the result as the first number
            firstNumber = Double.parseDouble(String.valueOf(textWithoutEqual));
            // Clear the result screen
            displayResult.setText("");
            // Store another operation sign
            mOperation = operation;

            // Get the whole number of the result
            Integer wholeNumber = textWithoutEqual.intValue();
            // Check if result is whole number or not
            double checkResult = textWithoutEqual / wholeNumber;

            if (firstNumber == 0.0) {
                showOperation.setText(0 + "" + String.valueOf(operation));
                // If result is not equal one
            } else if (checkResult != 1) {
                // Show the first number and the operation sign on screen
                showOperation.setText(firstNumber + "" + String.valueOf(operation));
            } else {
                // Show the first number and the operation sign on screen
                showOperation.setText(wholeNumber + "" + String.valueOf(operation));
            }
        }
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
        setOperation('x');
    }

    public void divide(View view) {
        setOperation('÷');
    }

    public void percent(View view) {
        setOperation('%');
    }

    /**
     * Undo the last number.
     */
    public void backSpace(View view) {
        String txt = showOperation.getText().toString();
        if (txt.length() > 0) {
            txt = txt.substring(0, txt.length() - 1);
            showOperation.setText(txt);
        }
    }

    public void zero(View view) {
        deletePreviousResult();
        setAll("0");
    }

    public void one(View view) {
        deletePreviousResult();
        setAll("1");
    }

    public void two(View view) {
        deletePreviousResult();
        setAll("2");
    }

    public void three(View view) {
        deletePreviousResult();
        setAll("3");
    }

    public void four(View view) {
        deletePreviousResult();
        setAll("4");
    }

    public void five(View view) {
        deletePreviousResult();
        setAll("5");
    }

    public void six(View view) {
        deletePreviousResult();
        setAll("6");
    }

    public void seven(View view) {
        deletePreviousResult();
        setAll("7");
    }

    public void eight(View view) {
        deletePreviousResult();
        setAll("8");
    }

    public void nine(View view) {
        deletePreviousResult();
        setAll("9");
    }

    public void point(View view) {
        deletePreviousResult();
        setAll(".");
    }

    /**
     * Set a negative number.
     */
    public void setNegativeNumber(View view) {
        if (showOperation.getText().toString().isEmpty()) {
            showOperation.setText("");
        } else {
            try {
                int num = Integer.parseInt(String.valueOf(showOperation.getText()));
                num = num * (-1);
                showOperation.setText(String.valueOf(num));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                case 'x':
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

            // Get the whole number of the result
            Integer wholeNumber = result.intValue();
            // Check if result is whole number or not
            double checkResult = result / wholeNumber;

            String coloredDecimalNumber = "<font color='#5d72e9'>" + getString(R.string.equals) + "" + result + "</font>";
            String coloredWholeNumber = "<font color='#5d72e9'>" + getString(R.string.equals) + "" + wholeNumber + "</font>";

            if (result == 0.0) {
                coloredWholeNumber = "<font color='#5d72e9'>" + getString(R.string.equals) + "" + 0 + "</font>";
                displayResult.setText(colorResult(String.valueOf(coloredWholeNumber)), TextView.BufferType.SPANNABLE);
                // If result is not equal one
            } else if (checkResult != 1) {
                // Set the decimal number
                displayResult.setText(colorResult(coloredDecimalNumber), TextView.BufferType.SPANNABLE);
            } else {
                // Set the whole number
                displayResult.setText(colorResult(coloredWholeNumber), TextView.BufferType.SPANNABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Color the final result
     */
    private Spanned colorResult(String number) {
        if (Build.VERSION.SDK_INT >= 24) {
            return (Html.fromHtml(number, 1)); // for 24 api and more
        } else {
            return Html.fromHtml(number, 2);// or for older api
        }
    }

    /**
     * Delete any previous results
     */
    private void deletePreviousResult() {
        if (!TextUtils.isEmpty(displayResult.getText().toString())) {
            showOperation.setText("");
            displayResult.setText("");
        }
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
                view.setFocusable(false);
            } else {
                view.setFocusableInTouchMode(true);
            }
            return true;
        }
    };

}
