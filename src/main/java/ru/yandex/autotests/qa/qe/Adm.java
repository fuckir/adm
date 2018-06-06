package ru.yandex.autotests.qa.qe;

import ru.yandex.autotests.qa.qe.domain.Grab;
import ru.yandex.autotests.qa.qe.domain.User;

import java.io.IOException;
import java.util.*;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import static ru.yandex.autotests.qa.qe.domain.Properties.PASSWORD;
import static ru.yandex.autotests.qa.qe.domain.Properties.USER_NAME;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Adm {

  public static void main(String... args) throws EmailException {

    Map<String, String> users = getUsers();

    List<String> logins = new ArrayList<String>(users.keySet());

    Collections.shuffle(logins);

    collectMessagesAndSendEmail(logins, users);
  }

  private static void collectMessagesAndSendEmail(List<String> logins, Map<String, String> users) throws EmailException {
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
        content = getMessages(users, newUser, oldUser);
        sendEmail(content, oldUser.getEmail());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      oldUser = newUser;
    }
    content = getMessages(users, firstUser, oldUser);
    sendEmail(content, oldUser.getEmail());
  }

  private static String getMessages(Map<String, String> users, User newUser, User oldUser) {
    String wishList = users.get(newUser.getLogin());
    String wishPart = !wishList.isEmpty() ? "В вишлисте написано: %s\n\n" : "Вишлист пуст.\n\n";

    String[] testMessage = {oldUser.getFirstName(), oldUser.getLastName(), oldUser.getEmail(), newUser.getFirstName(),
        newUser.getLastName(), newUser.getLogin(), wishList
    };
    System.out.println(String.join(" ", testMessage));

    return String.format("Здравствуй, %s. \n"
                         + " Мы тут всё хорошенько перетасовали и так случилось, что в этом году твоей жертвой будет: %s %s "
                         + "(https://staff.yandex-team.ru/%s).\n\n"
                         + wishPart
                         + "-- \n"
                         + "Дима\n"
                         + "Департамент Скуки и Развлечений / Отдел АДМ",
        oldUser.getFirstName(), newUser.getFirstName(), newUser.getLastName(), newUser.getLogin(), wishList);
  }

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
//      if (userData != null && userData.getLocation().contains("St. Petersburg")) { // TODO: надомники не имеют офиса
      userList.add(userData);
//      }
    }
    return userList;
  }

  private static void sendEmail(String content, String address) throws EmailException {
    Email email = new SimpleEmail();
    email.setHostName("smtp.yandex-team.ru");
    email.setSmtpPort(25);
    email.setAuthenticator(new DefaultAuthenticator(USER_NAME, PASSWORD));
    email.setSSLOnConnect(true);
    email.setCharset("UTF-8");

    email.setFrom("dmitrys@yandex-team.ru");
    email.setSubject("Клуб АДМ. Ваша жертва.");
    email.setMsg(content);
    email.addTo(address);
    email.addCc("dmitrys@yandex-team.ru");
    email.send();
  }

  private static Map<String, String> getUsers() {
    Map<String, String> users = new HashMap<>();
    users.put("mruban", "");
    users.put("ovalyona", "");
    users.put("enilina", "");
    return users;
  }
}
