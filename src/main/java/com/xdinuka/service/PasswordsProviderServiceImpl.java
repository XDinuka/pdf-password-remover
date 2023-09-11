package com.xdinuka.service;

import java.util.ArrayList;
import java.util.List;

public class PasswordsProviderServiceImpl implements PasswordsProviderService {
    @Override
    public List<String> getAllPasswords() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("sample-password");
        return objects;
    }
}
