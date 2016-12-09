package ru.yandex.autotests.qa.qe.domain;

import ru.yandex.autotests.qa.utils.http.HttpClientBuilder;
import ru.yandex.autotests.qa.utils.json.JsonUtils;
import ru.yandex.autotests.qa.utils.json.RecursiveJsonVisitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
public class Properties {
  private static final String O_AUTH = "cfca19685c3d4e56940716c12c91e8a3"; //https://oauth.yandex-team.ru/

  /**
   * Created by dmitrys
   * 01.12.16.
   */
  public static class Grab {

    public Map<String, String> getData(String login) throws IOException {
      final Map<String, String> data = new HashMap<>();
      data.put("login", login);

      final String url = "https://api.staff.yandex-team.ru/v3/persons?_pretty=1&login=" + login
                         + "&_fields=login%2Cname.first.ru%2Cname.last.ru%2Cwork_email%2Clocation.office.city.name.en";

      try (CloseableHttpClient httpClient = new HttpClientBuilder().build()) {
        final HttpGet request = new HttpGet(url);
        request.addHeader("Authorization", "OAuth " + O_AUTH);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
          final JsonElement parsed = JsonUtils.getResponse2Json(response);

          JsonUtils.visit(parsed, new RecursiveJsonVisitor() {
            @Override
            protected void doVisitObject(JsonObject object) {


              if (object.get("work_email") != null) {
                final JsonPrimitive email = object.getAsJsonPrimitive("work_email");
                data.put("email", email.getAsString());
              }

              if (object.get("last") != null) {
                final JsonPrimitive lastName = object.getAsJsonObject("last").getAsJsonPrimitive("ru");
                String fam = lastName.getAsString();
                if (fam.contains(" ")) {
                  fam = fam.substring(0, fam.lastIndexOf(" "));
                }
                data.put("lastName", fam);
              }

              if (object.get("first") != null) {
                final JsonPrimitive firstName = object.getAsJsonObject("first").getAsJsonPrimitive("ru");
                data.put("firstName", firstName.getAsString().trim());
              }

              if (object.get("location") != null) {
                final JsonPrimitive location = object
                    .getAsJsonObject("location")
                    .getAsJsonObject("office")
                    .getAsJsonObject("city")
                    .getAsJsonObject("name")
                    .getAsJsonPrimitive("en");
                data.put("location", location.getAsString());
              }
            }
          });

        }
        return data;
      }
    }

  }
}
