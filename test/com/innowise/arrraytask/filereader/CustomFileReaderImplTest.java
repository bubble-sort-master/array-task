package com.innowise.arrraytask.filereader;

import com.innowise.arraytask.exception.CustomArrayException;
import com.innowise.arraytask.filereader.CustomFileReader;
import com.innowise.arraytask.filereader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderImplTest {

    private final CustomFileReader fileReader = new CustomFileReaderImpl();
    private static final Path VALID_FILE = Path.of("data/input.txt");

    @Test
    void givenValidFile_whenReadLines_thenReturnAllLines() throws CustomArrayException {
        List<String> actual = fileReader.readLines(VALID_FILE);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty())
        );
    }

    @Test
    void givenNonExistingFile_whenReadLines_thenThrowException() {
        Path invalidPath = Path.of("data/missing.txt");

        assertThrows(CustomArrayException.class, () -> fileReader.readLines(invalidPath));
    }
}