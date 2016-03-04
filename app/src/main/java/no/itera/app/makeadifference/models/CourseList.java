package no.itera.app.makeadifference.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eier on 03.03.2016.
 */
public class CourseList {

  private Map<String, Course> courses;

  public CourseList() {
    this.courses = new HashMap<>();
  }

  public void addCourse(Course course) {
    courses.put(course.getId(), course);
  }

  public void removeCourse(Course course) {
    courses.remove(course);
  }

  public List<Course> getCourses() {
    return (ArrayList<Course>) courses.values();
  }

  /*
   * Create dummy-data for development-purposes. Should be removed as soon as database is set up.
   */

  private static Map<String, Course> DUMMY_COURSES;

  static {
    DUMMY_COURSES = new HashMap<>();
    for (int i = 1; i <= 10; i++) {
      long currentTimeMillis = new Date().getTime();
      long timeIntoTheFuture = (int) Math.random() * 86400000;
      Date startDate = new Date();
      startDate.setTime(currentTimeMillis + timeIntoTheFuture);
      DUMMY_COURSES.put(Integer.toString(i), createDummyCourse(Integer.toString(i), startDate));
    }
  }

  private static Course createDummyCourse(String id, Date startDate) {
    Date endDate = startDate;
    endDate.setTime(startDate.getTime() + ((int) Math.random() * 7200000));
    return new Course(id, "Dummy course " + id, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean finibus posuere dui, eu faucibus neque fermentum eget. Nam eu ornare ante. Cras massa mi, semper ac egestas eu, rutrum et massa. Donec pellentesque, nibh eu tincidunt sollicitudin, ante neque malesuada mi, eu lobortis magna mauris eget ex.", startDate, endDate);
  }

  public static Map<String, Course> getDummyCourses() {
    return DUMMY_COURSES;
  }

}
