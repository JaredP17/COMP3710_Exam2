package com.comp3710.exam2;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    EditText date, amount, category;
    Button add, spend;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        myDB = new DatabaseHelper(this);

        date = (EditText) findViewById(R.id.dateEditText);
        amount = (EditText) findViewById(R.id.amountEditText);
        category = (EditText) findViewById(R.id.categoryEditText);
        add = (Button) findViewById(R.id.addButton);
        spend = (Button) findViewById(R.id.spendButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(0);
            }
        });

        spend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(1);
            }
        });
    }

    private void addData(int version) {
        if(missingField()) {
            Toast.makeText(TransactionActivity.this, "Missing Fields", Toast.LENGTH_SHORT).show();
        }

        else {
            boolean isInserted;
            if (version == 1) {
                isInserted = myDB.insertData(date.getText().toString(),
                        -Double.parseDouble(amount.getText().toString()),
                        category.getText().toString());
            }

            else {
                isInserted = myDB.insertData(date.getText().toString(),
                        Double.parseDouble(amount.getText().toString()),
                        category.getText().toString());
            }

            if (isInserted) {
                Toast.makeText(TransactionActivity.this, "Successful Transaction", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(TransactionActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
            }

            clearFields();
        }
    }

    // Clears transaction EditText fields
    private void clearFields() {
        date.getText().clear();
        amount.getText().clear();
        category.getText().clear();
    }

    // Checks if required information is missing
    private boolean missingField() {
       return date.getText().toString().isEmpty() || amount.getText().toString().isEmpty() || category.getText().toString().isEmpty();
    }
}
