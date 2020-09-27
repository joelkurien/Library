import java.util.Scanner;
import java.util.Random;
import java.io.*;

class Book{
    private int isbn;
    public String author;
    private String publication;
    private int no_of_pages;
    public String bookName;
    
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
    public Book[] books;

    Library(int size){
        n = size;
        books = new Book[n];
    }
    void NoOfBooks(){ System.out.println(n); }

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
        for(int i=0; i<n; i++){
            if(findBook.equals(books[i].bookName)){
                for(int j=i; j<n-1; j++){
                    books[j] = books[j+1];
                }
                break;
            }
        }
        return books;
    }

    void library_display(){
        System.out.println(books.length);
        try{
            for(int i=0; i<books.length; i++){
                books[i].display();
            }
        }
        catch(Exception e){
            System.out.println("There is extra space");
        }
        
    }
}


class Member extends Library{

    private String member_name;
    private int member_phone;
    private String date_of_borrow;
    private String date_of_return;
    public String membership;
    public String borrow_book;

    Member(int n){
        super(n);
        member_name = "";
        member_phone = 0;
        membership = "None";
        date_of_borrow = "0-0-0";
        date_of_return = "0-0-0";
    }

    public void member_creation(){
        Scanner mb = new Scanner(System.in);
        
        System.out.printf("Enter your name: ");
        member_name = mb.next();

        System.out.printf("Enter your phone number: ");
        member_phone = mb.nextInt();
        
        System.out.println("Choose your membership: ");
        System.out.println("1. VIP Members ");
        System.out.println("2. Regular Members");
        System.out.println("3. Student Members");
        System.out.println("4. None");
        
        int quality;
        quality = mb.nextInt();
        switch(quality){
            case 1: membership = "VIP"; break;
            case 2: membership = "Regular"; break;
            case 3: membership = "Student"; break;
            case 4: membership = "None"; break;
        }

        String test_book;
        while(true){
            System.out.println("Enter the book to borrow: ");
            test_book = mb.nextLine();
            if(book_search(test_book) == true){
                borrow_book = test_book;
                break;
            } 
            else { System.out.println("The book is not available"); }
        }

        System.out.printf("Enter the Date of borrow");
        date_of_borrow = mb.nextLine();

        // private String returndate(String dateborrow){

        // }
    }

    public void display(){
        System.out.println("Name of the member: " + member_name);
        System.out.println("Phone number of the member: " + Integer.toString(member_phone));
        System.out.println("Membership Quality: " + membership);
        System.out.println("Book Borrowed: " + borrow_book);
        System.out.println("Date of Borrow: " + date_of_borrow);
        
    }
}

public class project{
    public static void main(String[] args) {
        Member member1 = new Member(1);
        member1.NoOfBooks();
        member1.library_input();   
        member1.library_display();
        member1.member_creation();
        member1.display();
    }
}
