package controllers;

/**
 *
 * @author Baruch
 */
import play.*;
import play.data.validation.*;
import play.mvc.*;

@Check("admin")
@With(Secure.class)
public class Posts extends CRUD {

}
