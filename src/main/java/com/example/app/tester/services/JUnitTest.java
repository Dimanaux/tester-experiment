package com.example.app.tester.services;

import java.io.File;
import java.io.IOException;

/**
 * Runs tests in set up environment
 * using script in shell.
 * Assume having this structure:
 * tests/
 *  junit.sh
 *  1/
 *   origin/
 *    src/main
 *     Spec.java
 *   1/
 *    src/main
 *     Spec.java
 *     Solution.java
 * Look junit.sh for more information.
 */
public class JUnitTest {
    private String output;
    private SystemCommand command;
    private boolean hasBeenRun = false;

    /**
     * @param command - JUnit Script runner (../../junit.sh)
     */
    public JUnitTest(ContextCommand command) {
//        this.command = new ContextCommand(projectRoot, "../../junit.sh");
        this.command = command;
    }

    /**
     * runs the script ./tests/junit.sh
     *
     * @throws IOException on java.util.Scanner IOException
     */
    public void run() throws IOException {
        if (!hasBeenRun) {
            command.run();
            hasBeenRun = true;
            output = command.getOutput();
        }
    }

    /**
     * get results of tests
     *
     * @return test output - empty before run() call.
     * Can be empty after run() call when all tests are passed,
     * otherwise shows errors - output of JUnit
     */
    public String getOutput() {
        return output;
    }
}
