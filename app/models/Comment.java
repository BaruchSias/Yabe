package models;

/**
 *
 * @author Baruch
 */
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
 
import play.db.jpa.*;
import play.test.Fixtures;
 
@Entity
public class Comment extends Model {
 
    public String author;
    public Date postedAt;
     
    @Lob
    public String content;
    
    @ManyToOne
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
    @Test
public void fullTest() {
    Fixtures.loadModels("data.yml");
 
    // Count things
    assertEquals(2, User.count());
    assertEquals(3, Post.count());
    assertEquals(3, Comment.count());
 
    // Try to connect as users
    assertNotNull(User.connect("bob@gmail.com", "secret"));
    assertNotNull(User.connect("jeff@gmail.com", "secret"));
    assertNull(User.connect("jeff@gmail.com", "badpassword"));
    assertNull(User.connect("tom@gmail.com", "secret"));
 
    // Find all of Bob's posts
    List<Post> bobPosts = Post.find("author.email", "bob@gmail.com").fetch();
    assertEquals(2, bobPosts.size());
 
    // Find all comments related to Bob's posts
    List<Comment> bobComments = Comment.find("post.author.email", "bob@gmail.com").fetch();
    assertEquals(3, bobComments.size());
 
    // Find the most recent post
    Post frontPost = Post.find("order by postedAt desc").first();
    assertNotNull(frontPost);
    assertEquals("About the model layer", frontPost.title);
 
    // Check that this post has two comments
    assertEquals(2, frontPost.comments.size());
 
    // Post a new comment
    frontPost.addComment("Jim", "Hello guys");
    assertEquals(3, frontPost.comments.size());
    assertEquals(4, Comment.count());
}
 
}
