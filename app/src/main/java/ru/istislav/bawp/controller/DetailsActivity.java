package ru.istislav.bawp.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.istislav.bawp.R;
import ru.istislav.bawp.data.CourseData;
import ru.istislav.bawp.model.Course;

public class DetailsActivity extends AppCompatActivity {
    private int courseId;
    private Course course;
    private ImageView courseImageView;
    private TextView courseTitle;
    private InputMethodManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        setUpUI();
        loadCourse();
    }

    private void loadCourse() {
        Bundle bundle = getIntent().getExtras();
        courseId = bundle.getInt("course_id");

        Toast.makeText(this, "Clicked: " + courseId, Toast.LENGTH_SHORT).show();

        course = new CourseData().courseList().get(courseId);
        courseImageView.setImageResource(course.getImageResourceId(this));
        courseTitle.setText(course.getCourseName());
    }

    private void setUpUI() {
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        courseImageView = (ImageView) findViewById(R.id.detailsCourseImage);
        courseTitle = (TextView) findViewById(R.id.detailsCourseTitle);

    }

}