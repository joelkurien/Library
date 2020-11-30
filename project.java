//Imports
import java.util.Scanner;
import java.util.Random;
import java.io.*;


//Class to create a book
class Book{
    private int isbn;
    public String author;
    private String publication;
    private int no_of_pages;
    public String bookName;
    
    //Constructor of Book Class
    Book(){
        isbn = 0;
        author = "";
        publication = "";
        no_of_pages = 0;
        bookName = "";
    }

    //Function to create a random isbn
    private int id_creator(){
        Random rand = new Random();
        int x = 1000000000;
        int isbn = rand.nextInt(x);
        return isbn;
    }
    
    //Input the data of the book
    public void book_input(){
        
        Scanner sc = new Scanner(System.in);
        
        isbn = id_creator();
        System.out.printf("Enter the name of the book: ");
        bookName = sc.nextLine();
        
        System.out.printf("Enter name of author: ");
        author = sc.nextLine();
        
        System.out.printf("Enter name of publication: ");
        publication = sc.nextLine();
        
        System.out.printf("Enter the no of Pages: ");
        no_of_pages = sc.nextInt();
        
        try{
            FileWriter myfile = new FileWriter("Library.txt", true);
            myfile.write(Integer.toString(isbn) + ' ');
            myfile.write(bookName + ' ');
            myfile.write(publication + ' ');
            myfile.write(Integer.toString(no_of_pages) + " \n");
            myfile.close();
        }
        catch(IOException e){
            System.out.println("The file could not be created");
        }
    }

    //Display function
    public void display(){
        System.out.println(isbn);
        System.out.println(bookName);
        System.out.println(author);
        System.out.println(publication);
        System.out.println(no_of_pages);
    }
}

//Class to create a library of books
class Library extends Book{
    
    //Input the books into an array of books
    void library_input(){
        //Collections of Books
        book_input();
    }

