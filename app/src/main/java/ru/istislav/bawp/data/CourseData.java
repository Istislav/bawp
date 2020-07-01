package ru.istislav.bawp.data;

import java.util.ArrayList;

import ru.istislav.bawp.model.Course;

public class CourseData {
    private String[] courseNames = { "Course 1", "Course 2", "Course 3", "Course 4", "Course 5", "Course 6", };

    public ArrayList<Course> courseList() {
        ArrayList<Course> list = new ArrayList<>();

        for (int i=0; i<courseNames.length; i++) {
            Course course =  new Course(courseNames[i],
                    courseNames[i].replaceAll("\\s+", "_").toLowerCase(), "happy_woman");
            list.add(course);
        }
        return list;
    }
}
