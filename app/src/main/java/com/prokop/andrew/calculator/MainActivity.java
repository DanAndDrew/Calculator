package com.prokop.andrew.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText operand1;
    private EditText operand2;
    private Button btnAddition;
    private Button btnSubtraction;
    private Button btnDivision;
    private Button btnMultiplication;
    private TextView txtResult;
    private Button btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operand1 = (EditText) findViewById(R.id.editOperand1);
        operand2 = (EditText) findViewById(R.id.editOperand2);
        btnAddition = (Button) findViewById(R.id.btnAddition);
        btnSubtraction = (Button) findViewById(R.id.btnSubtraction);
        btnMultiplication = (Button) findViewById(R.id.btnMultiplication);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand1.setText("");
                operand2.setText("");
                txtResult.setText("0.00");
                operand1.requestFocus();
            }
        });


        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCalculation("+");
            }
        });

        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCalculation("-");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCalculation("/");
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCalculation("*");
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private boolean editTextNotBlank() {
        boolean textNotBlank = false;
        if ((operand1.getText().length()>0) && (operand2.getText().length()>0)) {
              textNotBlank = true;
        }
        return textNotBlank;
    }

    private double getOperand1() {
        return Double.parseDouble(operand1.getText().toString());
    }

    private double getOperand2() {
        return Double.parseDouble(operand2.getText().toString());
    }

    private void resetAndSetResultAsOperand1() {
        operand1.setText(txtResult.getText());
        txtResult.setText("0.00");
        operand2.setText("");
        operand2.requestFocus();
    }

    private void completeCalculation(String action) {
        if (editTextNotBlank() && Double.parseDouble(txtResult.getText().toString()) == 0.0) {
            switch (action) {
                case "+":
                    txtResult.setText(Double.toString(getOperand1() + getOperand2()));
                    break;
                case "-":
                    txtResult.setText(Double.toString(getOperand1() - getOperand2()));
                    break;
                case "/":
                    txtResult.setText(Double.toString(getOperand1() / getOperand2()));
                    break;
                case "*":
                    txtResult.setText(Double.toString(getOperand1() * getOperand2()));
                    break;
                default:
                    break;

            }
        }else if (editTextNotBlank() && Double.parseDouble(txtResult.getText().toString()) != 0.0) {
            resetAndSetResultAsOperand1();
        }
        else {
            Toast.makeText(MainActivity.this, "Please enter numbers in both operand fields",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
