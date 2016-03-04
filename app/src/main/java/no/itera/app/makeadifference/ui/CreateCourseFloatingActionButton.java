package no.itera.app.makeadifference.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import no.itera.app.makeadifference.activities.events.course.CreateCourseActivity;

/**
 * Created by Eier on 04.03.2016.
 */
public class CreateCourseFloatingActionButton extends FloatingActionButton {

  public CreateCourseFloatingActionButton(Context context) {
    this(context, null);
  }

  public CreateCourseFloatingActionButton(Context context, AttributeSet attributeSet) {
    this(context, attributeSet, 0);
  }

  public CreateCourseFloatingActionButton(Context context, AttributeSet attributeSet, int defStyleAttribute) {
    super(context, attributeSet, defStyleAttribute);
    addListeners();
  }

  private void addListeners() {
    this.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(view.getContext(), "Using custom FAB!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), CreateCourseActivity.class);
        view.getContext().startActivity(intent);
      }
    });
  }

}
