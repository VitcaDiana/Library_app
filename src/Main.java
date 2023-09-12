
public class Main {
    public static void main(String[] args) throws Exception {
        //User client = new Client()
        Book book1 = new Book("Poezii", "Mihai Eminescu", "783y03");
        Book book2 = new Book("Amintiri din copilarie", "Ion Creanga", "7485944");
        Book book3 = new Book("Incepe cu de ce", "Simion Sinek", "4yrfh4");
        Book book4 = new Book("Basme", "Petre Ispirescu", "B2721");

        Library library = new Library(20);
        Client client1 = new Client("Ana", library);
        Admin admin = new Admin("Razvan", library);

        try {
            admin.addBook(book1);
            admin.addBook(book1);
            admin.addBook(book1);
            admin.addBook(book1);
            admin.addBook(book1);
            admin.addBook(book2);
            admin.addBook(book2);
            admin.addBook(book2);
            admin.addBook(book3);
            admin.addBook(book3);
            admin.addBook(book3);
            admin.addBook(book4);
            admin.addBook(book4);
            admin.listAllBooks();
            System.out.println(" ");

            if (client1.isBookAvailable("7485944")) {
                System.out.println("Cartea este disponibila pentru imprumut");
            }
            client1.borrowBook("783y03");
            client1.borrowBook("7485944");
            client1.borrowBook("4yrfh4");
            // client1.borrowBook("65872");

            //client1.returnBook("65872");
            client1.returnBook("4yrfh4");
            //client1.viewAvailableBooks();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        client1.viewAvailableBooks();
        System.out.println(" ");
        admin.listAllBooks();
        try {
            admin.deleteBook("B2721");
            admin.deleteBook("783y03", 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(" ");


        // admin.listBookDetails("65872");
        System.out.println(" ");
        admin.listBookDetails("783y03");
        admin.viewBorrowedBooks(client1);
        admin.listAllBooks();
        System.out.println(" ");
        client1.viewAvailableBooks();


    }
}
