import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){
        File dirGames = new File("C:\\Users\\andre\\IdeaProjects\\FileHandlingInstallation\\games");
        StringBuilder sb = new StringBuilder();

        //В папке Games создайте несколько директорий: src, res, savegames, temp.
        List<String> listDirGames = new ArrayList<>(Arrays.asList("src", "res", "savegames", "temp"));
        for (String name : listDirGames) {
            createDirectoryAddLogs(dirGames.getPath(),name,sb);
        }

        //В каталоге src создайте две директории: main, test.
        List<String> listDirSrc = new ArrayList<>(Arrays.asList("main", "test"));
        for (String name : listDirSrc) {
            createDirectoryAddLogs(dirGames.getPath() + "\\src",name,sb);
        }

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        List<String> listFileMain = new ArrayList<>(Arrays.asList("Main.java", "Utils.java"));
        for (String name : listFileMain) {
            createFileAddLogs(dirGames.getPath() + "\\src\\main",name,sb);
        }

        //В каталог res создайте три директории: drawables, vectors, icons.
        List<String> listDirRes = new ArrayList<>(Arrays.asList("drawables", "vectors", "icons"));
        for (String name : listDirRes) {
            createDirectoryAddLogs(dirGames.getPath() + "\\res",name,sb);
        }

        //В директории temp создайте файл temp.txt.
        createFileAddLogs(dirGames.getPath() + "\\temp", "temp.txt", sb);

        writeLogs(dirGames.getPath() + "\\temp\\temp.txt", sb.toString());
    }

    public static void createFileAddLogs(String path, String name, StringBuilder sb) {
        File file = new File(path, name);
        try {
            if (file.createNewFile()) {
                appendedLogs(sb, file);
                System.out.printf("Файл %s создан\n", file.getName());
            }
        } catch (IOException e) {
            System.out.printf("Файл %s не создан\n", file.getName());
        }
    }

    public static void createDirectoryAddLogs(String path, String name, StringBuilder sb) {
        File file = new File(path, name);
        if (file.mkdir()) {
            appendedLogs(sb, file);
            System.out.printf("Директория %s создана\n", file.getName());
        } else {
            System.out.printf("Директория %s не создана\n", file.getName());
        }
    }

    public static void appendedLogs(StringBuilder sb, File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time = sdf.format(new Date(file.lastModified()));

        sb.append(" | ")
                .append(file.getName())
                .append(" | ")
                .append(file.exists() ? "Файл существует | " : "Файл не существует | ")
                .append(file.isDirectory() ? "Каталог | " : "Файл | ")
                .append(file.isHidden() ? "Скрытый | " : "Не скрытый | ")
                .append("Размер файла: ")
                .append(file.length())
                .append(" байт | ")
                .append("время последнего изменения файла: ")
                .append(time)
                .append(" |\n");
    }

    public static void writeLogs(String temp, String logs) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            bw.write(logs);
            System.out.println("Логи успешно записаны в файл.");
        } catch (IOException e) {
            System.out.println("При записи логов произошло исключение.");
        }
    }
}
