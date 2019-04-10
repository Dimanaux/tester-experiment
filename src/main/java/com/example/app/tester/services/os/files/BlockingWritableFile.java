package com.example.app.tester.services.os.files;

import java.io.*;

/**
 * Creates a file, writes given input stream to file.
 * Can copy file, if using with FileInputStream.
 */
public class BlockingWritableFile implements InputStreamWritable {
    private final File file;

    /**
     * @param file file to write
     */
    public BlockingWritableFile(File file) {
        this.file = file;
    }

    /**
     * @param inputStream input source. Will be closed after writing.
     * @throws IOException happens if inputStream throws
     */
    @Override
    public void write(InputStream inputStream) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[512];

        int read = inputStream.read(buffer);
        while (read != -1) {
            outputStream.write(buffer, 0, read);
            read = inputStream.read(buffer);
        }
        inputStream.close();
        outputStream.close();
    }
}
