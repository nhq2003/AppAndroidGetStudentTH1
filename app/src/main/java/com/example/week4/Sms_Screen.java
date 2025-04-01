package com.example.week4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Sms_Screen extends AppCompatActivity {
    private TextView phoneNumberText, smsHistory;
    private EditText smsMessage;
    private Button sendButton, backButton;
    private StringBuilder messageHistory = new StringBuilder();
    private static final int SMS_PERMISSION_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_screen);

        phoneNumberText = findViewById(R.id.phoneNumber);
        smsMessage = findViewById(R.id.smsMessage);
        smsHistory = findViewById(R.id.smsHistory);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        phoneNumberText.setText(phoneNumber);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = smsMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    if (ContextCompat.checkSelfPermission(Sms_Screen.this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Sms_Screen.this,
                                new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                    } else {
                        sendSms(phoneNumber, message);
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sms_Screen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendSms(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            messageHistory.append("Gửi: ").append(message).append("\n");
            smsHistory.setText(messageHistory.toString());
            smsMessage.setText("");
            Toast.makeText(this, "Đã gửi SMS", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Không thể gửi SMS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = phoneNumberText.getText().toString();
                String message = smsMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendSms(phoneNumber, message);
                }
            } else {
                Toast.makeText(this, "Cần cấp quyền để gửi SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}