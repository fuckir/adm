package ru.yandex.autotests.qa.qe;

import org.jetbrains.annotations.NotNull;

import ru.yandex.autotests.qa.qe.domain.Grab;
import ru.yandex.autotests.qa.qe.domain.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Adm {

  public static void main(String... args) throws UnsupportedEncodingException, MessagingException, EmailException {

    Map<String, String> users = getUsers();

    List<String> logins = new ArrayList<>(users.keySet());

    Collections.shuffle(logins);

    printResultList(logins, users);
  }

  private static void sendEmail(String content, String address) throws EmailException {
    Email email = new SimpleEmail();
    email.setHostName("smtp.yandex-team.ru");
    email.setSmtpPort(25);
    email.setAuthenticator(new DefaultAuthenticator("dmitrys", "password"));
    email.setSSLOnConnect(true);
    email.setCharset("UTF-8");

    email.setFrom("dmitrys@yandex-team.ru");
    email.setSubject("Клуб АДМ. Ваша жертва.");
    email.setMsg(content);
    email.addTo(address);
    email.send();
  }

  @NotNull
  private static Map<String, String> getUsers() {
    Map<String, String> users = new HashMap<>();
    users.put("appolitta", "что-то душевное и памятное. Книгу/милую игрушку");
    users.put("lizvad", "");
    users.put("temino", "точно несъедобное :) что-то космическое..");
    users.put("sandrin", "");
    users.put("nastyaz", "буду рада любому подарку, если как-то поможет - люблю рисовать :)");
    users.put("selivanova-ns", "");
    users.put("antilles", "");
    users.put("katyaas", "");
    users.put("alina24", "что-то милое :)");
    users.put("ngavrilova", "");
    return users;
  }

  private static void printResultList(List<String> logins, Map<String, String> users) throws EmailException {
    List<User> userList = getUserList(logins);

    User firstUser = null;
    User oldUser = null;
    User newUser;
    String content;
    for (User user : userList) {
      newUser = user;
      if (oldUser == null) {
        firstUser = newUser;
      } else {
        content = getString(users, newUser, oldUser);
        sendEmail(content, oldUser.getEmail());
      }
      oldUser = newUser;
    }
    content = getString(users, firstUser, oldUser);
    sendEmail(content, oldUser.getEmail());
  }

  private static String getString(Map<String, String> users, User newUser, User oldUser) {
    String wishList = users.get(newUser.getLogin());
    String wishPart = !wishList.isEmpty() ? "В вишлисте написано: %s\n\n" : "";

    String[] testMessage = {oldUser.getFirstName(), oldUser.getLastName(), oldUser.getEmail(), newUser.getFirstName(),
        newUser.getLastName(), newUser.getLogin(), wishList
    };
    System.out.println(String.join(" ", testMessage));

    return String.format("Здравствуй, %s. \n"
                         + " Мы тут всё хорошенько перетасовали и так случилось, что в этом году твоей жертвой будет: %s %s "
                         + "(https://staff.yandex-team.ru/%s).\n\n"
                         + wishPart
                         + "-- \n"
                         + "Дмитрий\n"
                         + "Департамент Скуки и Развлечений / Отдел АДМ",
        oldUser.getFirstName(), newUser.getFirstName(), newUser.getLastName(), newUser.getLogin(), wishList);
  }

  @NotNull
  private static List<User> getUserList(List<String> logins) {
    List<User> userList = new ArrayList<>();

    //grab
    Grab grab = new Grab();
    for (String login : logins) {
      User userData = null;
      try {
        userData = grab.getUserData(login);
      } catch (IOException e) {
        e.printStackTrace();
      }
//      if (userData != null && userData.get("location").contains("St. Petersburg")) { // TODO: надомники не имеют офиса
      userList.add(userData);
//      }
    }
    return userList;
  }
}
