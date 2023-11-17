package com.xdinuka;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class PDFUtil {

    public static Optional<File> removePassword(Path path, List<String> passwords) {

        for (String password : passwords) {
            try (PDDocument document = Loader.loadPDF(path.toFile(), password)) {
                if (document.isEncrypted()) {
                    document.setAllSecurityToBeRemoved(true);
                    File outputFile = new File(path.getParent().toString(), "unencrypted_" + path.getFileName());
                    document.save(outputFile);
                    return Optional.of(outputFile);
                }
            } catch (IOException ignored) {
            }
        }
        return Optional.empty();
    }

}
