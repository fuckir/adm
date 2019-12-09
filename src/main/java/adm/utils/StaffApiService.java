package adm.utils;

import adm.domain.RawUser;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author dmitrys
 * @since 2019-08-01
 */
@Path("/v3")
public interface StaffApiService {

  @GET
  @Path("/persons")
  String getUser(@QueryParam("_pretty") String pretty,
                  @QueryParam("_one") String one,
                  @QueryParam("login") String login,
                  @QueryParam("_fields") String fields,
                  @HeaderParam("Authorization") String authorization,
                  @HeaderParam("ContentType") String contentType);
}
