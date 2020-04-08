package com.visionchatsvirtualcode7ec.visionchats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.visionchatsvirtualcode7ec.visionchats.Views.registroActivity;

public class MainActivity extends AppCompatActivity
{
    Button btn_inicio_session_java;
    Button btn_registro_java;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_registro_java = findViewById(R.id.btn_registro_xml);
        btn_registro_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,registroActivity.class);
                startActivity(intent);
            }
        });
    }
}
