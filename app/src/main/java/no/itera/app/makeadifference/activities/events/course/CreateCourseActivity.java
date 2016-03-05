package no.itera.app.makeadifference.activities.events.course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import no.itera.app.makeadifference.R;
import no.itera.app.makeadifference.activities.NavigationDrawerActivity;
import no.itera.app.makeadifference.activities.events.courselist.CourseListActivity;
import no.itera.app.makeadifference.models.Course;
import no.itera.app.makeadifference.storage.couchbase.CourseDatabaseManager;

public class CreateCourseActivity extends NavigationDrawerActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private TextView titleView;
  private TextView descriptionView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_course);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    titleView = (TextView) findViewById(R.id.edit_course_title);
    descriptionView = (TextView) findViewById(R.id.edit_course_description);

    Button submitButton = (Button) findViewById(R.id.create_course_submit_button);
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (saveCourse()) Toast.makeText(view.getContext(), "Saved course!", Toast.LENGTH_SHORT).show();
        Toast.makeText(view.getContext(), "Should now save activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), CourseListActivity.class);
        // intent.putExtra("NEW-COURSE-ID", "ID-FROM-DB");
        startActivity(intent);
      }
    });
  }

  private boolean saveCourse() {
    try {
      String title = titleView.getText().toString();
      String description = descriptionView.getText().toString();
      Course course = new Course(title, description);
      CourseDatabaseManager dbManager = CourseDatabaseManager.getInstance();
      dbManager.create(course);
      return true;
    } catch (Exception e) {
      Log.e(CreateCourseActivity.class.toString(), "Failed creating new course.", e);
      return false;
    }
  }
}
