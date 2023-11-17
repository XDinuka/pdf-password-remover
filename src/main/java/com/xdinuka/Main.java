package com.xdinuka;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        Path currentPath = Paths.get("").toAbsolutePath();

        List<Path> filesCreatedTodayInPath = FileUtil.findPdfFilesCreatedTodayInPath(currentPath);
        List<String> passwords = FileUtil.readPasswordsFile(currentPath.resolve("pdf-passwords.txt"));
        filesCreatedTodayInPath.stream().map(file -> PDFUtil.removePassword(file, passwords)).filter(Optional::isPresent).map(Optional::get).forEach(FileUtil::openFile);

    }
}