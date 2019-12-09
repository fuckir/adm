package adm.domain;

import java.io.IOException;
import java.io.StringReader;


import adm.utils.StaffApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * Created by dmitrys
 * 01.12.16.
 */
public class Grab {

    public RawUser parseRowUser(String login) {
        StaffApiService service = JAXRSClientFactory.create("https://api.staff.yandex-team.ru", StaffApiService.class);

        HTTPConduit conduit = (HTTPConduit) WebClient.getConfig(service).getConduit();
        HTTPClientPolicy policy = conduit.getClient();
        policy.setProxyServer("proxy-iwsva.yamoney.ru");
        policy.setProxyServerPort(8080);

        String data = service.getUser(
            "1",
            "1",
            login,
            "login,work_email,name.first.ru,name.last.ru,location.office.city.name.en",
            "OAuth " + Properties.O_AUTH,
            "application/json;charset=utf-8");

        ObjectMapper mapper = new ObjectMapper();
        RawUser rawUser = null;

        try {
            rawUser = mapper.readValue(data, RawUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawUser;
    }
}
