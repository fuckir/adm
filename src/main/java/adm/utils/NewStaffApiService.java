package adm.utils;

import javax.ws.rs.*;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
@Path("/1c82_lk/hs/staff/v1/persons")
public interface NewStaffApiService {

    @GET
    @Path("/{login}")
    String getUser(@PathParam("login") String login,
                   @QueryParam("isPreview") boolean isPreview);
}
