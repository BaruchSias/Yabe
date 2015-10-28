package models;

/**
 *
 * @author Baruch
 */
import java.util.*;
import java.util.Date;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;


@Entity
public class User extends Model {

    public String toString() {
        return email;
    }

    @Email
    @Required
    public String email;
    
    @Required
    public String password;
    
    public String fullname;
    public boolean isAdmin;

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

    public class Post extends Model {

        public String title;
        public Date postedAt;

        @Lob
        public String content;

        @ManyToOne
        public User author;
        public Object comments;

        public Post(User author, String title, String content) {
            this.author = author;
            this.title = title;
            this.content = content;
            this.postedAt = new Date();
        }

    }

}
