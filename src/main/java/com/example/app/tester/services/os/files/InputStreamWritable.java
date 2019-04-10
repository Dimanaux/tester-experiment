package com.example.app.tester.services.os.files;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamWritable {
    void write(InputStream inputStream) throws IOException;
}
