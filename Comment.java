import java.util.ArrayList;

/**
 * A class that represents a comment with an author, a text body, and replies.
 */
public class Comment {
    private String comment;
    private User author;
    private ArrayList<Comment> replies;
    /**
     * Constructs a new comment with the given author and text body.
     *
     * @param author The author of the comment
     * @param comment The text body of the comment
     */
    public Comment(User author, String comment) {
        this.author = author;
        this.comment = comment;
        this.replies = new ArrayList<Comment>();
    }
    /**
     * Constructs a new comment with the given author, text body, and replies.
     *
     * @param author The author of the comment
     * @param comment The text body of the comment
     * @param replies The replies to the comment
     */
    public Comment(User author, String comment, ArrayList<Comment> replies){
        this.author = author;
        this.comment = comment;
        this.replies = replies;
    }
    /**
     * Returns the text body of the comment.
     *
     * @return The text body of the comment
     */

    public String getText() {
        return comment;
    }
    /**
     * Returns the author of the comment.
     *
     * @return The author of the comment
     */

    public User getAuthor() {
        return author;
    }
    /**
     * Returns the replies to the comment.
     *
     * @return The replies to the comment
     */
    public ArrayList<Comment> getReplies(){
        return replies;
    }
    /**
     * Edits the text body of the comment.
     *
     * @param edited The new text body of the comment
     */
    public void edit(String edited){
        this.comment = edited;
    } 
    /**
     * Returns a string representation of the comment.
     *
     * @return A string representation of the comment
     */
    public String toString() {
        String com = "";
        com += author.getFirstName() + " " + author.getLastName() + " : " + comment;
        if(replies.isEmpty())
            return com;
        return com + "\n\t" + replies.toString();

    }
    /**
    * Adds a reply to the comment with the given author and text body.
    *
    * @param author The author of the reply
    * @param comment The text body of the reply
    */
   public void reply(User author, String comment) {
        replies.add(new Comment(author, comment));

    }
      
}
