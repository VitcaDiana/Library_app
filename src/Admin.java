public class Admin extends User {


    public Admin(String name, Library library) {
        super(name, library);
    }

    public boolean addBook(Book newBook) {
        Book bookIsInList = getLibrary().findBookByISBNCode(newBook.getISBNCode());
        //verific daca cartea exista in biblioteca
        if (bookIsInList != null) {
            //crestem nr de copii
            bookIsInList.setTotalNumberOfCopies(bookIsInList.getTotalNumberOfCopies() + 1);
            return true;
        }

        //daca cartea nu exista o adaug in biblioteca
        else {
            getLibrary().addBookInList(newBook);
            newBook.setTotalNumberOfCopies(1);
            return true;
        }

    }

    public void deleteBook(String ISBNCode) throws Exception {
        //verific daca cartea este in lista
        int foundIndex = getLibrary().findIndexOfBook(ISBNCode);
        if (foundIndex == -1) {
            throw new Exception("book does not exist");
        }
        //mutam elementele de dupa cartea gasita cu o pozitie la stanga
        getLibrary().deleteBook(foundIndex);


        if (getLibrary().isBookInList(ISBNCode)) {
            int index = getLibrary().findIndexOfBook(ISBNCode);
            //mutam elementele de dupa cartea gasita cu o pozitie la stanga
            for (int i = index; i < getLibrary().getNumberOfBooks() - 1; i++) {
                getLibrary().getBooks()[i] = getLibrary().getBooks()[i + 1];
            }
            //actualizam nr de carti
            getLibrary().setNumberOfBooks(getLibrary().getNumberOfBooks() - 1);
        } else {
            throw new Exception("Cartea nu există în bibliotecă.");
        }

    }

    public void deleteBook(String ISBNCode, int numberOfCopiesToDelete) throws Exception {
        //verific daca cartea este in lista


        //daca cartea nu exista in bilioteca, arunc exceptie
        //updatez numarul de copii al cartii din biblioteca

        Book book = getLibrary().findBookByISBNCode(ISBNCode);
        if (book == null){
            throw  new Exception("cartea nu exista");
        }
        if (book.getTotalNumberOfCopies()<=numberOfCopiesToDelete){
            throw  new Exception("you cannot delete this amount of cpies");
        }
        book.setTotalNumberOfCopies(book.getTotalNumberOfCopies()-numberOfCopiesToDelete);



//        if (getLibrary().isBookInList(ISBNCode)) {
//            Book book = getLibrary().findBookByISBNCode(ISBNCode);
//            int remainingCopies = book.getTotalNumberOfCopies() - numberOfCopiesToDelete;
//
//            if (remainingCopies >= 0) {
//                book.setTotalNumberOfCopies(remainingCopies);
//            } else {
//                throw new Exception("Nu există suficiente copii pentru a șterge.");
//            }
//        }else{
//                throw new Exception("Cartea nu există în bibliotecă.");
//            }
    }




    public void listAllBooks() {
        getLibrary().listBooks();
    }
    public void listBookDetails(String ISBNCode) {
        if (getLibrary().isBookInList(ISBNCode)) {
            Book book = getLibrary().findBookByISBNCode(ISBNCode);
            System.out.println(book.toString());
        } else {
            System.out.println("Cartea nu există în bibliotecă.");
        }
    }

    public void viewBorrowedBooks(Client client) {
        for (int i = 0; i < client.getNumberOfBorrowedBooks(); i++) {
            System.out.println(client.getBorrowedBookCodes()[i]);
        }
    }
    }






