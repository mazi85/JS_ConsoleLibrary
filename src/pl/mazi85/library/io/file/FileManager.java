package pl.mazi85.library.io.file;

import pl.mazi85.library.model.Library;

public interface FileManager {

    Library importData();
    void exportData(Library library);
}
