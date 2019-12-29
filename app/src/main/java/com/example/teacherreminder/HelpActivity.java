package com.example.teacherreminder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    Button buttonReturnToMainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        buttonReturnToMainPage = findViewById(R.id.buttonReturnToMainPage);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonReturnToMainPage:
                        finish();
                        break;
                }
            }
        };

        buttonReturnToMainPage.setOnClickListener(listener);
    }
}
