package pl.mazi85.library.app;

import pl.mazi85.library.exception.NoSuchOptionException;

public enum Option {
    EXIT(0, "wyjście z programu"),
    ADD_BOOK(1, "dodanie nowej książki"),
    ADD_MAGAZINES(2, "dodanie nowego magazynu"),
    PRINT_BOOKS(3, "wyświetl dostępne książki"),
    PRINT_MAGAZINES(4, "wyświetl dostępne magazyny");


    private final int number;
    private final String description;


    Option(int number, String description) {
        this.number = number;
        this.description = description;
    }


    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return number + " - " + description;
    }

    public static Option crateFromInt(int option) throws NoSuchOptionException {

        try {
            return Option.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o id " + option);
        }
    }
}
