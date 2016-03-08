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

import java.text.SimpleDateFormat;
import java.util.Date;

import no.itera.app.makeadifference.R;
import no.itera.app.makeadifference.activities.NavigationDrawerActivity;
import no.itera.app.makeadifference.activities.events.courselist.CourseListActivity;
import no.itera.app.makeadifference.models.Course;
import no.itera.app.makeadifference.storage.couchbase.CourseDatabaseManager;
import no.itera.app.makeadifference.ui.DatePickerFragment;

public class CreateCourseActivity extends NavigationDrawerActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private TextView titleView;
  private TextView descriptionView;
  private TextView startTimeView;
  private TextView endTimeView;

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
    startTimeView = (TextView) findViewById(R.id.edit_course_start_time);
    addDatePickerDialog(startTimeView, "start-date-picker", R.id.edit_course_start_time);
    endTimeView = (TextView) findViewById(R.id.edit_course_end_time);
    addDatePickerDialog(endTimeView, "end-date-picker", R.id.edit_course_end_time);

    Button submitButton = (Button) findViewById(R.id.create_course_submit_button);
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (saveCourse()) Toast.makeText(view.getContext(), "Saved course!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), CourseListActivity.class);
        startActivity(intent);
      }
    });
  }

  private boolean saveCourse() {
    try {
      if (formIsValid()) {
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTimeView.getText().toString());
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTimeView.getText().toString());

        Course course = new Course(title, description, startDate, endDate);
        CourseDatabaseManager dbManager = CourseDatabaseManager.getInstance();
        dbManager.create(course);
        return true;
      }
    } catch (Exception e) {
      Log.e(CreateCourseActivity.class.toString(), "Failed creating new course.", e);
    }
    return false;
  }

  private void addDatePickerDialog(View view, final String tag, final int outputViewId) {
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
          DatePickerFragment datePickerFragment = new DatePickerFragment();
          datePickerFragment.setOutputTextView(outputViewId);
          datePickerFragment.show(getFragmentManager(), tag);
        }
      }
    });
  }

  private boolean formIsValid() {
    return validateRequiredField(titleView, "Du må gi arrangementet en tittel") &&
        validateRequiredField(descriptionView, "Du må legge inn en beskrivelse av arrangementet") &&
        validateRequiredField(startTimeView, "Du må sette når arrangementet starter") &&
        validateRequiredField(endTimeView, "Du må sette når arrangementet slutter");
  }

  private boolean validateRequiredField(TextView view, String errorMessage) {
    if (view == null || view.getText() == null || view.getText().length() == 0) {
      Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_SHORT).show();
      return false;
    }
    return true;
  }
}
