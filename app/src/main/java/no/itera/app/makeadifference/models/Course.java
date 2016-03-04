package no.itera.app.makeadifference.models;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eier on 03.03.2016.
 */
public class Course {
  private final String id;
  private final String title;
  private final String details;
  private final Date startDate;
  private final Date endDate;
  private final Map<String, Person> attendees;

  public Course(String id, String title, String details, Date startDate, Date endDate) {
    this.id = id;
    this.title = title;
    this.details = details;
    this.startDate = startDate;
    this.endDate = endDate;
    attendees = new HashMap<>();
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDetails() {
    return details;
  }

  public Date getStartDate() { return startDate; }

  public Date getEndDate() { return endDate; }

  public Map<String, Person> getAttendees() {
    return attendees;
  }

  public void signUp(Person person) {
    attendees.put(person.getId(), person);
  }

  public void withDraw(Person person) {
    attendees.remove(person);
  }

  @Override
  public String toString() {
    return title;
  }

  public static class CourseDateComparator implements Comparator<Course> {
    @Override
    public int compare(Course lhs, Course rhs) {
      return lhs.getStartDate().compareTo(rhs.getEndDate());
    }
  }
}
