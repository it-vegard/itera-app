package no.itera.app.makeadifference.activities.events.courselist;

import android.content.Intent;
import android.os.Bundle;

import no.itera.app.makeadifference.R;
import no.itera.app.makeadifference.activities.NavigationDrawerActivity;
import no.itera.app.makeadifference.activities.events.course.CourseActivity;
import no.itera.app.makeadifference.models.Course;

public class CourseListActivity extends NavigationDrawerActivity
    implements CourseListFragment.OnListFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDrawerActivity(R.layout.activity_courses);
  }

  @Override
  public void onListFragmentInteraction(Course course) {
    Intent intent = new Intent(this, CourseActivity.class);
    intent.putExtra("courseId", course.getId());
    startActivity(intent);
  }
}
