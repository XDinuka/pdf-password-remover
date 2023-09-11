package com.xdinuka.service;

import java.util.HashMap;
import java.util.Map;

public class PDFPathProviderServiceImpl implements PDFPathProviderService {
    @Override
    public Map<String, String> getPDFPathMap() {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("C:/input-file.pdf","C:/output-file.pdf");

        return objectObjectHashMap;
    }
}
