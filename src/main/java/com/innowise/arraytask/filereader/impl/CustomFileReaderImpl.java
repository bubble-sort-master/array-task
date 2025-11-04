package com.innowise.arraytask.filereader;

import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.validator.Validator;
import com.innowise.arraytask.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {
    private static final Logger logger = LogManager.getLogger(CustomFileReader.class);

    public List<String> readLines(Path filePath) throws CustomArrayException {
        logger.debug("Starting to read file: {}", filePath);
        List<String> validLines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            Validator validator = new ValidatorImpl();
            String line;
            while ((line = br.readLine()) != null) {
                if (validator.isValidLine(line)) {
                    validLines.add(line);
                }
            }
            logger.info("File reading completed: {} valid lines found in file: {}",
                    validLines.size(), filePath);

        } catch (IOException e) {
            logger.error("IOException while reading file: {} - {}", filePath, e.getMessage());
            throw new CustomArrayException("Exception while reading file " + filePath, e);
        }

        return validLines;
    }
}