package com.example.app.tester.services;

import com.example.app.tester.services.os.files.BlockingWritableFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class TestEnvironment {
    private final File testPath;
    private final MultipartFile solutionFile;
    private final String rootPath;

    /**
     * @param specId solutionFile identifier
     * @param solutionId
     * @param file   solutionFile file to load
     */
    public TestEnvironment(String path, Long specId, Long solutionId, MultipartFile file) {
        this.rootPath = path + "/" + specId + "/" + solutionId;
        this.testPath = new File(rootPath + "/src/main/" + file.getOriginalFilename());
        this.solutionFile = file;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setUp() throws IOException {
        new BlockingWritableFile(testPath)
                .write(solutionFile.getInputStream());
    }
}
