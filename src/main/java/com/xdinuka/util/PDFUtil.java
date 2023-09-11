package com.xdinuka.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PDFUtil {

    public static Optional<File> removePassword(String inputPath, String outputPath, List<String> passwords) {

        for (String password : passwords) {
            try (PDDocument document = Loader.loadPDF(new File(inputPath), password)) {
                document.setEncryptionDictionary(null);
                document.setAllSecurityToBeRemoved(true);
                File outputFile = new File(outputPath);
                document.save(outputFile);
                return Optional.of(outputFile);
            } catch (IOException ignored) {
            }
        }
        return Optional.empty();
    }

    public static void openFile(File outputFilel) {
        try {
            Desktop.getDesktop().open(outputFilel);
        } catch (IOException ignored) {
        }
    }


}
