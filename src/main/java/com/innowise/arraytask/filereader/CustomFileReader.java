package com.innowise.arraytask.filereader;

import com.innowise.arraytask.exception.CustomArrayException;

import java.nio.file.Path;
import java.util.List;

public interface CustomFileReader {
    List<String> readLines(Path filePath) throws CustomArrayException;
}
