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
       //varianta de pseudocod in care nu ma gandesc sa sparg in metode

        //1. verific daca cartea este disponibila (nr de imprumutate mai mic decat nr total de copii) in librarie
        //2. daca e disponibile, adaug codul la lista de coduri a clientului
        //3. numarul de carti imprumutate al cartii din librarie creste cu 1

        //1.1 gasesc cartea cu codul din lista de carti din librarie

        //varianta de pseudocod in care ma gandesc sa saprg in metode
        //metoda care sa verifice daca exista cartea cu ISBNCode-ul
        //metoda care sa verifice daca cartea e disponibila

        //1. daca cartea cu ISBNCode nu exista, atunci arunci arunci exceptie
        //2. daca cartea cu ISBNCode nu este disponibila, atunci arunc exceptie
        //3. adaug codul la lista de coduri a clientului
        //4. numarul de carti imprumutate al cartii din librarie creste cu 1

        //varianta cu spargere in sub metode
        Book foundBook = getLibrary().findBookByISBNCode(ISBNCode);
        if (foundBook == null){
            throw new Exception("cartea nu exista");
        }
        if (!foundBook.isAvailable()){
            throw new Exception("cartea nu e disponibila");
        }
        borrowedBookCodes[numberOfBorrowedBooks]= foundBook.getISBNCode();
        numberOfBorrowedBooks++;
        foundBook.setBorrowedNumberOfCopies(foundBook.getBorrowedNumberOfCopies()+1);

        //varianta fara spargere in sub metode de la inceput
//        boolean isFound = false;
//        for (int i = 0; i < getLibrary().getNumberOfBooks(); i++) {
//            Book currentBook = getLibrary().getBooks()[i];
//            if (ISBNCode.equals(currentBook.getISBNCode())){
//                isFound = true;
//                if (currentBook.getBorrowedNumberOfCopies()<currentBook.getTotalNumberOfCopies()){
//                    borrowedBookCodes[numberOfBorrowedBooks]= currentBook.getISBNCode();
//                    numberOfBorrowedBooks++;
//                    currentBook.setBorrowedNumberOfCopies(currentBook.getBorrowedNumberOfCopies()+1);
//                } else{
//                    throw new Exception("cartea nu este diposnibila");
//                }
//            }
//        }
//        if (!isFound){
//            throw  new Exception("cartea nu exista");
//        }











        //  Book[] book = getLibrary().getBooks();
        if (isBookAvailable(ISBNCode)) {
            getBorrowedBookCodes()[numberOfBorrowedBooks] = ISBNCode;
            numberOfBorrowedBooks++;
            Book book = getLibrary().findBookByISBNCode(ISBNCode);
            book.setBorrowedNumberOfCopies(book.getBorrowedNumberOfCopies() + 1);
            return true;
        }
        throw new Exception("Cartea nu este disponibila pentru imprumut");
    }

    public void deleteISBNCode(int startIndex){
        for (int j = startIndex; j < numberOfBorrowedBooks - 1; j++) {
            borrowedBookCodes[j] = borrowedBookCodes[j + 1];
        }
        borrowedBookCodes[numberOfBorrowedBooks - 1] = null;
        numberOfBorrowedBooks--;
    }

    public void returnBook(String ISBNCode) throws Exception {
        //daca cartea nu este in lista de carti imprumutate ale clientului, arunc exceptie
        //eliminam cartea din lista de carti imprumutate a clientului
        //scadem nr de carti imprumutate a cartii din librarie
        int foundISBNCodeIndex = getLibrary().findIndexOfBook(ISBNCode);
        if (foundISBNCodeIndex == -1 ){
            throw new Exception("nu ai imprumutat aceasta carte");
        }
        deleteISBNCode(foundISBNCodeIndex);
        Book book = getLibrary().findBookByISBNCode(ISBNCode);
        book.setBorrowedNumberOfCopies(book.getBorrowedNumberOfCopies() - 1);



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
        Book book2 = getLibrary().findBookByISBNCode(ISBNCode);
        if (book2 != null) {
            int borrowedCopies = book.getBorrowedNumberOfCopies();
            if (borrowedCopies > 0) {
                book2.setBorrowedNumberOfCopies(borrowedCopies - 1);
            } else {
                throw new Exception("Cartea cu codul " + ISBNCode + "nu este imprumutata acum");
            }


        } else {
            throw new Exception("Cartea cu codul " + ISBNCode + "nu a fost gasita in biblioteca");
        }

    }

}
