package pl.mazi85.library.io.file;

import pl.mazi85.library.exception.DataExportException;
import pl.mazi85.library.exception.DataImportException;
import pl.mazi85.library.exception.InvalidDataException;
import pl.mazi85.library.model.*;
import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_users.csv";

    @Override
    public Library importData() {
      Library library = new Library();
      importPublications(library);
      importUsers(library);

      return library;
    }

    private void importUsers(Library library) {
        try(Scanner fileReader = new Scanner(new File(USERS_FILE_NAME))){

            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                LibraryUser libraryUser = createUserFromString(line);
                library.addUser(libraryUser);
            }

        }catch (FileNotFoundException e){
            throw new DataImportException("Brak pliku "+USERS_FILE_NAME);
        }

    }

    private LibraryUser createUserFromString(String csvText) {

        String[] split = csvText.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        return new LibraryUser(firstName,lastName,pesel);
    }

    private void importPublications(Library library) {

        try(Scanner fileReader = new Scanner(new File(FILE_NAME))){

            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }

        }catch (FileNotFoundException e){
            throw new DataImportException("Brak pliku "+FILE_NAME);
        }

    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)){
          return  createBook(split);
        }else if(Magazine.TYPE.equals(type)){
            return  createMagazine(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji " + type );
    }

    private Magazine createMagazine(String[] split) {
        String title = split[1];
        String publisher = split[2];
        int year = Integer.valueOf(split[3]);
        int month = Integer.valueOf(split[4]);
        int day = Integer.valueOf(split[5]);
        String language = split[6];
        return new Magazine(title,publisher,language,year,month,day);
    }

    private Book createBook(String[] split) {
        String title = split[1];
        String publisher = split[2];
        int year = Integer.valueOf(split[3]);
        String author = split[4];
        int pages = Integer.valueOf(split[5]);
        String isbn = split[6];
        return new Book(title,author,year,pages,publisher,isbn);
    }

    @Override
    public void exportData(Library library) {
        exportPublication(library);
        exportUsers(library);
    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCSv(users, USERS_FILE_NAME);
    }

    private void exportPublication(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCSv(publications, FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCSv(Collection<T> collection, String fileName) {
        try(var fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ) {

            for (T element : collection) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu do pliku "+ fileName);
        }
    }
}
