public class Library {
    private Book[] books;
    private int numberOfBooks;

    public Library(int size) {
        this.books = new Book[size];

    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public boolean isBookInList(String ISBNCode) {
        //verific daca cartea este in lista
        for (int i = 0; i < numberOfBooks ; i++) {
            if (books[i] != null && ISBNCode.equals(books[i].getISBNCode())) {
                return true;
            }
        }
        return false;
    }

    public int findIndexOfBook(String ISBNCode) {
        //caut in lista de carti codul ISBN introdus iar daca il gasesc returnez pozitia la care l-am gasit
        for (int i = 0; i < numberOfBooks; i++) {
            if (ISBNCode.equals(books[i].getISBNCode())) {
                return i;
            }
        }
        return -1;
    }

    public Book findBook(String ISBNCode) {
        for (int i = 0; i < numberOfBooks; i++) {
            if (ISBNCode.equals(books[i].getISBNCode())) {
                return books[i];
            }
        }
        return null;
    }

    public void addBookInList(Book book) {
        books[numberOfBooks] = book;
        numberOfBooks++;
    }


    public void listBooks() {
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println(books[i]);
        }
    }
}




