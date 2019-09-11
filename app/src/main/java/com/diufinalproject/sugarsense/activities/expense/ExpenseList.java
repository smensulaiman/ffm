package com.diufinalproject.sugarsense.activities.expense;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.diufinalproject.sugarsense.R;
import java.util.ArrayList;

public class ExpenseList extends AppCompatActivity {

    Button btnAddNewNote;
    TextView txtViewResponse;
    RecyclerView recyclerViewList;
    UserExpenseListAdapter adapter;

    TextView txtCompanyName;
    TextView txtContactPerson;
    TextView txtPurpose;
    TextView txtDateTime;
    TextView txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtContactPerson = findViewById(R.id.txtContactPerson);
        txtPurpose = findViewById(R.id.txtPurpose);
        txtDateTime = findViewById(R.id.txtDateTime);
        txtAmount = findViewById(R.id.txtAmount);

        btnAddNewNote = findViewById(R.id.btnAddNewNote);

        btnAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseList.this,ExpenseActivity.class));
            }
        });

        ArrayList <ModelUserVisit> arrayList =  new ArrayList<ModelUserVisit>();
        arrayList.add(new ModelUserVisit(ExpenseSession.companyName, ExpenseSession.purpose, ExpenseSession.contactPerson, ExpenseSession.amount, ExpenseSession.dateTime));

        recyclerViewList = findViewById(R.id.recyclerViewList);
        adapter = new UserExpenseListAdapter(ExpenseList.this,arrayList);
        recyclerViewList.setAdapter(adapter);

        if(ExpenseSession.isEmpty){
            findViewById(R.id.layout_row).setVisibility(View.INVISIBLE);
        }else {
            findViewById(R.id.layout_row).setVisibility(View.VISIBLE);
            txtCompanyName.setText(ExpenseSession.companyName);
            txtContactPerson.setText(ExpenseSession.contactPerson);
            txtPurpose.setText(ExpenseSession.purpose);
            txtDateTime.setText(ExpenseSession.dateTime);
            txtAmount.setText(ExpenseSession.amount);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ExpenseSession.onBack){
            if(ExpenseSession.isEmpty){
                findViewById(R.id.layout_row).setVisibility(View.INVISIBLE);
            }else {
                findViewById(R.id.layout_row).setVisibility(View.VISIBLE);
                txtCompanyName.setText(ExpenseSession.companyName);
                txtContactPerson.setText(ExpenseSession.contactPerson);
                txtPurpose.setText(ExpenseSession.purpose);
                txtDateTime.setText(ExpenseSession.dateTime);
                txtAmount.setText(ExpenseSession.amount);
            }
            ExpenseSession.onBack = false;
        }
    }
}
