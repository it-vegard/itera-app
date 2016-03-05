package no.itera.app.makeadifference;

import android.app.Application;
import android.util.Log;

import no.itera.app.makeadifference.storage.couchbase.CourseDatabaseManager;

public class IteraApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    initializeSingletons();
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    CourseDatabaseManager dbManager = CourseDatabaseManager.getInstance();
    dbManager.disconnect();
    Log.i(IteraApplication.class.toString(), "Disconnected from database. Shutting down application...");
  }

  private void initializeSingletons() {
    CourseDatabaseManager.initInstance(this, "courses");
  }
}
