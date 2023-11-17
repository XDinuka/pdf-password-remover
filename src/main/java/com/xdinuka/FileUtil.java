package com.xdinuka;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {


    public static List<Path> findPdfFilesCreatedTodayInPath(Path path) throws IOException {
        final LocalDate today = LocalDate.now();
        final List<Path> filesCreatedToday = new ArrayList<>();

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)  {
                if (isCreatedOn(today, attrs.creationTime()) && isPdfFile(file)) {
                    filesCreatedToday.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return filesCreatedToday;
    }


    public static boolean isCreatedOn(LocalDate localDate, FileTime creationTime) {
        LocalDate fileCreationDate = creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return fileCreationDate.equals(localDate);
    }

    private static boolean isPdfFile(Path file) {
        return Files.isRegularFile(file) && file.toString().toLowerCase().endsWith(".pdf");
    }

    static List<String> readPasswordsFile(Path path) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    static void openFile(File file) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        } catch (IOException ignored) {
        }
    }
}
