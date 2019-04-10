package com.example.app.tester.services.tests;

import com.example.app.tester.services.os.system.SystemCommand;

import java.io.IOException;

/**
 * Runs tests in set up environment
 * using script in shell.
 * Look junit.sh for more information.
 */
public class JUnitTest implements Test {
    private String output;
    private SystemCommand command;
    private boolean hasBeenRun = false;

    /**
     * @param command - JUnit Script runner (../../junit.sh)
     */
    public JUnitTest(SystemCommand command) {
        this.command = command;
    }

    /**
     * runs the script ./tests/junit.sh only once.
     */
    @Override
    public void run() {
        if (!hasBeenRun) {
            try {
                command.run();
                hasBeenRun = true;
                output = command.getOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get results of tests
     *
     * @return test output - empty before run() call.
     * Can be empty after run() call when all tests are passed,
     * otherwise shows errors - output of JUnit
     */
    @Override
    public String getOutput() {
        return output;
    }
}
