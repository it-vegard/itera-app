package no.itera.app.makeadifference.activities.events.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import no.itera.app.makeadifference.R;
import no.itera.app.makeadifference.activities.NavigationDrawerActivity;
import no.itera.app.makeadifference.activities.events.courselist.CourseListActivity;

public class CreateCourseActivity extends NavigationDrawerActivity
    implements NavigationView.OnNavigationItemSelectedListener {

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

    Button submitButton = (Button) findViewById(R.id.create_course_submit_button);
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO: Save to db
        Toast.makeText(view.getContext(), "Should now save activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), CourseListActivity.class);
        // intent.putExtra("NEW-COURSE-ID", "ID-FROM-DB");
        startActivity(intent);
      }
    });
  }
}
