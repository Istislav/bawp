package ru.istislav.bawp.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
    private EditText commentEditText;
    private ArrayList<String> comments;
    private ArrayAdapter<String> commentsAdapter;
    private ListView commentsListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        setUpUI();
        setUpAdapter();
        loadCourse();
    }

    private void setUpAdapter() {
        commentsListview = (ListView) findViewById(R.id.detailsCommentsListView);
        comments = new ArrayList<>();
        comments.add("Hello!");
        comments.add("How are you?");
        commentsAdapter = new ArrayAdapter<String>(this, R.layout.comment_row, comments);
        commentsListview.setAdapter(commentsAdapter);
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

        commentEditText = findViewById(R.id.detailsComments);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detailsAddButton:
                if(!isEditTextVisible) {
                    revealEditText(revealView);
                    commentEditText.requestFocus();
                    inputManager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT);

                    button.setImageResource(R.drawable.icn_morph);
                    Animatable animatable = (Animatable) button.getDrawable();
                    animatable.start();
                } else {
                    hideEditText(revealView);
                    button.setImageResource(R.drawable.icn_morph_reverse);
                    inputManager.hideSoftInputFromWindow(commentEditText.getWindowToken(), 0);
                    Animatable animatable = (Animatable) button.getDrawable();
                    animatable.start();
                }
                break;
        }
    }

    private void hideEditText(final LinearLayout revealView) {
        int cx = revealView.getRight() - 30;
        int cy = revealView.getBottom() - 60;

        int initialRadius = revealView.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, initialRadius, 0f);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealView.setVisibility(View.INVISIBLE);
            }
        });
        isEditTextVisible = false;
        anim.start();
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