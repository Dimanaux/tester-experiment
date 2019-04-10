package com.example.app.tester.services;

import com.example.app.tester.services.os.files.BlockingWritableFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class SpecEnvironment {
    private final File testPathDir;
    private final MultipartFile spec;
    private final File savedFile;

    /**
     * @param specId spec identifier
     * @param file   spec file to load
     */
    public SpecEnvironment(String path, int specId, MultipartFile file) {
        // TODO refactor
        String dir = path + "/" + specId + "/origin/src/main/";
        this.testPathDir = new File(dir);
        this.savedFile = new File(dir + "/" + file.getOriginalFilename());
        this.spec = file;
    }

    public File getPath() {
        return testPathDir;
    }

    public void setUp() throws IOException {
        if (!testPathDir.exists()) {
            testPathDir.mkdirs();
        }
        if (!savedFile.exists()) {
            savedFile.createNewFile();
        }
        new BlockingWritableFile(savedFile)
                .write(spec.getInputStream());
    }
}
