package com.xdinuka;

import com.xdinuka.service.PDFPathProviderServiceImpl;
import com.xdinuka.service.PasswordsProviderServiceImpl;
import com.xdinuka.util.PDFUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        List<String> allPasswords = new PasswordsProviderServiceImpl().getAllPasswords();
        Map<String, String> pdfPathMap = new PDFPathProviderServiceImpl().getPDFPathMap();
        pdfPathMap.forEach((key, value) -> {
            Optional<File> file = PDFUtil.removePassword(key, value, allPasswords);
            file.ifPresent(PDFUtil::openFile);
        });
    }
}