package edu.citadel.tyler.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.io.Serializable;
import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity{
    Button cButton;
    Button ceButton;
    Button addButton;
    Button subtractButton;
    Button multiplyButton;
    Button divideButton;
    Button dotButton;
    Button signButton;
    Button equalsButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    TextView textScreen;
    int screenSave;
    CalculatorEngine ce = new CalculatorEngine(12);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cButton = (Button) findViewById(R.id.cButton);
        ceButton = (Button) findViewById(R.id.ceButton);
        addButton = (Button) findViewById(R.id.addButton);
        subtractButton = (Button) findViewById(R.id.subtractButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        dotButton = (Button) findViewById(R.id.dotButton);
        signButton = (Button) findViewById(R.id.signButton);
        equalsButton = (Button) findViewById(R.id.equalsButton);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        textScreen = (TextView) findViewById(R.id.textScreen);

        cButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.clear();
                textScreen.setText(ce.getDisplay());
            }
        });
        ceButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.clearEntry();
                textScreen.setText(ce.getDisplay());
            }
        });

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.perform(Operation.ADD);
                textScreen.setText(ce.getDisplay());
            }
        });

        subtractButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.perform(Operation.SUBTRACT);
                textScreen.setText(ce.getDisplay());
            }
        });
        multiplyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.perform(Operation.MULTIPLY);
                textScreen.setText(ce.getDisplay());
            }
        });
        divideButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.perform(Operation.DIVIDE);
                textScreen.setText(ce.getDisplay());
            }
        });
        signButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.toggleSign();
                textScreen.setText(ce.getDisplay());
            }
        });
        dotButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('.');
                textScreen.setText(ce.getDisplay());
            }
        });
        equalsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.perform(Operation.EQUALS);
                textScreen.setText(ce.getDisplay());
            }
        });
        button0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('0');
                textScreen.setText(ce.getDisplay());
            }
        });
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('1');
                textScreen.setText(ce.getDisplay());
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('2');
                textScreen.setText(ce.getDisplay());
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('3');
                textScreen.setText(ce.getDisplay());
            }
        });
        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('4');
                textScreen.setText(ce.getDisplay());
            }
        });
        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('5');
                textScreen.setText(ce.getDisplay());
            }
        });
        button6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('6');
                textScreen.setText(ce.getDisplay());
            }
        });
        button7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('7');
                textScreen.setText(ce.getDisplay());
            }
        });
        button8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('8');
                textScreen.setText(ce.getDisplay());
            }
        });
        button9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ce.insert('9');
                textScreen.setText(ce.getDisplay());
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("save",ce);       // call appropriate outState "put" methods
    }
    @Override
    protected void onRestoreInstanceState(Bundle inState)
    {
        super.onRestoreInstanceState(inState);
        ce = (CalculatorEngine) inState.getSerializable("save");
        textScreen.setText(ce.getDisplay());
    }

    public class CalculatorEngine implements Serializable
    {
        private static final long serialVersionUID = -5587095393398443766L;

        private final int maxDisplayLength;
        private double accumulator;
        private double maxValue;
        private double minValue;
        private StringBuilder display;
        private Operation savedOp;
        private boolean opPerformed;
        private boolean hasErrors;


        /**
         * Construct a new calculator engine with the specified maximum display
         * length.  The display is initialized to "0".
         */
        public CalculatorEngine(int maxDisplayLength)
        {
            this.maxDisplayLength = maxDisplayLength;
            display = new StringBuilder(maxDisplayLength);

            // set max and min values
            StringBuilder buffer = new StringBuilder(maxDisplayLength);
            for (int n = 0;  n < maxDisplayLength;  ++n)
                buffer.append('9');

            maxValue = Double.parseDouble(buffer.toString());

            buffer.setCharAt(0, '-');
            minValue = Double.parseDouble(buffer.toString());

            clear();
        }


        /**
         * Default constructor.  Construct a new calculator engine with
         * a display length of 12.  The display is initialized to "0".
         */
        public CalculatorEngine()
        {
            this(12);
        }


        /**
         * Clear the calculator.  The accumulator is set to 0.0, and the
         * display is set to "0".
         */
        public void clear()
        {
            hasErrors = false;
            clearEntry();
            accumulator = 0.0;
            savedOp = null;
            opPerformed = false;
        }


        /**
         * Clear the display;  that is, set the display to "0".
         */
        public void clearEntry()
        {
            if (!hasErrors)
            {
                display.setLength(0);
                display.append('0');
            }
        }


        /**
         * Append a new character to the end of the display.  The new character
         * must be either a digit or a decimal point, and the number of characters
         * in the display must be less than the maximum display length;  otherwise
         * this method has no effect, and the input character is ignored.
         *
         * @param c  the character to be appended to the end of the display.
         */
        public void insert(char c)
        {
            if (!hasErrors && display.length() < maxDisplayLength)
            {
                if (opPerformed)
                {
                    clearEntry();
                    opPerformed = false;
                }

                if (Character.isDigit(c))
                {
                    if (display.length() == 1 && display.charAt(0) == '0')
                        display.setLength(0);

                    display.append(c);
                }
                else if (c == '.')
                    display.append(c);
            }
        }


        /**
         * Toggles the sign (+/-) of the display.
         */
        public void toggleSign()
        {
            if (!hasErrors)
            {
                char leadingChar = display.charAt(0);

                if (getDisplayValue() != 0.0)
                {
                    if (leadingChar == '-')
                        display.deleteCharAt(0);
                    else if (display.length() < maxDisplayLength)
                        display.insert(0, '-');
                }
            }
        }


        /**
         *  Returns a string representation of the display.
         */
        public String getDisplay()
        {
            return display.toString();
        }


        /**
         *  Returns a double representation of the display.
         */
        public double getDisplayValue()
        {
            if (!hasErrors)
                return Double.parseDouble(display.toString());
            else
                return Double.NaN;
        }


        /**
         * Perform the specified operation.
         */
        public void perform(Operation op)
        {
            if (!hasErrors && savedOp != null)
            {
                switch (savedOp)
                {
                    case ADD:
                        accumulator += getDisplayValue();
                        break;
                    case SUBTRACT:
                        accumulator -= getDisplayValue();
                        break;

                    case MULTIPLY:
                        accumulator *= getDisplayValue();
                        break;

                    case DIVIDE:
                        accumulator /= getDisplayValue();
                        break;
                    default:
                        // do nothing
                }

                if (isInRange(accumulator))
                {
                    setDisplay(accumulator);
                    savedOp = null;
                }
                else
                    error();
            }

            if (!hasErrors || op == Operation.CLEAR)
            {
                switch (op)
                {
                    case ADD:
                    case SUBTRACT:
                    case MULTIPLY:
                    case DIVIDE:
                        accumulator = getDisplayValue();
                        savedOp = op;
                        break;

                    case EQUALS:
                        setDisplay(accumulator);
                        break;

                    case CLEAR:
                        clear();
                        break;

                    case CLEAR_ENTRY:
                        clearEntry();
                        break;
                }

                opPerformed = true;
            }
        }


        /**
         * Sets display to a string version of the specified value.
         */
        private void setDisplay(double value)
        {
            if (isInRange(value))
            {
                String valueStr = Double.toString(value);

                // if valueStr contains a decimal point, round value
                // to maxDisplayLength and remove trailing zeros
                int decimalPointIndex = valueStr.indexOf(".");
                if (decimalPointIndex > 0)
                {
                    // round to maxDisplayLength
                    if (valueStr.length() > maxDisplayLength)
                    {
                        NumberFormat nf = NumberFormat.getInstance();
                        nf.setGroupingUsed(false);
                        nf.setMaximumFractionDigits(maxDisplayLength - decimalPointIndex - 1);
                        valueStr = nf.format(value);
                    }
                }

                // if valueStr still contains a decimal point, check for
                // and remove ".0" as the last two characters
                decimalPointIndex = valueStr.indexOf(".");
                if (decimalPointIndex > 0 && valueStr.length() >= 3)
                {
                    if (decimalPointIndex == valueStr.length() - 2
                            && valueStr.charAt(valueStr.length() -1) == '0')
                    {
                        valueStr = valueStr.substring(0, valueStr.length() - 2);
                    }
                }

                if (valueStr.length() > maxDisplayLength)
                    error();
                else
                {
                    display.setLength(0);
                    display.append(valueStr);
                }
            }
            else
                error();
        }


        /**
         * Returns true if the specified value is within the allowable
         * range for this CalculatorEngine.
         */
        private boolean isInRange(double value)
        {
            return value <= maxValue && value >= minValue;
        }


        /**
         * Signal an error within this CalculatorEngine.
         */
        private void error()
        {
            clear();
            hasErrors = true;
            accumulator = Double.NaN;
            display.setLength(0);
            display.append("Error");
        }


        @Override
        public String toString()
        {
            return "CalculatorEngine [accumulator=" + accumulator + ", display=" + display
                    + ", opPerformed=" + opPerformed + ", savedOp=" + savedOp + "]";
        }
    }
    // Operation File
    public enum Operation
    {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUALS, CLEAR, CLEAR_ENTRY;
    }
}
