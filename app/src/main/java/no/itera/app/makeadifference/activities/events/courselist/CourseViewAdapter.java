package no.itera.app.makeadifference.activities.events.courselist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import no.itera.app.makeadifference.activities.events.courselist.CourseListFragment.OnListFragmentInteractionListener;
import no.itera.app.makeadifference.R;
import no.itera.app.makeadifference.models.Course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Course} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

  private final List<Course> mValues;
  private final OnListFragmentInteractionListener mListener;

  public CourseViewAdapter(List<Course> items, OnListFragmentInteractionListener listener) {
    mValues = items;
    mListener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_course, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.course = mValues.get(position);
    holder.courseTitleView.setText(mValues.get(position).getTitle());
    holder.courseTitleDescription.setText(mValues.get(position).getDetails());
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MMMM HH:mm");
    holder.startDateView.setText(dateFormat.format(mValues.get(position).getStartDate()));
    holder.endDateView.setText(dateFormat.format(mValues.get(position).getEndDate()));

    holder.courseView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          // Notify the active callbacks interface (the activity, if the
          // fragment is attached to one) that an item has been selected.
          mListener.onListFragmentInteraction(holder.course);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public final View courseView;
    public final TextView courseTitleView;
    public final TextView courseTitleDescription;
    public final TextView startDateView;
    public final TextView endDateView;
    public Course course;

    public ViewHolder(View view) {
      super(view);
      courseView = view;
      courseTitleView = (TextView) view.findViewById(R.id.course_list_course_title);
      courseTitleDescription = (TextView) view.findViewById(R.id.course_list_course_description);
      startDateView = (TextView) view.findViewById(R.id.course_list_course_start_date);
      endDateView = (TextView) view.findViewById(R.id.course_list_course_end_date);
    }

    @Override
    public String toString() {
      return super.toString() + " '" + courseTitleDescription.getText() + "'";
    }
  }
}
