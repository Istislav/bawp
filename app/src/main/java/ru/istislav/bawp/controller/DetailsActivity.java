package ru.istislav.bawp.controller;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.istislav.bawp.R;
import ru.istislav.bawp.data.CourseData;
import ru.istislav.bawp.model.Course;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private int courseId;
    private Course course;
    private ImageView courseImageView;
    private TextView courseTitle;
    private InputMethodManager inputManager;
    private LinearLayout revealView;
    private boolean isEditTextVisible = false;
    private FloatingActionButton button;

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
        revealView = (LinearLayout) findViewById(R.id.revealView);
        revealView.setVisibility(View.INVISIBLE);
        isEditTextVisible = false;

        button = (FloatingActionButton) findViewById(R.id.detailsAddButton);
        button.setOnClickListener(this); // Don't forget it!!!
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detailsAddButton:
                if(!isEditTextVisible) {
                    revealEditText(revealView);
                }
                break;
        }
    }

    private void revealEditText(LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;

        int finalRadius = Math.max(revealView.getWidth(), revealView.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0f, finalRadius);
        revealView.setVisibility(View.VISIBLE);
        isEditTextVisible = true;
        anim.start();
    }
}