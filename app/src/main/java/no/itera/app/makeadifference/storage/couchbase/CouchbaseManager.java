package no.itera.app.makeadifference.storage.couchbase;

import android.content.Context;

import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;

import java.util.Map;

import no.itera.app.makeadifference.models.JsonObject;
import no.itera.app.makeadifference.storage.DatabaseManager;

public abstract class CouchbaseManager<T> implements DatabaseManager<T> {

  protected Manager manager;
  protected Database database;
  protected Context context;
  protected String databaseName;

  @Override
  public boolean connect() {
    try {
      manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS);
      database = manager.getDatabase(databaseName.toLowerCase());
      Log.i(CouchbaseManager.class.toString(), "Connected to '" + databaseName + "'.");
      return true;
    } catch (Exception e) {
      Log.e(CouchbaseManager.class.toString(), "Could not connect to '" + databaseName + "'.", e);
      if (database != null) database.close();
      if (manager != null) manager.close();
      return false;
    }
  }

  @Override
  public boolean disconnect() {
    try {
      if (database != null && database.isOpen()) database.close();
      if (manager != null && database.isOpen()) manager.close();
      return true;
    } catch (Exception e) {
      Log.e(CouchbaseManager.class.toString(), "Caught error when trying to close database connection.", e);
      return false;
    }
  }
}
