package com.example.week4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Call_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_screen);

        TextView phoneNumberText = findViewById(R.id.phoneNumber);
        Button endCallButton = findViewById(R.id.endCallButton);

        // Lấy số điện thoại từ Intent
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        phoneNumberText.setText(phoneNumber);

        // Nút kết thúc cuộc gọi
        endCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Call_Screen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish(); // Kết thúc CallScreenActivity
            }
        });
    }
}