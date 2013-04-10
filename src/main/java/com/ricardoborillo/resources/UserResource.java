package com.ricardoborillo.resources;

import com.ricardoborillo.model.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    @GET
    @Path("/{userId}")
    public User getUserById(@PathParam("userId") String userId)
    {
        User user = new User();
        user.setId(Long.parseLong(userId));
        user.setName("Ricardo Borillo");

        return user;
    }
}