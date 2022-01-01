package pl.mazi85.library.io.file;

import pl.mazi85.library.exception.DataExportException;
import pl.mazi85.library.exception.DataImportException;
import pl.mazi85.library.exception.InvalidDataException;
import pl.mazi85.library.model.Book;
import pl.mazi85.library.model.Library;
import pl.mazi85.library.model.Magazine;
import pl.mazi85.library.model.Publication;

import java.io.*;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";

    @Override
    public Library importData() {
      Library library = new Library();
      try(Scanner fileReader = new Scanner(new File(FILE_NAME))){

          while (fileReader.hasNextLine()){
              String line = fileReader.nextLine();
              Publication publication = createObjectFromString(line);
              library.addPublication(publication);
          }

      }catch (FileNotFoundException e){
          throw new DataImportException("Brak pliku "+FILE_NAME);
      }
      return library;
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
        Publication[] publications = library.getPublications();
        try(var fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ) {

            for (Publication publication : publications) {
                bufferedWriter.write(publication.toCsv());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new DataImportException("Błąd zapisu do pliku "+ FILE_NAME);
        }

    }
}
