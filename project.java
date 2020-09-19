import java.util.Scanner;
import java.util.Random;

class Book{
    private int isbn;
    public String author;
    private String publication;
    private int no_of_pages;
    public String bookName;
    public static int popularity;
    Book(){
        isbn = 0;
        author = "";
        publication = "";
        no_of_pages = 0;
        bookName = "";
    }

    private int isbn_creator(){
        Random rand = new Random();
        int x = 1000000000;
        int isbn = rand.nextInt(x);
        return isbn;
    }

    public void book_input(){
        Scanner sc = new Scanner(System.in);
        
        isbn = isbn_creator();
        System.out.printf("Enter the name of the book: ");
        bookName = sc.nextLine();
        
        System.out.printf("Enter name of author: ");
        author = sc.nextLine();
        
        System.out.printf("Enter name of publication: ");
        publication = sc.nextLine();
        
        System.out.printf("Enter the no of Pages: ");
        no_of_pages = sc.nextInt();   
    }

    public void display(){
        System.out.println(isbn);
        System.out.println(bookName);
        System.out.println(author);
        System.out.println(publication);
        System.out.println(no_of_pages);
    }
}


class Library{
    private int n; 
    private Book[] books;

    Library(int size){
        n = size;
        books = new Book[n];
    }
    void disps(){
        System.out.println(n);
    }
    void library_input(){
        //Collections of Books
        for(int i=0; i<n; i++){ books[i] = new Book(); }

        //Inputs of Books
        for(int i=0; i<n; i++){
            books[i].book_input();
        }
    }

    boolean book_search(String findBook){
        for(int i=0; i<n; i++){
            if(findBook.equals(books[i].bookName)){
                return true;
            }
        }
        return false;
    }

    Book[] DeleteByBookName(String findBook){
        Book newbook[] = new Book[n];
        for(int i=0,k=0; i<n; i++){
            if(findBook.equals(books[i].bookName)){
                System.out.println("Deleted!");
                continue;
            } else {
                newbook[k++] = books[i];
            }
        }
        
        books = newbook.clone();
        return books;
    }

    void library_display(){
        System.out.println(books.length);
        for(int i=0; i<books.length; i++){
            books[i].display();
        }
    }
}

// class Member{

// }
public class project{
    public static void main(String[] args) {
        Library library1 = new Library(10);
        library1.disps();
        library1.library_input();   
        library1.library_display();
        library1.DeleteByBookName("Sherlock Holmes");
        library1.library_display();
        System.out.println(library1.book_search("Great Expectations"));
    }
}
