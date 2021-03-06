package com.example.app.tester.services.os.system;

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
     * @return the output of command run. If not present, empty string is returned
     */
    String getOutput();

    /**
     * @return error as string. If not present, empty string is returned
     */
    String getError();
}
