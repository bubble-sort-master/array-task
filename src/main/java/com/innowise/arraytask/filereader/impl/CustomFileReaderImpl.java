package com.innowise.arraytask.filereader.impl;

import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.filereader.CustomFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomFileReaderImpl implements CustomFileReader {
    private static final Logger logger = LogManager.getLogger(CustomFileReaderImpl.class);

    @Override
    public List<String> readLines(Path filePath) throws CustomArrayException {
        logger.debug("Reading all lines from file: {}", filePath);
        try {
            List<String> lines = Files.readAllLines(filePath);
            logger.info("Successfully read {} lines from file: {}", lines.size(), filePath);
            return lines;
        } catch (IOException e) {
            logger.error("IOException while reading file: {} - {}", filePath, e.getMessage());
            throw new CustomArrayException("Exception while reading file " + filePath, e);
        }
    }
}
