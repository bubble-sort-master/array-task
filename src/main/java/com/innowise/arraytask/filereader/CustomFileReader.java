package com.innowise.arraytask.filereader;


import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.validator.Validator;
import com.innowise.arraytask.validator.impl.ValidatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {

    private final Path filePath;

    public CustomFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public List<String> readValidLines() throws CustomArrayException {
        List<String> validLines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            Validator validator = new ValidatorImpl();
            String line;
            while ((line = br.readLine()) != null) {
                if (validator.isValidLine(line)) {
                    validLines.add(line);
                }
            }
        } catch (IOException e) {
            throw new CustomArrayException("Exception while reading file " + filePath, e);
        }
        return validLines;
    }
}