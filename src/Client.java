public class Client extends User {
    private String[] borrowedBookCodes;
    private int numberOfBorrowedBooks;

    public Client(String name, Library library) {
        super(name, library);
        this.borrowedBookCodes = new String[10];
        this.numberOfBorrowedBooks = 0;
    }

    public String[] getBorrowedBookCodes() {
        return borrowedBookCodes;
    }

    public void setBorrowedBookCodes(String[] borrowedBookCodes) {
        this.borrowedBookCodes = borrowedBookCodes;
    }

    public int getNumberOfBorrowedBooks() {
        return numberOfBorrowedBooks;
    }

    public void setNumberOfBorrowedBooks(int numberOfBorrowedBooks) {
        this.numberOfBorrowedBooks = numberOfBorrowedBooks;
    }

    public boolean isBookAvailable(String ISBNCode) throws Exception {
        //caut o carte in biblioteca dupa ISBN code
        // si verific dace este sau nu in biblioteca
//        Book book = getLibrary().findBook(ISBNCode);
//        if (book == null) {
//            throw new Exception("Cartea nu se afla in lista");
//        }
//        return book.getBorrowedNumberOfCopies() < book.getTotalNumberOfCopies();
        return getLibrary().isBookInList(ISBNCode);

    }

    public void viewAvailableBooks() {
        Book[] books = getLibrary().getBooks();
        for (int i = 0; i < getLibrary().getNumberOfBooks(); i++) {
            int availableCopies = books[i].getTotalNumberOfCopies() - books[i].getBorrowedNumberOfCopies();
            if (availableCopies > 0) {
                System.out.println("Cartea "+ books[i].getTitle()+
                        " Autor "+ books[i].getAuthor()+
                        " ISBN "+ books[i].getISBNCode()+
                        " Carti disponibile "+availableCopies);
            }
        }
       // getLibrary().listBooks();

    }

    public boolean borrowBook(String ISBNCode) throws Exception {
        //  Book[] book = getLibrary().getBooks();
        if (isBookAvailable(ISBNCode)) {
            getBorrowedBookCodes()[numberOfBorrowedBooks] = ISBNCode;
            numberOfBorrowedBooks++;
            Book book = getLibrary().findBook(ISBNCode);
            book.setBorrowedNumberOfCopies(book.getBorrowedNumberOfCopies() + 1);
            return true;
        }
        throw new Exception("Cartea nu este disponibila pentru imprumut");
    }


    public boolean returnBook(String ISBNCode) throws Exception {
        //verificam daca cartea este in lista de carti imprumutate a clientului
        boolean bookFound = false;
        for (int i = 0; i < numberOfBorrowedBooks; i++) {
            if (borrowedBookCodes[i].equals(ISBNCode)) {
                bookFound = true;
                //eliminam cartea din lista de carti imprumutate a clientului
                for (int j = i; j < numberOfBorrowedBooks - 1; j++) {
                    borrowedBookCodes[j] = borrowedBookCodes[j + 1];
                }
                borrowedBookCodes[numberOfBorrowedBooks - 1] = null;
                numberOfBorrowedBooks--;
               // return true;


            }
        }
        if (!bookFound) {
            throw new Exception("Cartea cu codul " + ISBNCode + " nu a fost imprumutata de acest client");
        }
        Book book = getLibrary().findBook(ISBNCode);
        if (book != null) {
            int borrowedCopies = book.getBorrowedNumberOfCopies();
            if (borrowedCopies > 0) {
                book.setBorrowedNumberOfCopies(borrowedCopies - 1);
            } else {
                throw new Exception("Cartea cu codul " + ISBNCode + "nu este imprumutata acum");
            }


        } else {
            throw new Exception("Cartea cu codul " + ISBNCode + "nu a fost gasita in biblioteca");
        }
        return true;
    }

}
