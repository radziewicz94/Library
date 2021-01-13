package mradziewicz.io.file;

import mradziewicz.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
