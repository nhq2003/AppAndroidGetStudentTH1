package com.example.week4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {
    EditText InputName, InputMSSV, InputClass, InputSDT, InputText;
    Button BtnSubmit, BtnClear, BtnCall, BtnSMS;
    RadioGroup radioGroupYear; // Thay vì khai báo từng RadioButton
    CheckBox CheckBox1, CheckBox2, CheckBox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        InputName = findViewById(R.id.InputName);
        InputMSSV = findViewById(R.id.InputMSSV);
        InputClass = findViewById(R.id.InputClass);
        InputSDT = findViewById(R.id.InputSDT);
        InputText = findViewById(R.id.InputText);

        BtnSubmit = findViewById(R.id.BtnSubmit);
        BtnClear = findViewById(R.id.BtnClear);
        BtnCall = findViewById(R.id.BtnCall);
        BtnSMS = findViewById(R.id.BtnSMS);

        // Ánh xạ RadioGroup
        radioGroupYear = findViewById(R.id.radioGroupYear);

        // Ánh xạ CheckBox
        CheckBox1 = findViewById(R.id.CheckBox1);
        CheckBox2 = findViewById(R.id.CheckBox2);
        CheckBox3 = findViewById(R.id.CheckBox3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Log_in.class);
                String Name = InputName.getText().toString().trim();
                String MSSV = InputMSSV.getText().toString().trim();
                String Class = InputClass.getText().toString().trim();
                String SDT = InputSDT.getText().toString().trim();
                String Text = InputText.getText().toString().trim();

                // Lấy thông tin năm học từ RadioGroup
                String year = "";
                int selectedId = radioGroupYear.getCheckedRadioButtonId();
                if (selectedId == R.id.Btn1) {
                    year = "Năm 1";
                } else if (selectedId == R.id.Btn2) {
                    year = "Năm 2";
                } else if (selectedId == R.id.Btn3) {
                    year = "Năm 3";
                } else if (selectedId == R.id.Btn4) {
                    year = "Năm 4";
                }

                // Lấy thông tin chuyên ngành từ CheckBox
                String major = "";
                if (CheckBox1.isChecked()) major += "MT - HTN ";
                if (CheckBox2.isChecked()) major += "Điện tử ";
                if (CheckBox3.isChecked()) major += "Mạng - Viễn thông";

                Bundle bundle = new Bundle();
                bundle.putString("InputName", Name);
                bundle.putString("InputMSSV", MSSV);
                bundle.putString("InputClass", Class);
                bundle.putString("InputSDT", SDT);
                bundle.putString("Year", year);
                bundle.putString("Major", major);
                bundle.putString("InputText", Text);

                intent.putExtra("package", bundle);
                startActivity(intent);
            }
        });

        BtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputName.setText("");
                InputMSSV.setText("");
                InputClass.setText("");
                InputSDT.setText("");
                InputText.setText("");

                CheckBox1.setChecked(false);
                CheckBox2.setChecked(false);
                CheckBox3.setChecked(false);

                // Xóa lựa chọn trong RadioGroup
                radioGroupYear.clearCheck();
            }
        });

        BtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = InputSDT.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    Intent callIntent = new Intent(MainActivity.this, Call_Screen.class);
                    callIntent.putExtra("phoneNumber", phoneNumber);
                    startActivity(callIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = InputSDT.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    Intent smsIntent = new Intent(MainActivity.this, Sms_Screen.class);
                    smsIntent.putExtra("phoneNumber", phoneNumber);
                    startActivity(smsIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}