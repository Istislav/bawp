package ru.istislav.bawp.controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.istislav.bawp.R;

public class DetailsActivity extends AppCompatActivity {
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        courseId = bundle.getInt("course_id");

        Toast.makeText(this, "Clicked: " + courseId, Toast.LENGTH_SHORT).show();

    }
}