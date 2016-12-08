package ru.yandex.autotests.qa.qe;

import ru.yandex.autotests.qa.qe.domain.Properties;

import java.io.IOException;
import java.util.*;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Adm {
  public static void main(String... args) {

    List<String> users = Arrays.asList(
        "melikhov",
        "qwizzy",
        "jsus",
        "yurakura",
        "nsobyanin",
        "karpycheva",
        "sekolosova",
        "mturina",
        "kuchumova",
        "karama",
        "chertkova",
        "boronchiev",
        "lozenko",
        "motorin",
        "alina24",
        "adekvaten",
        "ollven",
        "librarian",
        "greygrey",
        "kolevatova",
        "perovskaya",
        "amlisov",
        "andrey-nautilus",
        "grand",
        "whistler",
        "a10zn8",
        "dolf",
        "a-kononova"
    );
    Collections.shuffle(users);
    printResultList(users);
  }

  private static void printResultList(List<String> users) {
    Map<String, String[]> result = new LinkedHashMap<>();

    //grab
    Properties.Grab grab = new Properties.Grab();
    for (String user : users) {
      Map<String, String> userData = null;
      try {
        userData = grab.getData(user);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (userData != null && userData.get("location").contains("St. Petersburg")) {
        result.put(user, new String[]{userData.get("firstName"), userData.get("lastName"), userData.get("email"), userData.get("login")
        });
      }
    }

    String[] firstUser = new String[4];
    String[] oldUser = new String[4];
    String[] newUser;
    for (Map.Entry user : result.entrySet()) {
      newUser = (String[]) user.getValue();
      if (oldUser[0] == null) {
        firstUser = newUser;
      } else {
        System.out.println(oldUser[0] + " " + oldUser[1] + " " + oldUser[2] + " " + newUser[0] + " " + newUser[1] + " " + newUser[3]);
      }
      oldUser = newUser;
    }
    System.out.println(
        oldUser[0] + " " + oldUser[1] + " " + oldUser[2] + " " + firstUser[0] + " " + firstUser[1] + " " + firstUser[3]);
  }
}
