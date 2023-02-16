package com.jake5113.tp06memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ArrayList<Member> members = new ArrayList<>();

    EditText etName;
    RadioGroup rg;
    RadioButton rbMale, rbFemale;
    Spinner spinner;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);

        MenuItem addItem = menu.findItem(R.id.add);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("이름을 입력하세요");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.dialog);
            AlertDialog dialog = builder.create();
            dialog.show();

            etName = dialog.findViewById(R.id.et_name);
            rg = dialog.findViewById(R.id.rg_gender);
            rbMale = dialog.findViewById(R.id.rb_male);
            rbFemale = dialog.findViewById(R.id.rb_female);
            spinner = dialog.findViewById(R.id.spinner);
            btnAdd = dialog.findViewById(R.id.btn_add_member);
            btnCancel = dialog.findViewById(R.id.btn_cancel);

            String name = etName.getText().toString();


            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }
}