package adm.domain;

import java.io.IOException;


import adm.domain.user.NewRawUser;
import adm.utils.NewStaffApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;

import static adm.domain.Properties.PASSWORD;
import static adm.domain.Properties.USER_NAME;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
public class NewStaffGrab {

    public NewRawUser parseRowUser(String login) {
        NewStaffApiService service = JAXRSClientFactory.create("https://staff.yooteam.ru", NewStaffApiService.class);

        HTTPConduit conduit = (HTTPConduit) WebClient.getConfig(service).getConduit();
        conduit.getAuthorization().setAuthorizationType("NTLM");
        conduit.getAuthorization().setUserName(USER_NAME);
        conduit.getAuthorization().setPassword(PASSWORD);

        String data = service.getUser(
            login,
            true
        );

        ObjectMapper mapper = new ObjectMapper();
        NewRawUser rawUser = null;

        try {
            rawUser = mapper.readValue(data, NewRawUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawUser;
    }
}
