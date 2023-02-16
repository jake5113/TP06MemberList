package com.jake5113.tp06memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ArrayList<Member> members = new ArrayList<>();

    EditText etName;
    RadioGroup rg;
    RadioButton rbMale, rbFemale;
    Spinner spinner;
    Button btnAdd, btnCancel;
    String nation;

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
        if (item.getItemId() == R.id.add) {
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


            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get name
                    String name = etName.getText().toString();
                    // get gender
                    int id = rg.getCheckedRadioButtonId();
                    RadioButton radioButton = dialog.findViewById(id);
                    String gender = radioButton.getText().toString();
                    //get nation
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] city = getResources().getStringArray(R.array.city);
                            nation = city[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {}
                    });
                    //TODO 여기서부터 수정해야함. get(0)이어서 계속 확인이 불가 & gender값이 null 임
                    members.add(new Member(name, nation));
                    Toast.makeText(MainActivity.this, members.get(0).name + members.get(0).nation, Toast.LENGTH_SHORT).show();
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