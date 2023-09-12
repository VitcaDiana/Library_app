public class User {
    private String name;
    private Library getLibrary;

    public User(String name, Library library) {
        this.name = name;
        this.getLibrary = library;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Library getLibrary() {
        return getLibrary;
    }

    public void setGetLibrary(Library getLibrary) {
        this.getLibrary = getLibrary;
    }
}
