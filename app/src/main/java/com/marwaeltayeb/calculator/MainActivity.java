package com.marwaeltayeb.calculator;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.marwaeltayeb.calculator.SoundUtils.playSound;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final String FIRST_NUMBER = "firstNumber";
    private final String SECOND_NUMBER = "secondNumber";
    private final String OPERATOR = "operator";
    private final String RESULT = "result";
    private final String WHOLE_NUMBER = "wholeNumber";

    @BindView(R.id.showOperation)
    EditText showOperation;
    @BindView(R.id.displayResult)
    TextView displayResult;

    double firstNumber = 0;
    double secondNumber = 0;
    Double result;
    char mOperation;

    private Integer wholeNumber;
    private double checkResult;
    
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        showOperation.setOnTouchListener(onTouchListener);

        if (savedInstanceState != null) {
            firstNumber = savedInstanceState.getDouble(FIRST_NUMBER);
            secondNumber = savedInstanceState.getDouble(SECOND_NUMBER);
            mOperation = savedInstanceState.getChar(OPERATOR);
            result = savedInstanceState.getDouble(RESULT);
            wholeNumber = savedInstanceState.getInt(WHOLE_NUMBER);

            displayColouredResult();

            if (Double.isNaN(result) || result == 0.0) {
                displayResult.setText("");
            }
        }
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
    @OnClick(R.id.btnClear)
    public void clear(View view) {
        if (displayResult.getText().length() > 0 || showOperation.getText().length() > 0) {
            showOperation.setText("");
            displayResult.setText("");
            result = Double.NaN;
        }
    }

    @OnClick(R.id.btnAdd)
    public void add(View view) {
        setOperation('+');
    }

    @OnClick(R.id.btnSub)
    public void subtract(View view) {
        setOperation('-');
    }

    @OnClick(R.id.btnMul)
    public void multiply(View view) {
        setOperation('x');
    }

    @OnClick(R.id.btnDiv)
    public void divide(View view) {
        setOperation('÷');
    }

    @OnClick(R.id.btnPercent)
    public void percent(View view) {
        setOperation('%');
    }

    /**
     * Undo the last number.
     */
    @OnClick(R.id.btnBS)
    public void backSpace(View view) {
        String txt = showOperation.getText().toString();
        if (txt.length() > 0) {
            txt = txt.substring(0, txt.length() - 1);
            showOperation.setText(txt);
        }
    }

    @OnClick(R.id.btnZero)
    public void zero(View view) {
        deletePreviousResult();
        setAll("0");
    }

    @OnClick(R.id.btn1)
    public void one(View view) {
        deletePreviousResult();
        setAll("1");
    }

    @OnClick(R.id.btn2)
    public void two(View view) {
        deletePreviousResult();
        setAll("2");
    }

    @OnClick(R.id.btn3)
    public void three(View view) {
        deletePreviousResult();
        setAll("3");
    }

    @OnClick(R.id.btn4)
    public void four(View view) {
        deletePreviousResult();
        setAll("4");
    }

    @OnClick(R.id.btn5)
    public void five(View view) {
        deletePreviousResult();
        setAll("5");
    }

    @OnClick(R.id.btn6)
    public void six(View view) {
        deletePreviousResult();
        setAll("6");
    }

    @OnClick(R.id.btn7)
    public void seven(View view) {
        deletePreviousResult();
        setAll("7");
    }

    @OnClick(R.id.btn8)
    public void eight(View view) {
        deletePreviousResult();
        setAll("8");
    }

    @OnClick(R.id.btn9)
    public void nine(View view) {
        deletePreviousResult();
        setAll("9");
    }

    @OnClick(R.id.btnPoint)
    public void point(View view) {
        deletePreviousResult();
        setAll(".");
    }

    /**
     * Set a negative number.
     */
    @OnClick(R.id.btnNegative)
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

    @OnClick(R.id.btnEquals)
    public void equals(View view) {
        try {
            // Split it
            String[] splittedText = getTheSecondNumber();

            if (splittedText.length > 1) {

                Log.d(TAG, "array: " + Arrays.toString(splittedText));
                Log.d(TAG, "firstNumber: " + firstNumber + " secondNumber: " + secondNumber);
                Log.d(TAG, "operation: " + mOperation);

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
                            result = Double.POSITIVE_INFINITY;
                            displayResult.setText(String.valueOf(result));
                            break;
                        } else {
                            result = firstNumber / secondNumber;
                        }
                        break;
                    case '%':
                        result = firstNumber % secondNumber;
                        break;
                }

                displayColouredResult();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayColouredResult() {

        // Get the whole number of the result
        wholeNumber = result.intValue();
        // Check if result is whole number or not
        checkResult = result / wholeNumber;

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

        Log.d(TAG, "equals: " + coloredWholeNumber);
    }

    /**
     * get the second number
     */
    private String[] getTheSecondNumber() {
        // Get the Text
        String text = showOperation.getText().toString();

        if (mOperation == 'x') {
            // Split by operator x
            return text.split(String.valueOf("" + mOperation));
        } else {
            // Split by operator +, -, ÷
            return text.split(String.valueOf("\\" + mOperation));
        }
    }

    /**
     * Color the final result
     */
    private Spanned colorResult(String number) {
        if (Build.VERSION.SDK_INT >= 24) {
            return (Html.fromHtml(number, 1)); // for 24 api and more
        } else {
            return Html.fromHtml(number);// or for older api
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
        @SuppressLint("ClickableViewAccessibility")
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble(FIRST_NUMBER, firstNumber);
        outState.putDouble(SECOND_NUMBER, secondNumber);
        outState.putChar(OPERATOR, mOperation);
        if (result != null) {
            outState.putDouble(RESULT, result);
            outState.putInt(WHOLE_NUMBER, wholeNumber);
        }

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_play_sound) {
            if (displayResult.getText().length() > 0) {
                if (result == 0.0) {
                    playSound("0",this);
                } else if (checkResult != 1) {
                    playSound(String.valueOf(result),this);
                } else {
                    playSound(String.valueOf(wholeNumber),this);
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
