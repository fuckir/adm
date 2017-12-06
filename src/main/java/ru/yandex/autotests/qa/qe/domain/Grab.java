package ru.yandex.autotests.qa.qe.domain;

import ru.yandex.autotests.qa.utils.http.HttpClientBuilder;
import ru.yandex.autotests.qa.utils.json.JsonUtils;
import ru.yandex.autotests.qa.utils.json.RecursiveJsonVisitor;

import java.io.IOException;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Grab {
  private String firstName = "";
  private String lastName = "";
  private String email = "";
  private String location = "";

  public User getUserData(String login) throws IOException {

    String url = "https://api.staff.yandex-team.ru/v3/persons?_pretty=1&login=" + login
                 + "&_fields=login%2Cname.first.ru%2Cname.last.ru%2Cwork_email%2Clocation.office.city.name.en";

    try (CloseableHttpClient httpClient = new HttpClientBuilder().build()) {
      HttpGet request = new HttpGet(url);
      request.addHeader("Authorization", "OAuth " + Properties.O_AUTH);
      try (CloseableHttpResponse response = httpClient.execute(request)) {
        JsonElement parsed = JsonUtils.getResponse2Json(response);

        JsonUtils.visit(parsed, new RecursiveJsonVisitor() {
          @Override
          protected void doVisitObject(JsonObject object) {

            if (object.get("work_email") != null) {
              JsonPrimitive workEmail = object.getAsJsonPrimitive("work_email");
              email = workEmail.getAsString();
            }

            if (object.get("last") != null) {
              JsonPrimitive last = object.getAsJsonObject("last").getAsJsonPrimitive("ru");
              String fam = last.getAsString();
              if (fam.contains(" ")) {
                fam = fam.substring(0, fam.lastIndexOf(" "));
              }
              lastName = fam;
            }

            if (object.get("first") != null) {
              JsonPrimitive first = object.getAsJsonObject("first").getAsJsonPrimitive("ru");
              firstName = first.getAsString().trim();
            }

            if (object.get("location") != null) {
              JsonPrimitive grabbedLocation = object
                  .getAsJsonObject("location")
                  .getAsJsonObject("office")
                  .getAsJsonObject("city")
                  .getAsJsonObject("name")
                  .getAsJsonPrimitive("en");
              location = grabbedLocation.getAsString();
            }
          }
        });

      }
      return new User(login, firstName, lastName, email, location);
    }
  }

}
