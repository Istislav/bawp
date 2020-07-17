package ru.istislav.bawp.controller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import ru.istislav.bawp.R;
import ru.istislav.bawp.data.CourseListAdapter;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CourseListAdapter adapter;
    private Menu menu;
    private boolean isListView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);

        adapter = new CourseListAdapter();

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new CourseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DashboardActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        if (isListView) {
            showGridView();
        } else {
            showListView();
        }
    }

    private void showListView() {
        staggeredGridLayoutManager.setSpanCount(1);
        MenuItem item = menu.findItem(R.id.action_toggle);
        item.setIcon(R.drawable.list_black_24dp);
        item.setTitle(getString(R.string.show_as_grid));
        isListView = true;
    }

    private void showGridView() {
        staggeredGridLayoutManager.setSpanCount(2);
        MenuItem item = menu.findItem(R.id.action_toggle);
        item.setIcon(R.drawable.grid_on_black_24dp);
        item.setTitle(getString(R.string.show_as_list));
        isListView = false;
    }


}