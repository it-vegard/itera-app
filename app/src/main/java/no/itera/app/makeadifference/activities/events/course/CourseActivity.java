package no.itera.app.makeadifference.activities.events.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import no.itera.app.makeadifference.R;
import no.itera.app.makeadifference.activities.NavigationDrawerActivity;
import no.itera.app.makeadifference.activities.events.courselist.CourseListActivity;
import no.itera.app.makeadifference.models.Course;
import no.itera.app.makeadifference.storage.couchbase.CourseDatabaseManager;

public class CourseActivity extends NavigationDrawerActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDrawerActivity(R.layout.activity_course);

    initCourse();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
  }

  private void initCourse() {
    Intent instigatingIntent = getIntent();
    final String courseId = instigatingIntent.getExtras().get("courseId").toString();
    final Course course = loadCourse(courseId);
    if (course != null) {
      TextView titleView = (TextView) findViewById(R.id.course_title);
      titleView.setText(course.getTitle());
      TextView descriptionView = (TextView) findViewById(R.id.course_description);
      descriptionView.setText(course.getDetails());
    } else {
      Toast.makeText(this, "Couldn't find course with id '" + courseId + "'.", Toast.LENGTH_SHORT).show();
    }
    Button deleteButton = (Button) findViewById(R.id.delete_course_button);
    deleteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (deleteCourse(courseId)) {
          Toast.makeText(view.getContext(), "Deleted course '" + course.getTitle() + "'!", Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(view.getContext(), CourseListActivity.class);
          startActivity(intent);
        } else {
          Toast.makeText(view.getContext(), "Could not delete course '" + courseId + "'!", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  public boolean deleteCourse(String courseId) {
    CourseDatabaseManager dbManager = CourseDatabaseManager.getInstance();
    return dbManager.delete(courseId);
  }

  private Course loadCourse(String courseId) {
    CourseDatabaseManager dbManager = CourseDatabaseManager.getInstance();
    return dbManager.get(courseId);
  }
}
