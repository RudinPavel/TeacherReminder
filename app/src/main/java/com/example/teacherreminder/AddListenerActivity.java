package com.example.teacherreminder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teacherreminder.db.MyDatabase;

public class AddListenerActivity extends AppCompatActivity {


    MyDatabase DBConnector;
    Context context;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;

    Button buttonAddListenerButton;
    Button buttonReturnToMainPage;

    EditText editTextName;
    EditText editTextLogin;
    EditText editTextEmail;
    EditText editTextInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listener);

        buttonAddListenerButton = findViewById(R.id.buttonAddListenerButton);
        buttonReturnToMainPage = findViewById(R.id.buttonReturnToMainPage);

        editTextName = findViewById(R.id.editTextAddListenerName);
        editTextLogin = findViewById(R.id.editTextAddListenerLogin);
        editTextEmail = findViewById(R.id.editTextAddListenerEmail);
        editTextInfo = findViewById(R.id.editTextAddListenerInfo);

        context = this;
        DBConnector = new MyDatabase(this);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonAddListenerButton:

                        DBConnector.insert(editTextName.getText().toString(), editTextLogin.getText().toString(),
                                editTextEmail.getText().toString(), editTextInfo.getText().toString());

                        Toast.makeText(context, "Слушатель добавлен в базу", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(AddListenerActivity.this, MainPageActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.buttonReturnToMainPage:
                        finish();
                        break;
                }
            }
        };

        buttonAddListenerButton.setOnClickListener(listener);
        buttonReturnToMainPage.setOnClickListener(listener);
    }
}
