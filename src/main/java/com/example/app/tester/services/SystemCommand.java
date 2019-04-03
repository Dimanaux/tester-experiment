package com.example.app.tester.services;

import java.io.IOException;

/**
 * Provides interface for system command execution process
 */
public interface SystemCommand {
    /**
     * runs the command
     *
     * @param args - params of the command
     * @throws IOException - IOException during execution
     */
    void run(String... args) throws IOException;

    /**
     * @return the output of command run
     */
    String getOutput();
}