    //Searching for a book
    boolean book_search(String findBook){
        try{
            Scanner search = new Scanner(new File("Library.txt"));
            while(search.hasNext()){
                String data = search.nextLine().toString();
                if(data.contains(findBook)){
                    System.out.println(data);
                    return true;
                }
            }
            search.close();
            System.out.println("The book is not available\n");
            return false;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
       
        return false;
    }

    //Deleting a book by name
    void DeleteByBookName(String findBook){
        try{
            FileWriter temp = new FileWriter("temp.txt", true);
            FileReader files = new FileReader("Library.txt");
            BufferedReader myfile = new BufferedReader(files);
            String lines;
            while((lines = myfile.readLine()) != null){
                if(lines.contains(findBook)){
                    System.out.println("The book has been removed...");
                    continue;
                }
                temp.write(lines + " \n");
            }
            temp.close();
            myfile.close();
            files.close();
         } 
         catch(FileNotFoundException e){
             System.out.println("The file is not found");
             e.printStackTrace();
         }
         catch(IOException e){
             System.out.println(e);
         }

         try{
            File newmyfile = new File("C:\\Users\\Joel John\\Documents\\Mid Sem Project\\Library.txt");
            if(newmyfile.delete()){
                System.out.println("Deleted");;
            }
            else{
                 System.out.println("Noo");
            }

            File newtemp = new File("temp.txt");
            File newfile = new File("Library.txt");
            if(newtemp.renameTo(newfile)){
                System.out.println(" ");
            }
            else{
                System.out.println("No");
            }
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }
    
    // //Displaying the books
    void library_display(){
        try{
            File myfile = new File("Library.txt");
            FileReader reader = new FileReader(myfile);
            int data;
            while((data = reader.read()) != -1){
                System.out.print((char) data);
            }
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("The file is not found\n");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("An unexpected result occurred");
        }
    }
}


class Member extends Library{

    //Data members of Member class
    private String member_name;
    private int id;
    private long member_phone;
    private String date_of_borrow;
    private String date_of_return;
    public String membership;
    public String borrow_book;

    //Constructor of Member class
    Member(){
        id = 0;
        member_name = "";
        member_phone = 0;
        membership = "None";
        date_of_borrow = "0-0-0";
        date_of_return = "0-0-0";
    }

    //Function to calculate return date based on memberships
    String returndate(String dateborrow, String mship){
        String[] parts = dateborrow.split("-");
        String returnDate = " ";
        if(mship.equals("VIP")){
            int years = Integer.parseInt(parts[2]);
            years++;
            returnDate = dateborrow.substring(0,6) + years;
            return returnDate; 
        } else {
            if(mship.equals("Regular")){
                int months = Integer.parseInt(parts[0]);
                if(months > 9){
                    months = (months + 3) - 12;    
                }else{
                    months += 3;
                }
                returnDate = Integer.toString(months) + dateborrow.substring(2,dateborrow.length());
                return returnDate;
            } else {
                if(mship.equals("Student")){
                    int weeks = Integer.parseInt(parts[1]);
                    if((Integer.parseInt(parts[0])<8 && Integer.parseInt(parts[0])%2!=0) || (Integer.parseInt(parts[0])>=8 && Integer.parseInt(parts[0])%2==0)){
                        if(weeks >= 18){
                            weeks = (weeks + 14) - 31;
                        }else {
                            weeks += 14;
                        }
                    }else{
                        if(weeks >=16){
                            weeks = (weeks + 14) - 30;
                        }else {
                            weeks += 14;
                        }
                    } 
                    returnDate = dateborrow.substring(0,3) + Integer.toString(weeks) + dateborrow.substring(5,dateborrow.length());
                    return returnDate;
            } else {
                if(mship.equals("None")){
                    int weeks = Integer.parseInt(parts[0]);
                    if((Integer.parseInt(parts[0])<8 && Integer.parseInt(parts[0])%2!=0) || (Integer.parseInt(parts[0])>=8 && Integer.parseInt(parts[0])%2==0)){
                        if(weeks >= 25){
                            weeks = (weeks + 7) - 31;
                        }else{
                            weeks += 7;
                        }
                    }else{
                        if(weeks >= 24){
                            weeks = (weeks + 7) - 30;
                        }else{
                            weeks += 7;
                        }
                    }
                    returnDate = dateborrow.substring(0,3) + Integer.toString(weeks) + dateborrow.substring(5,dateborrow.length());
                    return returnDate;
            }
        }
        }
        return "0-0-0";
    }}

    //To create a membership and borrow a book
    public void member_creation(){
        Scanner mb = new Scanner(System.in);
        
        //Random member id generation
        Random rand = new Random();
        int x = 1000000000;
        id = rand.nextInt(x);

        System.out.printf("Enter your name: ");
        member_name = mb.nextLine();

        System.out.printf("Enter your phone number: ");
        member_phone = mb.nextLong();
        
        System.out.println("Choose your membership: ");
        System.out.println("1. VIP Members ");
        System.out.println("2. Regular Members");
        System.out.println("3. Student Members");
        System.out.println("4. None");
        
        //Enter the quality of membership
        int quality;
        quality = mb.nextInt();
        switch(quality){
            case 1: membership = "VIP"; break;
            case 2: membership = "Regular"; break;
            case 3: membership = "Student"; break;
            case 4: membership = "None"; break;
        }
   
        try{
            FileWriter memfile = new FileWriter("Member.txt", true);
            memfile.write(Integer.toString(id) + " ");
            memfile.write(member_name + ' ');
            memfile.write(Long.toString(member_phone) + ' ');
            memfile.write(membership + " \n");
            memfile.close();
        }
        catch(IOException e){
            System.out.println("The file cannot be created or data cannot be inputed");
        }
    }   

    //borrowing a book
    public void borrowbook(int id){
        try{
            Scanner mem_search = new Scanner(new File("Member.txt"));
            FileWriter myfile = new FileWriter("Borrowed_Books.txt", true);
            while(mem_search.hasNext()){
                String data = mem_search.nextLine().toString();
                if(data.contains(Integer.toString(id))){
                    String array[] = data.split(" ");
                    Scanner ls = new Scanner(System.in);
                    System.out.print("Enter the book to borrow: ");
                    String searchbook = ls.nextLine();
                    if(book_search(searchbook) == true) {
                        
                        Scanner mb = new Scanner(System.in);
                        System.out.print("Enter the date of borrow: ");
                        String borrowdate = mb.nextLine();
                        String returnds = returndate(borrowdate, array[array.length - 1]);
                        String value = Integer.toString(id) + " " + searchbook + " " + borrowdate + " " + returnds + " \n";  
                        myfile.write(value);
                    }
                }
            }
            mem_search.close();
            myfile.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("Data is not inputted into the file\n");
        }
    }

    //searching for a member
    boolean member_search(int id){
        try{
            Scanner search = new Scanner(new File("Member.txt"));
            while(search.hasNext()){
                String data = search.nextLine().toString();
                if(data.contains(Integer.toString(id))){
                    System.out.println(data);
                    return true;
                }
            }
            search.close();
            System.out.println("The id is not available\n");
            return false;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
       
        return false;
    }

    //deleting a file
    private void file_replacement(String filename){
        try{
            File newmyfile = new File("C:\\Users\\Joel John\\Documents\\Mid Sem Project\\" + filename +".txt");
            if(newmyfile.delete()){
                System.out.println("Deleted");;
            }
            else{
                 System.out.println("Noo");
            }

            File newtemp = new File("newtemp.txt");
            File newfile = new File(filename + ".txt");
            if(newtemp.renameTo(newfile)){
                System.out.println(" ");
            }
            else{
                System.out.println("No");
            }
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }

    //Deleting a member of the library
    void DeleteByID(int id){
        try{
            FileWriter temp = new FileWriter("newtemp.txt", true);
            FileReader files = new FileReader("Member.txt");
            BufferedReader myfile = new BufferedReader(files);
            String lines;
            while((lines = myfile.readLine()) != null){
                if(lines.contains(Integer.toString(id))){
                    System.out.println("Member has been successfully removed...");;
                    continue;
                }
                temp.write(lines + " \n");
            }
            temp.close();
            myfile.close();
            files.close();
         } 
         catch(FileNotFoundException e){
             System.out.println("The file is not found");
             e.printStackTrace();
         }
         catch(IOException e){
             System.out.println(e);
         }

         file_replacement("Member");
    }

    //Returning a book
    void book_return(int id, String findbook){
        try{
            FileWriter temp = new FileWriter("newtemp.txt", true);
            FileReader files = new FileReader("Borrowed_Books.txt");
            BufferedReader myfile = new BufferedReader(files);
            String lines;
            while((lines = myfile.readLine()) != null){
                if(lines.contains(Integer.toString(id)) && lines.contains(findbook)){
                    System.out.println("Book has been returned");;
                    continue;
                }
                    
                temp.write(lines + " \n");
            }
            temp.close();
            myfile.close();
            files.close();
         } 
         catch(FileNotFoundException e){
             System.out.println("The file is not found");
             e.printStackTrace();
         }
         catch(IOException e){
             System.out.println(e);
         }

         file_replacement("Borrowed_Books");
    }

    //Display the member data
    public void display(){
        try{
            File myfile = new File("Member.txt");
            FileReader reader = new FileReader(myfile);
            int data;
            while((data = reader.read()) != -1){
                System.out.print((char) data);
            }
            reader.close();
        }  
        catch(FileNotFoundException e){
            System.out.println("The file does not exist");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("Error in execution");
        }
    }
}


class Menu_Library{
    Menu_Library(){
        System.out.println("Welcome librarian");
    }

    void menu(){
        Library l = new Library();
        int choice;
        do{
            System.out.println("1. Enter New Book Details: ");
            System.out.println("2. Display Book Collections: ");
            System.out.println("3. Remove a book: ");
            System.out.println("4. Search for a book");
            System.out.println("0. Exit");
            System.out.print("Enter choice [0-4] ");
            Scanner inp = new Scanner(System.in);
            choice = inp.nextInt();
            switch(choice){
                case 1:l.book_input();break;
                case 2:l.library_display();break;
                case 3:
                    String strings;
                    Scanner strs = new Scanner(System.in);
                    System.out.print("Enter the name of book to delete: ");
                    strings = strs.nextLine();
                    l.DeleteByBookName(strings);
                    break;
                case 4:
                    String stri;
                    Scanner st = new Scanner(System.in);
                    System.out.print("Enter the name of book to find: ");
                    stri = st.nextLine();
                    l.book_search(stri);
                    break;
            }
        }while(choice != 0);
    }
}

class Menu_member{
    Menu_member(){
        System.out.println("Welcome Customer!!!");
    }

    void menu(){
        Member l = new Member();
        int choice;
        do{
            System.out.println("1. Member Signup: ");
            System.out.println("2. Display Members: ");
            System.out.println("3. Remove a Member: ");
            System.out.println("4. Search for a Member");
            System.out.println("5. Borrow a book");
            System.out.println("6. Return a book");
            System.out.println("0. Exit");
            System.out.print("Enter choice [0-4] ");
            Scanner inp = new Scanner(System.in);
            choice = inp.nextInt();
            switch(choice){
                case 1:l.member_creation();break;
                case 2:l.display();break;
                case 3:
                    int identity;
                    Scanner ints = new Scanner(System.in);
                    System.out.print("Enter the id of the member to delete: ");
                    identity = ints.nextInt();
                    l.DeleteByID(identity);
                    break;
                case 4:
                    int ids;
                    Scanner inter = new Scanner(System.in);
                    System.out.print("Enter the id of the member to find: ");
                    ids = inter.nextInt();
                    l.member_search(ids);
                    break;
                case 5:
                    int its;
                    Scanner inti = new Scanner(System.in);
                    System.out.print("Enter your id: ");
                    its = inti.nextInt();
                    l.borrowbook(its);
                    break;
                case 6:
                    int value;
                    String books;
                    Scanner inta = new Scanner(System.in);
                    System.out.print("Enter the book name: ");
                    books = inta.nextLine();
                    System.out.print("Enter your id: ");
                    value = inta.nextInt();
                    l.book_return(value, books);
                    break;
            }
        }while(choice != 0);
    }
}


//Driver Code
public class project{
    public static void main(String[] args) {

        char position;
        Scanner pos = new Scanner(System.in);
        System.out.print("Who are you: [L/M]: ");
        position = pos.next().charAt(0);
        if(position == 'L' || position == 'l'){
            Menu_Library men = new Menu_Library();
            men.menu();
        }
        else{
            Menu_member mem = new Menu_member();
            mem.menu();
        }
    }
}
