import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        File dirGames = new File("C:\\Users\\andre\\IdeaProjects\\FileHandlingInstallation\\games");
        StringBuilder sb = new StringBuilder();

        //В папке Games создайте несколько директорий: src, res, savegames, temp.
        File dirSrc = new File(dirGames, "src");
        if (dirSrc.mkdir()) appendedLogs(sb, dirSrc);
        File dirRes = new File(dirGames, "res");
        if (dirRes.mkdir()) appendedLogs(sb, dirRes);
        File dirSavegames = new File(dirGames, "savegames");
        if (dirSavegames.mkdir()) appendedLogs(sb, dirSavegames);
        File dirTemp = new File(dirGames, "temp");
        if (dirTemp.mkdir()) appendedLogs(sb, dirTemp);

        //В каталоге src создайте две директории: main, test.
        File dirMain = new File(dirSrc, "main");
        if (dirMain.mkdir()) appendedLogs(sb, dirMain);
        File dirTest = new File(dirSrc, "test");
        if (dirTest.mkdir()) appendedLogs(sb, dirTest);

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        File fileMain = new File(dirMain, "Main.java");
        if (fileMain.createNewFile()) appendedLogs(sb, fileMain);
        File fileUtils = new File(dirMain, "Utils.java");
        if (fileUtils.createNewFile()) appendedLogs(sb, fileUtils);

        //В каталог res создайте три директории: drawables, vectors, icons.
        File dirDrawables = new File(dirRes, "drawables");
        if (dirDrawables.mkdir()) appendedLogs(sb, dirDrawables);
        File dirVectors = new File(dirRes, "vectors");
        if (dirVectors.mkdir()) appendedLogs(sb, dirVectors);
        File dirIcons = new File(dirRes, "icons");
        if (dirIcons.mkdir()) appendedLogs(sb, dirIcons);

        //В директории temp создайте файл temp.txt.
        File fileTemp = new File(dirTemp, "temp.txt");
        if (fileTemp.createNewFile()) appendedLogs(sb, fileTemp);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
}
