package com.example.singinsingup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity_2 extends AppCompatActivity {
    TextView tv_bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        tv_bienvenido=findViewById(R.id.tv_bienvenido);

        String dato=getIntent().getStringExtra("email2");
        tv_bienvenido.setText("Bienvenido "+dato);
    }
}