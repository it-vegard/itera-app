package no.itera.app.makeadifference.models;

import com.couchbase.lite.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements JsonObject {
  private final String id;
  private final String title;
  private final String details;
  private final Date startDate;
  private final Date endDate;
  private final List<String> tags;
  private final Map<String, Person> attendees;

  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String DETAILS = "details";
  private static final String START_DATE = "startDate";
  private static final String END_DATE = "endDate";
  private static final String TAGS = "tags";
  private static final String ATTENDEES = "attendees";

  public Course(String title, String details) {
    this(title, details, new Date(), new Date());
  }

  public Course(String title, String details, Date startDate, Date endDate) {
    this(title, details, startDate, endDate, new ArrayList<String>(), new HashMap<String, Person>(), null);
  }

  public Course(String title, String details, Date startDate, Date endDate, List<String> tags) {
    this(title, details, startDate, endDate, tags, new HashMap<String, Person>(), null);
  }

  public Course(String title, String details, Date startDate, Date endDate, List<String> tags, Map<String, Person> attendees, String id) {
    this.id = id;
    this.title = title;
    this.details = details;
    this.startDate = startDate;
    this.endDate = endDate;
    this.tags = tags;
    this.attendees = attendees;
  }
  public Course(Document document) {
    Map<String, Object> jsonObject = document.getProperties();
    this.id = document.getId();
    this.title = (String) jsonObject.get(TITLE);
    this.details = (String) jsonObject.get(DETAILS);
    this.startDate = new Date((long)jsonObject.get(START_DATE));
    this.endDate = new Date((long)jsonObject.get(END_DATE));
    this.tags = (List<String>) jsonObject.get(TAGS);
    this.attendees = (Map<String, Person>) jsonObject.get(ATTENDEES);
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

  @Override
  public Map<String, Object> toJson() {
    Map<String, Object> jsonObject = new HashMap<>();
    jsonObject.put(ID, id);
    jsonObject.put(TITLE, title);
    jsonObject.put(DETAILS, details);
    jsonObject.put(START_DATE, startDate);
    jsonObject.put(END_DATE, endDate);
    jsonObject.put(TAGS, tags);
    jsonObject.put(ATTENDEES, attendees);
    return jsonObject;
  }
}
