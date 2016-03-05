package no.itera.app.makeadifference.storage;

import java.util.List;

public interface DatabaseManager<T> {

  public boolean connect();

  public boolean disconnect();

  public boolean create(T object);

  public T get(String id);

  public List<T> getAll();

  public boolean delete(String id);

  public boolean update(String id, T object);

}
