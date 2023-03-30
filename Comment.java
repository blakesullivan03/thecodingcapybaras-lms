import java.util.ArrayList;

/**
 * 
 */
public class Comment {
    private String comment;
    private User author;
    private ArrayList<Comment> replies;

    public Comment(User author, String comment) {
        this.author = author;
        this.comment = comment;
        this.replies = new ArrayList<Comment>();
    }

    public Comment(User author, String comment, ArrayList<Comment> replies){
        this.author = author;
        this.comment = comment;
        this.replies = replies;
    }

    public String getText() {
        return this.comment;
    }

    public User getAuthor() {
        return this.author;
    }
    
    public void edit(String edited){
        this.comment = edited;
    } 

    public String toString() {
        String com = "|" + this.author + ": " + this.comment;
        if(replies.isEmpty())
            return com;
        return com + "\n     " + replies.toString();

    }

   public void reply(User author, String comment) {
        replies.add(new Comment(author, comment));

    }
      
}
