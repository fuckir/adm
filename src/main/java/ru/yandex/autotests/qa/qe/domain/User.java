package ru.yandex.autotests.qa.qe.domain;

/**
 * @author dmitrys
 * @since 06.12.17
 */
public class User {
  private String login;
  private String firstName;
  private String lastName;
  private String email;
  private String location;

  User(String login, String firstName, String lastName, String email, String location) {
    this.login = login;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.location = location;
  }

  public String getLogin() {
    return login;
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

  public String getLocation() {
    return location;
  }
}
