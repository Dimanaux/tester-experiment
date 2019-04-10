package com.example.app.tester.services;

import com.example.app.tester.services.os.files.BlockingWritableFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class SpecEnvironment {
    private final File testPath;
    private final MultipartFile spec;

    /**
     * @param specId spec identifier
     * @param file   spec file to load
     */
    public SpecEnvironment(String path, int specId, MultipartFile file) {
        this.testPath = new File(path + "/" + specId + "/origin/src/main/" + file.getOriginalFilename());
        this.spec = file;
    }

    public File getTestPath() {
        return testPath;
    }

    public void setUp() throws IOException {
        if (!testPath.exists()) {
            testPath.mkdirs();
        }
        new BlockingWritableFile(testPath)
                .write(spec.getInputStream());
    }
}
