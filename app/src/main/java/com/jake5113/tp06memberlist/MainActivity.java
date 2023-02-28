package com.jake5113.tp06memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    ArrayList<Member> members = new ArrayList<>();
    EditText etName;
    RadioGroup radioGroup;
    RadioButton rbMale, rbFemale;
    Spinner spinner;
    Button btnAdd, btnCancel;
    String nation;
    int imgGender, imgNation;
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 테마 설정
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(MainActivity.this, members);
        recyclerView.setAdapter(adapter);

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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog);
        AlertDialog dialog = builder.create();
        dialog.show();

        etName = dialog.findViewById(R.id.et_name);
        radioGroup = dialog.findViewById(R.id.rg_gender);
        rbMale = dialog.findViewById(R.id.rb_male);
        rbFemale = dialog.findViewById(R.id.rb_female);
        spinner = dialog.findViewById(R.id.spinner);
        btnAdd = dialog.findViewById(R.id.btn_add_member);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        //get nation
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] city = getResources().getStringArray(R.array.city);
                nation = city[position];
                imgNation = R.drawable.flag_australia + position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String[] city = getResources().getStringArray(R.array.city);
                nation = city[0];
                imgNation = R.drawable.flag_australia;
            }
        });

        // get gender
        radioGroup = dialog.findViewById(R.id.rg_gender);
        imgGender = R.drawable._male;
        assert radioGroup != null;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbMale.isChecked()) imgGender = R.drawable._male;
                else if (rbFemale.isChecked()) imgGender = R.drawable._female;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get name
                String name = etName.getText().toString();
                if (name.equals("")) name = "익명";

                // 멤버 추가 및 리사이클러뷰에 표시
                members.add(0, new Member(name, nation, imgGender, imgNation));
                adapter.notifyItemInserted(0);
                recyclerView.scrollToPosition(0);
                if (!members.isEmpty()) findViewById(R.id.tv_data).setVisibility(View.GONE);
            }
        });

        // Dialog Cancel 버튼 클릭시
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        return super.onOptionsItemSelected(item);
    }

}