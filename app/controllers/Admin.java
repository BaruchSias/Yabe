package controllers;

/**
 *
 * @author Baruch
 */
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Admin extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

    public static void index() {
        render();
    }

    private static class Secure {

        public Secure() {
        }
    }

}
