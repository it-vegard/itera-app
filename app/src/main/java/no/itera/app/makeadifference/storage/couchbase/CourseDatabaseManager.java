package no.itera.app.makeadifference.storage.couchbase;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import no.itera.app.makeadifference.models.Course;
import no.itera.app.makeadifference.models.JsonObject;

public class CourseDatabaseManager extends CouchbaseManager<Course> {

  private static CourseDatabaseManager instance;

  private CourseDatabaseManager(Context context, String databaseName) {
    this.context = context;
    this.databaseName = databaseName;
  }

  public static void initInstance(Context context, String databaseName) {
    if (instance == null) {
      instance = new CourseDatabaseManager(context, databaseName);
    }
  }

  public static CourseDatabaseManager getInstance() {
    return instance;
  }

  @Override
  public boolean create(Course course) {
    try {
      if (database == null || !database.isOpen()) {
        connect();
      }
      Document document = database.createDocument();
      document.putProperties(course.toJson());
      return true;
    } catch (NullPointerException e) {
      Log.e(CourseDatabaseManager.class.toString(), "Error occurred. Is course object null? " + (course == null ? "Yes" : "No"), e);
    } catch (CouchbaseLiteException e) {
      Log.e(CourseDatabaseManager.class.toString(), "Could not create document for course '" + course.getId() + "'.", e);
    } catch (Exception e) {
      Log.e(CourseDatabaseManager.class.toString(), "Unexpected error occurred!", e);
    }
    return false;
  }

  @Override
  public Course get(String id) {
    Document document = database.getExistingDocument(id);
    if (document == null) return null;
    Course course = new Course(document);
    return course;
  }

  @Override
  public List<Course> getAll() {
    List<Course> courses = new ArrayList<>();
    try {
      if (database == null || !database.isOpen()) connect();
      Query query = database.createAllDocumentsQuery();
      QueryEnumerator result = query.run();

      for (Iterator<QueryRow> it = result; it.hasNext();) {
        QueryRow row = it.next();
        Document document = row.getDocument();
        Course course = new Course(document);
        courses.add(course);
      }
    } catch (CouchbaseLiteException e) {
      Log.e(CourseDatabaseManager.class.toString(), "Failed retrieving all courses.", e);
    }
    return courses;
  }

  @Override
  public boolean update(String id, Course course) {
    Document document = database.getDocument(id);
    Map<String, Object> newProperties = course.toJson();
    try {
      document.putProperties(newProperties);
      return true;
    } catch (CouchbaseLiteException e) {
      Log.e(CourseDatabaseManager.class.toString(), "Could not update document '" + document.getId() + "'.", e);
      return false;
    }
  }

  @Override
  public boolean delete(String id) {
    try {
      Document document = database.getExistingDocument(id);
      document.delete();
      return true;
    } catch (CouchbaseLiteException e) {
      Log.e(CourseDatabaseManager.class.toString(), "Could not delete course '" + id + "'.", e);
      return false;
    }
  }
}
