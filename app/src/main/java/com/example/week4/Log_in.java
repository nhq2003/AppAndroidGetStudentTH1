package com.example.week4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Log_in extends AppCompatActivity {
    TextView ResultText;
    Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        ResultText = findViewById(R.id.ResultText);

        BtnBack = findViewById(R.id.BtnBack);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Nhan Intent, lay bundle khoi intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        if (bundle != null) {
            // Lấy dữ liệu từ Bundle
            String Name = bundle.getString("InputName","");
            String MSSV = bundle.getString("InputMSSV","");
            String Class = bundle.getString("InputClass","");
            String SDT = bundle.getString("InputSDT","");
            String year = bundle.getString("Year","");
            String major = bundle.getString("Major","");
            String Text = bundle.getString("InputText","");

            // Tạo chuỗi hiển thị kết quả
            String result = "Thông tin sinh viên:\n\n"
                    + "Họ tên: " + Name + "\n"
                    + "MSSV: " + MSSV + "\n"
                    + "Lớp: " + Class + "\n"
                    + "SĐT: " + SDT + "\n"
                    + "Sinh viên nănm: " + (year.isEmpty() ? "Không chọn" : year) + "\n"
                    + "Chuyên ngành: " + (major.isEmpty() ? "Không chọn" : major) + "\n"
                    + "Định hướng: " +  Text + "\n";
            // Hiển thị thông tin trên TextView
            ResultText.setText(result);
        }

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}