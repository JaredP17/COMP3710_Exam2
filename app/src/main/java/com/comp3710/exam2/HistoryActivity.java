package com.comp3710.exam2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Double balance = 0.0;
    TextView balanceView, noData;
    TableLayout tableLayout;
    TableRow tableRow;
    DecimalFormat fmt = new DecimalFormat("$#,###,##0.00"); // Balance format
    DecimalFormat fmt2 = new DecimalFormat("#,##0.00"); // Amount format in table

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        myDB = new DatabaseHelper(this);
        balanceView = (TextView) findViewById(R.id.balanceTextView);
        noData = (TextView) findViewById(R.id.noDataTextView);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableRow = (TableRow) findViewById(R.id.tableRow);

        Cursor data = myDB.getData();

        if (data.getCount() == 0) {
            noData.setVisibility(View.VISIBLE);
            tableLayout.setVisibility(View.INVISIBLE);
            balanceView.setText(String.format(getText(R.string.current_balance).toString(), fmt.format(balance)));

        }

        else {
            ArrayList<Transaction> tList = new ArrayList<Transaction>();
            noData.setVisibility(View.INVISIBLE);
            tableLayout.setVisibility(View.VISIBLE);

            int i = 0;
            while (data.moveToNext()) { // Iterate through database and add transactions to an array list
                Transaction t = new Transaction();
                t.setDate(data.getString(0));
                t.setAmount(data.getDouble(1));
                t.setCategory(data.getString(2));
                tList.add(t);

                TableRow row = new TableRow(this);

                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 3f);
                params.setMargins(2,2,2,2);

                // New rows for table in transaction history
                TextView tv = new TextView(this);
                tv.setLayoutParams(params);
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(16);
                tv.setPadding(20, 5, 0, 5);
                tv.setText(t.getDate());

                TextView tv2 = new TextView(this);
                tv2.setLayoutParams(params);
                tv2.setBackgroundColor(Color.WHITE);
                tv2.setTextColor(Color.BLACK);
                tv2.setTextSize(16);
                tv2.setGravity(Gravity.RIGHT);
                tv2.setPadding(0, 5, 20, 5);
                tv2.setText(fmt2.format(t.getAmount()));

                TextView tv3 = new TextView(this);
                tv3.setLayoutParams(params);
                tv3.setBackgroundColor(Color.WHITE);
                tv3.setTextColor(Color.BLACK);
                tv3.setTextSize(16);
                tv3.setPadding(20, 5, 0, 5);
                tv3.setText(t.getCategory());

                // Add views to new row
                tableLayout.addView(row);
                row.addView(tv);
                row.addView(tv2);
                row.addView(tv3);

                //Toast.makeText(HistoryActivity.this, tList.get(i).getDate(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(HistoryActivity.this, tList.get(i).getAmount() + "", Toast.LENGTH_SHORT).show();
                balance += tList.get(i).getAmount();
                //Toast.makeText(HistoryActivity.this, tList.get(i).getCategory(), Toast.LENGTH_SHORT).show();
                i++;
            }
            balanceView.setText(String.format(getText(R.string.current_balance).toString(), fmt.format(balance)));
            }
        }
    }
