package edu.manuel.android.login.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.manuel.android.login.R;

/**
 * Created by postgres on 21/9/2017.
 */

public class UsersActivity extends AppCompatActivity{

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Bienvenido "+nameFromIntent);
    }
}
