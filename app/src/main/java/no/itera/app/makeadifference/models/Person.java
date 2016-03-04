package no.itera.app.makeadifference.models;

/**
 * Created by Eier on 03.03.2016.
 */
public class Person {

  private String id;
  private String firstName;
  private String lastName;
  private String email;

  public Person(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }
}
