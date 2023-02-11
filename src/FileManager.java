import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    //метод распаковки файла CSV
    protected static String readFileText(String path) {        //принимаем полный или относительный путь к файлу
        try {
            return Files.readString(Path.of(path)); //возвращаем содержимое файла
        } catch (IOException e) {                   //или ловим ошибку
            System.out.println("Невозможно прочитать файл с отчётом");
            return null;
        }
    }

    //метод получения имени файла месячного отчёта
    protected static String readFileName(String path) {
        File file = new File(path);
        return file.getName();
    }

    //метод работы со строкой формата CSV, достаем из нее данные
    protected static String[] fileSplit(String fileContent) {
        return fileContent.split("\\n");
    }
}
