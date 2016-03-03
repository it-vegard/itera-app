package no.itera.app.makeadifference.activities.events.courselist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample title for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CourseList {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<Course> ITEMS = new ArrayList<Course>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, Course> ITEM_MAP = new HashMap<String, Course>();

  static {
    addItem(new Course("1", "Fagkveld: Redux", "Vi går gjennom egenskapene ved Redux, og hvordan det spiller inn i React-rammeverket."));
    addItem(new Course("2", "Itera Academy: Konsulentkurs", "Lær å bli en god konsulent"));
    addItem(new Course("3", "Fagdag 2: Itera-konferansen", "Lyntaler på lyntaler!"));
  }

  private static void addItem(Course item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.id, item);
  }

  private static Course createDummyItem(int position) {
    return new Course(String.valueOf(position), "Course " + position, makeDetails(position));
  }

  private static String makeDetails(int position) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about Item: ").append(position);
    for (int i = 0; i < position; i++) {
      builder.append("\nMore details information here.");
    }
    return builder.toString();
  }

  /**
   * A dummy item representing a piece of title.
   */
  public static class Course {
    public final String id;
    public final String title;
    public final String details;

    public Course(String id, String title, String details) {
      this.id = id;
      this.title = title;
      this.details = details;
    }

    @Override
    public String toString() {
      return title;
    }
  }
}
