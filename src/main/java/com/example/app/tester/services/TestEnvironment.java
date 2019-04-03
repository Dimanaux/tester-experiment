package com.example.app.tester.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class TestEnvironment {
    private final File testPath;
    private final MultipartFile solutionFile;
    private final String rootPath;

    /**
     * @param specId solutionFile identifier
     * @param file   solutionFile file to load
     */
    public TestEnvironment(String path, int specId, int solutionId, MultipartFile file) {
        this.rootPath = path + "/" + specId + "/" + solutionId;
        this.testPath = new File(rootPath + "/src/main/" + file.getOriginalFilename());
        this.solutionFile = file;


    }

    public String getRootPath() {
        return rootPath;
    }

    public void setUp() throws IOException {
        if (!testPath.exists()) {
            testPath.mkdirs();
        }
        solutionFile.transferTo(testPath);
    }
}
