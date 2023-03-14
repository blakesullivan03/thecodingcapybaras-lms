import java.util.ArrayList;

public class Comment {
    private String comment;
    private User author;
    private ArrayList<Comment> replies;

    public Comment(User author, String comment, ArrayList<Comment> replies){
        this.author = author;
        this.comment = comment;
        this.replies = replies;
    }

    public void edit(String edited){
        this.comment = edited;
    } 

    public String toString(){
        
        return "|" + author + ": " + comment;
    }

   /**  public void reply(User author, String comment){
        replies.add(new Comment(comment, author));
    }*/
      
}
