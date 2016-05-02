/************************
 * Name: Rakul Mahenthiran
 * Date: Dec 1, 2015 
 * Course: CENG320
 * Lab: Lab7
 ************************/

package lab7server;

public class Books {

    //book fields
    String authorName;
    String bookTitle;
    String subjectArea;
    String price;
    String quantity;

    //book constructor
    public Books(String authorName, String bookTitle, String subjectArea, String quantity, String price) {
        this.authorName = authorName;
        this.bookTitle = bookTitle;
        this.subjectArea = subjectArea;
        this.quantity = quantity;
        this.price = price;
    }

    //get book name
    public String getAuthorName() {
        return authorName;
    }

    //get book title
    public String getBookTitle() {
        return bookTitle;
    }

    //get book subject
    public String getSubjectArea() {
        return subjectArea;
    }

    //get book price
    public String getPrice() {
        return price;
    }

    //get book quantity
    public String getQuantity() {
        return quantity;
    }
    
    //set book title
    public void setTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    //set book quantity
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    //set book price
    public void setPrice(String price) {
        this.price = price;
    }

    //get all book fields in string
    public String toString(){
        return (authorName+","+bookTitle+","+subjectArea+","+quantity+","+price);
    }
}

