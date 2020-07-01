package ru.istislav.bawp.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.istislav.bawp.R;
import ru.istislav.bawp.model.Course;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {
//    private ArrayList<Course> courseArrayList;
    private CourseData courseData = new CourseData();
    private OnItemClickListener itemClickListener;

    @NonNull
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);
        return new ViewHolder(courseRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListAdapter.ViewHolder holder, int position) {
        Course course = courseData.courseList().get(position);
        holder.courseTitle.setText(course.getCourseName());
        Picasso.with(holder.courseTitle.getContext())
                .load(course.getImageResourceId(holder.courseTitle.getContext()))
                .into(holder.courseImageView);
        Picasso.with(holder.courseTitle.getContext())
                .load(course.getImageResourceId(holder.courseTitle.getContext()))
                .into(holder.authorImageView);
    }

    @Override
    public int getItemCount() {
        return courseData.courseList().size();
//        return courseArrayList.size();
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView courseTitle;
        public ImageView courseImageView, authorImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //registering our view for click receiving
            itemView.setOnClickListener(this);

            courseTitle = itemView.findViewById(R.id.courceTitleId);
            courseImageView = itemView.findViewById(R.id.courseImageId);
            authorImageView = itemView.findViewById(R.id.authorImageId);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
