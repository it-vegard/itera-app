package no.itera.app.makeadifference.models;

import java.util.Map;

public interface JsonObject<T> {

  public Map<String, Object> toJson();

}