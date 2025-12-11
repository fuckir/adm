package adm.domain;

import adm.domain.user.NewRawUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static adm.domain.Properties.PASSWORD;
import static adm.domain.Properties.USER_NAME;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
public class NewStaffGrab {

    public NewRawUser parseRowUser(String login) {
        String url = "https://staff.yooteam.ru/1c82_lk/hs/staff/v1/persons/" + login + "?isPreview=true";

        try {
            String response = null;
            CredentialsProvider credsProvider = new BasicCredentialsProvider();

            NTCredentials ntCredentials = new NTCredentials(
                    USER_NAME,
                    PASSWORD,
                    null,
                    null
            );

            credsProvider.setCredentials(AuthScope.ANY, ntCredentials);

            try (CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCredentialsProvider(credsProvider)
                    .build()) {
                HttpClientContext context = HttpClientContext.create();
                HttpGet httpget = new HttpGet(url);

                try (CloseableHttpResponse response1 = httpclient.execute(httpget, context)) {
                    HttpEntity entity = response1.getEntity();
                    if (entity != null) {
                        String responseString = EntityUtils.toString(entity);
                        EntityUtils.consume(entity);
                        response = responseString;
                    }
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, NewRawUser.class);
        } catch (Exception e) {
            throw new IllegalStateException("Error for user: " + login);
        }
    }
}
