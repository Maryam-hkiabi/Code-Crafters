package cloudcode.maps.data_access;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserFileDataAccessObject implements UserDataAccessInterface {

    private final File txtFile;

    public UserFileDataAccessObject(String txtPath) {
        txtFile = new File(txtPath);
    }

    @Override
    public void saveSearch(String search) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true));
             BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {

            String row;

            while ((row = reader.readLine()) != null) {
                List<String> histList = new ArrayList<>(Arrays.asList(row.split(",")));

                if (!histList.contains(search)) {
                    writer.write(search + ",");
                }
                reader.close();
                writer.close();
            }
        }
    }

    @Override
    public void removeSearch(String search) throws IOException {
        Scanner scan = new Scanner(txtFile);
        StringBuilder builder = new StringBuilder();

        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            builder.append(input);
        }

        String content = builder.toString();
        content = content.replaceAll(search + ",", "");

        PrintWriter writer = new PrintWriter(txtFile);

        writer.append(content);
        writer.flush();

        writer.close();
    }

    @Override
    public void clearSearchHistory() throws IOException {
        Files.newBufferedWriter(txtFile.toPath(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
